package com.math012.payment_service.business.converter;

import com.math012.payment_service.business.DTO.OrderResponseDTO;
import com.math012.payment_service.infra.document.PaymentDocument;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentConverter {
    PaymentDocument convertToPaymentDocumentFromOrderResponseDTO(OrderResponseDTO dto);
    OrderResponseDTO convertToOrderResponseDTOFromPaymentDocument(PaymentDocument document);
}