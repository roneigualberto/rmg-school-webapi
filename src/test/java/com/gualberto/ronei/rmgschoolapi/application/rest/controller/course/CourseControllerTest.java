package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseRepository;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.BaseIntegrationTest;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum.BEGINNER;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_ABOUT;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_PRICE;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestContants.COURSE_TITLE;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseControllerTest extends BaseIntegrationTest {


    public static final String BASE_URI = "/api/v1/courses";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private UserRepository userRepository;


    private CourseRequest courseRequestTest;
    private User userTest;
    private Category categoryTest;
    private SubCategory subCategoryTest;


    @Test
    @Transactional
    @WithMockUser(username = USER_EMAIL)
    void shouldCreatedCourse() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourseRequest();

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(courseRequestTest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(courseRequestTest.getName()))
                .andExpect(jsonPath("$._links").isMap());
    }


    private void givenCourseRequest() {
        courseRequestTest = CourseRequest.builder()
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .price(COURSE_PRICE)
                .skillLevel(BEGINNER)
                .about(COURSE_ABOUT)
                .categoryId(categoryTest.getId())
                .subCategoryId(subCategoryTest.getId())
                .build();
    }

    private void givenUser() {
        userTest = UserTestConstants.DEFAULT_USER;
        userTest = userRepository.save(userTest);
    }

    private void givenCategory() {
        categoryTest = CategoryTestContants.DEFAULT_CATEGORY;
        categoryTest = categoryRepository.save(categoryTest);
    }

    private void givenSubCategory() {
        subCategoryTest = CategoryTestContants.DEFAULT_SUB_CATEGORY;
        subCategoryTest = subCategoryRepository.save(subCategoryTest);
    }

}