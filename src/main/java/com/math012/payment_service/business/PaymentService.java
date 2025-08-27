package com.math012.payment_service.business;

import com.math012.payment_service.business.DTO.OrderResponseDTO;
import com.math012.payment_service.business.converter.PaymentConverter;
import com.math012.payment_service.business.topics.kafka.producer.PaymentProducer;
import com.math012.payment_service.infra.enums.TransactionStatus;
import com.math012.payment_service.infra.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private PaymentProducer producer;

    @Autowired
    private PaymentRepository repository;

    @Autowired
    PaymentConverter converter;


    public void transactionResult(OrderResponseDTO dto){
        Random random = new Random();
        var response = random.nextBoolean();
        dto.setTimestamp(new Date());
        if (response){
            dto.setStatus(TransactionStatus.TRANSACTION_SUCCESS);
            log.info("transactionSuccess: Iniciando a conversão de DTO para entidade MongoDB");
            var entity = converter.convertToPaymentDocumentFromOrderResponseDTO(dto);
            log.info("transactionSuccess: conversão de DTO para entidade MongoDB feita com sucesso");
            log.info("transactionSuccess: salvando dados da transação");
            repository.save(entity);
            log.info("transactionSuccess: dados da transação salvos com sucesso");
            log.info("transactionSuccess: produzindo o tópico payment success");
            producer.sendEventPaymentSuccess(dto);
            log.info("transactionSuccess: tópico payment success produzido com sucesso");
            log.info("transactionSuccess: transaction success");
        }
        else {
            dto.setStatus(TransactionStatus.TRANSACTION_FAILED);
            log.info("transactionFailed: Iniciando a conversão de DTO para entidade MongoDB");
            var entity = converter.convertToPaymentDocumentFromOrderResponseDTO(dto);
            log.info("transactionFailed: conversão de DTO para entidade MongoDB feita com sucesso");
            log.info("transactionFailed: salvando dados da transação");
            repository.save(entity);
            log.info("transactionFailed: dados da transação salvos com sucesso");
            log.info("transactionFailed: produzindo o tópico payment failed");
            producer.sendEventPaymentFailed(dto);
            log.info("transactionFailed: tópico payment failed produzido com sucesso");
            log.info("transactionFailed: transaction failed");
        }
    }
}