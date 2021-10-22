package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class PaymentForm {

    private PaymentTypeEnum type;

    private String cardHolderName;

    private String cardNumber;

    private Integer expirationYear;

    private Integer expirationMonth;

    private String cvv;

    private Integer installments;

    private boolean shouldSave;
}
