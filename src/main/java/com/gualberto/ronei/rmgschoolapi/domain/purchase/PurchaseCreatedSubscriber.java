package com.gualberto.ronei.rmgschoolapi.domain.purchase;

public interface PurchaseCreatedSubscriber {

    void handler(PurchaseCreatedEvent event);
}
