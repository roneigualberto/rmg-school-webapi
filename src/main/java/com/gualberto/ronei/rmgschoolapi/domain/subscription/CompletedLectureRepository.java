package com.gualberto.ronei.rmgschoolapi.domain.subscription;

import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompletedLectureRepository extends JpaRepository<CompletedLecture, Long> {


    void deleteBySubscriptionAndLecture(Subscription subscription, Lecture lecture);
}
