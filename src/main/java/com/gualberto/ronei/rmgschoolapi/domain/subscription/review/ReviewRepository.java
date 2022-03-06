package com.gualberto.ronei.rmgschoolapi.domain.subscription.review;

import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.completedlecture.CompletedLecture;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


}
