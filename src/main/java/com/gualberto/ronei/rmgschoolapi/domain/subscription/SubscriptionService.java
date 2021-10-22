package com.gualberto.ronei.rmgschoolapi.domain.subscription;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;

import java.util.List;

public interface SubscriptionService {

    List<Subscription> create(User student, List<Course> courses);
}
