package com.gualberto.ronei.rmgschoolapi.domain.subscription;

import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByStudent(User student);


}