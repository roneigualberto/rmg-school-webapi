package com.gualberto.ronei.rmgschoolapi.infra.tests;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;

public final class CourseTestConstants {


    //Course
    public static final Long COURSE_ID = 113L;
    public static final String COURSE_NAME = "Course Name";
    public static final String COURSE_TITLE = "Course Title";
    public static final String COURSE_ABOUT = "About Course";
    public static final Double COURSE_PRICE = 19.99;
    public static final Course DEFAULT_COURSE;

    //Section
    public static final Long SECTION_1_ID = 233L;
    public static final String SECTION_1_NAME = "Section 1";
    public static final Integer SECTION_1_ORDER = 1;
    public static final Section SECTION_1;

    public static final Long LECTURE_1_ID = 123L;
    public static final String LECTURE_1_TITLE = "Lecture 1";
    public static final Integer LECTURE_1_ORDER = 1;
    public static final Lecture LECTURE_1;


    static {
        DEFAULT_COURSE = Course.builder()
                .id(COURSE_ID)
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .about(COURSE_ABOUT)
                .instructor(UserTestConstants.DEFAULT_USER)
                .skillLevel(SkillLevelEnum.BEGINNER)
                .category(CategoryTestContants.DEFAULT_CATEGORY)
                .subCategory(CategoryTestContants.DEFAULT_SUB_CATEGORY)
                .price(COURSE_PRICE)
                .build();

        SECTION_1 = Section.builder()
                .name(SECTION_1_NAME)
                .order(SECTION_1_ORDER)
                .build();

        LECTURE_1 = Lecture.builder().section(SECTION_1)
                .id(LECTURE_1_ID)
                .title(LECTURE_1_TITLE)
                .order(LECTURE_1_ORDER)
                .type(LectureType.VIDEO)
                .build();
    }


}
