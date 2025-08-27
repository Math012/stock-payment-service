package com.math012.payment_service.business.topics.kafka.producer;

import com.math012.payment_service.business.DTO.OrderResponseDTO;
import com.math012.payment_service.business.converter.ConvertToJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    ConvertToJson converter;

    @Value("${spring.kafka.producer.payment.success.topic}")
    private String paymentSuccess;

    @Value("${spring.kafka.producer.payment.failed.topic}")
    private String paymentFailed;

    public void sendEventPaymentSuccess(OrderResponseDTO dto) {
        try {
            log.info("sendEventPaymentSuccess: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendEventPaymentSuccess: DTO convertido com sucesso");
            log.info("sendEventPaymentSuccess: enviando o tópico: {}", paymentSuccess);
            kafkaTemplate.send(paymentSuccess, content);
            log.info("sendEventPaymentSuccess: Tópico: {} enviado com sucesso", paymentSuccess);
        } catch (Exception e) {
            log.error("sendEventPaymentSuccess: Error while sending the topic: {paymentSuccess} error message: {}", e.getMessage());
        }
    }

    public void sendEventPaymentFailed(OrderResponseDTO dto) {
        try {
            log.info("sendEventPaymentFailed: Convertendo o objeto dto: {} para Json", dto);
            var content = converter.convertToJson(dto);
            log.info("sendEventPaymentFailed: DTO convertido com sucesso");
            log.info("sendEventPaymentFailed: enviando o tópico: {}", paymentFailed);
            kafkaTemplate.send(paymentFailed, content);
            log.info("sendEventPaymentFailed: Tópico: {} enviado com sucesso", paymentFailed);
        } catch (Exception e) {
            log.error("sendEventPaymentFailed: Error while sending the topic: {paymentFailed} error message: {}", e.getMessage());
        }
    }
}