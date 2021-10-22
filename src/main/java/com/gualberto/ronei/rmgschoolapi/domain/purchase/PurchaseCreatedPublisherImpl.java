package com.gualberto.ronei.rmgschoolapi.domain.purchase;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PurchaseCreatedPublisherImpl implements PurchaseCreatedPublisher {


    private final List<PurchaseCreatedSubscriber> subscribers;


    @Override
    public void publish(PurchaseCreatedEvent event) {
        subscribers.forEach((subscriber) -> subscriber.handler(event));
    }
}
