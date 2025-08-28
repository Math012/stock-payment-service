package com.math012.payment_service.business.DTO;

import com.math012.payment_service.infra.enums.TransactionStatus;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class OrderResponseDTO {
    private String productName;
    private int totalQuantity;
    private double productPrice;
    private Date timestamp;
    private String userEmail;
    private TransactionStatus status;
}