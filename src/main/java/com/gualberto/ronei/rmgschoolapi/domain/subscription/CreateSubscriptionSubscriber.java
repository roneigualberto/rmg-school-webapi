package com.gualberto.ronei.rmgschoolapi.domain.subscription;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.Purchase;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseCreatedEvent;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseCreatedSubscriber;
import com.gualberto.ronei.rmgschoolapi.domain.purchase.PurchaseItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CreateSubscriptionSubscriber implements PurchaseCreatedSubscriber {


    private final SubscriptionService subscriptionService;

    @Override
    public void handler(PurchaseCreatedEvent event) {

        Purchase purchase = event.getPurchase();

        List<Course> courses = purchase.getItems().stream().map(PurchaseItem::getCourse).collect(Collectors.toList());

        subscriptionService.create(purchase.getOwner(), courses);

    }
}
