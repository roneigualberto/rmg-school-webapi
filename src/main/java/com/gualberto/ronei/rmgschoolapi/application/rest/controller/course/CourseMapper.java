package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {


    CourseResponse fromCourse(Course course);

    List<CourseResponse> fromCourses(List<Course> courses);

    CourseForm toCourseForm(CourseRequest request);

    SectionForm toSectionForm(SectionRequest sectionRequest);

    LectureForm toLectureForm(LectureRequest lectureRequest);

    LectureResponse fromLecture(Lecture lecture);

    List<LectureResponse> fromLectures(List<Lecture> lectures);

    SectionResponse fromSection(Section section);

    List<SectionResponse> fromSections(List<Section> lectures);

}
