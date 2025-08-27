package com.math012.payment_service.infra.document;

import com.math012.payment_service.infra.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collation = "payment")
public class PaymentDocument {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String productName;
    private int totalQuantity;
    private double productPrice;
    private Date timestamp;
    private String userEmail;
    private TransactionStatus status;
}