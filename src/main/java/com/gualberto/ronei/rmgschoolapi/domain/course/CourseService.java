package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;

import java.io.InputStream;
import java.util.List;

public interface CourseService {


    Course createCourse(CourseForm courseForm);

    List<Course> getMyCourses();

    Section addSection(Long courseId, SectionForm sectionForm);

    Lecture addLecture(Long courseId, LectureForm lectureForm);

    void storeLectureContent(Long courseId, Long lectureId, InputStream content);

    InputStream getLectureContent(Long courseId, Long lectureId);
}
