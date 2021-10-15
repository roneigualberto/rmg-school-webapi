package com.gualberto.ronei.rmgschoolapi.domain.course;

import java.util.List;

public interface CourseService {


    Course createCourse(Long instructorId, CourseForm courseForm);

    List<Course> findByInstructor(Long instructorId);

}
