package com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase;

import com.gualberto.ronei.rmgschoolapi.domain.purchase.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class PaymentRequest {

    private PaymentTypeEnum type;

    private String cardHolderName;

    private String cardNumber;

    private Integer expirationYear;

    private Integer expirationMonth;

    private String cvv;

    private Integer installments;

    private boolean shouldSave;
}
