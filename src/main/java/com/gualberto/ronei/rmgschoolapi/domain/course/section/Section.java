package com.gualberto.ronei.rmgschoolapi.domain.course.section;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "section_order"},
        name = "section_order_un"))
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Course course;

    @Column(nullable = false)
    private String name;

    @Column(name = "section_order", nullable = false)
    private Integer order;

    @Builder.Default
    @OneToMany(mappedBy = "section")
    @ToString.Exclude
    private List<Lecture> lectures = new ArrayList<>();


    public void addLecture(Lecture lecture) {

        lecture.setSection(this);
        lecture.setCourse(course);
        this.lectures.add(lecture);
    }

}
