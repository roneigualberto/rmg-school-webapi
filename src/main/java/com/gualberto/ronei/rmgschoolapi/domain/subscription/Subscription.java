package com.gualberto.ronei.rmgschoolapi.domain.subscription;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subscription {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Boolean finished;

    @Column
    private LocalDateTime finishedDate;


    public CompletedLecture completedLecture(Lecture lecture) {

        if (!Objects.equals(course, lecture.getCourse())) {
            throw new DomainException("Lecture has no course");
        }

        return CompletedLecture.builder().subscription(this)
                .completedDate(LocalDateTime.now())
                .lecture(lecture).build();
    }
}
