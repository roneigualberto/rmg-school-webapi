package com.gualberto.ronei.rmgschoolapi.domain.subscription;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Transactional
    @Override
    public List<Subscription> create(User student, List<Course> courses) {

        List<Subscription> subscriptions = courses.stream().map((course) ->
                Subscription.builder().student(student)
                        .course(course)
                        .createdAt(LocalDateTime.now())
                        .finished(false)
                        .build()
        ).collect(Collectors.toList());

        subscriptionRepository.saveAll(subscriptions);

        return subscriptions;
    }
}
