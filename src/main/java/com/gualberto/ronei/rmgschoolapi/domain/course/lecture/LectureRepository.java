package com.gualberto.ronei.rmgschoolapi.domain.course.lecture;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {


    boolean existsBySectionAndOrder(Section section, Integer order);
}
