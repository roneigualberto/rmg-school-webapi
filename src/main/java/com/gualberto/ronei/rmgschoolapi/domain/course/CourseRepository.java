package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByInstructor(User instructor);
}
