package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Builder
@Data
public class PurchaseForm {

    private PaymentForm payment;

    private Set<Long> courseIds;
}
