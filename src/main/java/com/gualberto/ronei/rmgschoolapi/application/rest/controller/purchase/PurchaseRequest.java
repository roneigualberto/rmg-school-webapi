package com.gualberto.ronei.rmgschoolapi.application.rest.controller.purchase;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PurchaseRequest {
    private PaymentRequest payment;

    private Set<Long> courseIds;


}