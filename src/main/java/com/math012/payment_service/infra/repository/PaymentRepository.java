package com.math012.payment_service.infra.repository;

import com.math012.payment_service.infra.document.PaymentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentDocument, Integer> {
}
