package com.gualberto.ronei.rmgschoolapi.domain.course.section;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section, Long> {


    boolean existsByCourseAndOrder(Course course, Integer order);
}
