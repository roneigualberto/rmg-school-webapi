package com.gualberto.ronei.rmgschoolapi.domain.subscription;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.completedlecture.CompletedLecture;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.completedlecture.CompletedLectureRepository;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.review.Review;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.review.ReviewForm;
import com.gualberto.ronei.rmgschoolapi.domain.subscription.review.ReviewRepository;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
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

    private final CourseService courseService;

    private final CompletedLectureRepository completedLectureRepository;

    private final ReviewRepository reviewRepository;

    private final LoggedUserContext loggedUserContext;


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

    @Override
    public List<Subscription> getLoggedStudentSubscriptions() {
        User student = loggedUserContext.getLoggedUser();
        return subscriptionRepository.findByStudent(student);
    }

    @Override
    public void completeLecture(Long subscriptionId, Long lectureId) {

        Subscription subscription = get(subscriptionId);

        Lecture lecture = courseService.getLectureById(lectureId).orElseThrow(() -> new DomainException("Lecture not found"));


        CompletedLecture completedLecture = subscription.completedLecture(lecture);


        completedLectureRepository.save(completedLecture);


    }

    @Override
    public void deleteCompletedLecture(Long subscriptionId, Long lectureId) {

        Subscription subscription = get(subscriptionId);

        Lecture lecture = courseService.getLectureById(lectureId).orElseThrow(() -> new DomainException("Lecture not found"));

        completedLectureRepository.deleteBySubscriptionAndLecture(subscription, lecture);
    }

    @Override
    public void createReview(Long subscriptionId, ReviewForm reviewForm) {
        Subscription subscription = get(subscriptionId);
        Review review = subscription.createReview(reviewForm);
        reviewRepository.save(review);
    }

    private Subscription get(Long subscriptionId) {
        return subscriptionRepository
                .findById(subscriptionId).orElseThrow(() -> new DomainException("Subscription not found"));
    }


}
