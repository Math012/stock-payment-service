package com.math012.payment_service.business.topics.kafka.consumer;

import com.math012.payment_service.business.DTO.OrderResponseDTO;
import com.math012.payment_service.business.PaymentService;
import com.math012.payment_service.business.converter.ConvertToJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentConsumer {

    @Autowired
    private PaymentService service;

    @Autowired
    ConvertToJson converter;

    @KafkaListener(
            topics = "${spring.kafka.consumer.send.order.for.payment.topic}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void orderForPayment(String order){
        log.info("orderForPayment: Consumindo o tópico send.order.for.payment.topic");
        OrderResponseDTO dto = converter.readJson(order, OrderResponseDTO.class);
        service.transactionResult(dto);
    }
}