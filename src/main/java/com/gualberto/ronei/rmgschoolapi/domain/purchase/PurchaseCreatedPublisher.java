package com.gualberto.ronei.rmgschoolapi.domain.purchase;

public interface PurchaseCreatedPublisher {

    void publish(PurchaseCreatedEvent event);
}
