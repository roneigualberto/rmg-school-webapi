package com.gualberto.ronei.rmgschoolapi.domain.subscription;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompletedLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Lecture lecture;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime completedDate;

}
