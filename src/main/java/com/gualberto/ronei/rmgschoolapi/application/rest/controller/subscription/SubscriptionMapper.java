package com.gualberto.ronei.rmgschoolapi.application.rest.controller.subscription;

import com.gualberto.ronei.rmgschoolapi.domain.subscription.Subscription;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {


    SubscriptionResponse fromSubscription(Subscription subscription);

    List<SubscriptionResponse> fromSubscriptions(List<Subscription> subscriptions);


}
