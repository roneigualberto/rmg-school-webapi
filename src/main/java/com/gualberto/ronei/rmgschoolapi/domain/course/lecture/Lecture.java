package com.gualberto.ronei.rmgschoolapi.domain.course.lecture;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"section_id", "lecture_order"},
        name = "lecture_order_un"))
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Section section;

    @Enumerated(EnumType.STRING)
    private LectureType type;

    @Column(nullable = false)
    private String title;

    @Column(name = "lecture_order", nullable = false)
    private Integer order;


}
