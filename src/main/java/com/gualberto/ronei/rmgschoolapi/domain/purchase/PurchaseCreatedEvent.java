package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PurchaseCreatedEvent {

    private Purchase purchase;
}
