package com.gualberto.ronei.rmgschoolapi.infra.tests;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum;

public final class CourseTestConstants {

    public static final Long COURSE_ID = 113L;
    public static final String COURSE_NAME = "Course Name";
    public static final String COURSE_TITLE = "Course Title";
    public static final String COURSE_ABOUT = "About Course";
    public static final Double COURSE_PRICE = 19.99;

    public static final String SECTION_1_NAME = "Section 1";
    public static final Integer SECTION_1_ORDER = 1;
    public static final Course DEFAULT_COURSE;

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
    }


}
