package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;

import java.util.List;

public interface CourseService {


    Course createCourse(CourseForm courseForm);

    List<Course> getMyCourses();

    Section addSection(Long courseId, SectionForm sectionForm);
}
