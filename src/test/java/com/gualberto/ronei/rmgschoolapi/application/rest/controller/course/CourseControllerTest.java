package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.BaseIntegrationTest;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants;
import com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum.BEGINNER;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.*;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private SectionRepository sectionRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private UserRepository userRepository;


    private CourseRequest courseRequestTest;
    private User userTest;
    private Category categoryTest;
    private Section section1Test;
    private SubCategory subCategoryTest;
    private Course courseTest;
    private SectionRequest sectionRequest;
    private LectureRequest lectureRequest;
    private Lecture lecture1Test;
    private Long courseId;


    @AfterEach
    void tearDown() {

        if (lecture1Test != null) {
            lectureRepository.delete(lecture1Test);
        }

        if (section1Test != null) {
            sectionRepository.delete(section1Test);
        }

        if (courseTest != null) {
            courseTest.getSections().remove(section1Test);
            courseRepository.delete(courseTest);
        }

        if (courseId != null) {
            courseRepository.deleteById(courseId);
        }

        if (subCategoryTest != null) {
            subCategoryRepository.delete(subCategoryTest);
        }

        if (categoryTest != null) {
            categoryRepository.delete(categoryTest);
        }

        if (userTest != null) {
            userRepository.delete(userTest);
        }
    }

    @Test
    @Transactional
    @WithMockUser(username = USER_EMAIL)
    void shouldCreatedCourse() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourseRequest();

        String id = "$.id";

        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(courseRequestTest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(id).isNotEmpty())
                .andExpect(jsonPath("$.name").value(courseRequestTest.getName()))
                .andExpect(jsonPath("$.title").value(courseRequestTest.getTitle()))
                .andExpect(jsonPath("$.about").value(courseRequestTest.getAbout()))
                .andExpect(jsonPath("$.price").value(courseRequestTest.getPrice()))
                .andExpect(jsonPath("$.category.id").value(categoryTest.getId()))
                .andExpect(jsonPath("$.category.name").value(categoryTest.getName()))
                .andExpect(jsonPath("$.subCategory.id").value(subCategoryTest.getId()))
                .andExpect(jsonPath("$.subCategory.name").value(subCategoryTest.getName()))
                .andExpect(jsonPath("$.instructor.id").value(userTest.getId()))
                .andExpect(jsonPath("$.instructor.firstName").value(userTest.getFirstName()))
                .andExpect(jsonPath("$.instructor.lastName").value(userTest.getLastName()))
                .andExpect(jsonPath("$.instructor.email").value(userTest.getEmail()))
                .andExpect(jsonPath("$._links").isMap())
                .andDo((result) -> {
                    var contentMap = objectMapper.readValue(result.getResponse().getContentAsString(), Map.class);
                    courseId = Long.parseLong(contentMap.get("id").toString());

                });


    }

    @Test
    @Transactional
    @WithMockUser(username = USER_EMAIL)
    void shouldAddSection() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSectionRequest();

        mockMvc.perform(post(BASE_URI + "/{courseId}/sections", courseTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(sectionRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(sectionRequest.getName()))
                .andExpect(jsonPath("$.order").value(sectionRequest.getOrder()))
                .andExpect(jsonPath("$._links").isMap());
    }

    @Test
    @Transactional
    @WithMockUser(username = USER_EMAIL)
    void shouldAddLecture() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSection1();
        givenLectureRequest();

        mockMvc.perform(post(BASE_URI + "/{courseId}/lectures", courseTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(lectureRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value(lectureRequest.getTitle()))
                .andExpect(jsonPath("$.type").value(lectureRequest.getType().name()))
                .andExpect(jsonPath("$.order").value(lectureRequest.getOrder()))
                .andExpect(jsonPath("$.section.id").value(section1Test.getId()))
                .andExpect(jsonPath("$._links").isMap());
    }


    @Test
    @Transactional
    @DisplayName("Should return all courses where the logged in user is an instructor")
    @WithMockUser(username = USER_EMAIL)
    void shouldReturnMyCourses() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSection1();

        mockMvc.perform(get(BASE_URI + "/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.courses").isArray())
                .andExpect(jsonPath("$._embedded.courses[0].name").value(courseTest.getName()))
                .andExpect(jsonPath("$._embedded.courses[0].title").value(courseTest.getTitle()))
                .andExpect(jsonPath("$._embedded.courses[0].about").value(courseTest.getAbout()))
                .andExpect(jsonPath("$._embedded.courses[0].price").value(courseTest.getPrice()))
                .andExpect(jsonPath("$._embedded.courses[0].category.id").value(categoryTest.getId()))
                .andExpect(jsonPath("$._embedded.courses[0].category.name").value(categoryTest.getName()))
                .andExpect(jsonPath("$._embedded.courses[0].subCategory.id").value(subCategoryTest.getId()))
                .andExpect(jsonPath("$._embedded.courses[0].subCategory.name").value(subCategoryTest.getName()))
                .andExpect(jsonPath("$._embedded.courses[0].instructor.id").value(userTest.getId()))
                .andExpect(jsonPath("$._embedded.courses[0].instructor.firstName").value(userTest.getFirstName()))
                .andExpect(jsonPath("$._embedded.courses[0].instructor.lastName").value(userTest.getLastName()))
                .andExpect(jsonPath("$._embedded.courses[0].instructor.email").value(userTest.getEmail()))
                .andExpect(jsonPath("$._embedded.courses[0].sections").isArray())
                .andExpect(jsonPath("$._embedded.courses[0].sections").isNotEmpty());
    }

    @Test
    @WithMockUser(username = USER_EMAIL)
    void shouldUploadLectureContent() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSection1();
        givenLecture1();

        String url = BASE_URI + "/{courseId}/lectures/{lectureId}/content";

        MockMultipartFile mockMultipartFile = new MockMultipartFile("content", "some-text.txt", "text/plain", "test data".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart(url, courseTest.getId(), lecture1Test.getId())
                .file(mockMultipartFile)
        ).andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
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

    private void givenSectionRequest() {
        sectionRequest = SectionRequest.builder()
                .name(SECTION_1_NAME)
                .order(SECTION_1_ORDER)
                .build();
    }

    private void givenLectureRequest() {
        lectureRequest = LectureRequest.builder()
                .sectionId(section1Test.getId())
                .title(LECTURE_1_TITLE)
                .order(LECTURE_1_ORDER)
                .type(LectureType.VIDEO)
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

    private void givenSection1() {
        section1Test = CourseTestConstants.SECTION_1;
        section1Test.setCourse(courseTest);
        section1Test = sectionRepository.save(section1Test);
        courseTest.addSection(section1Test);

    }

    private void givenLecture1() {
        lecture1Test = LECTURE_1;
        lecture1Test.setCourse(courseTest);
        lecture1Test.setSection(section1Test);
        lecture1Test = lectureRepository.save(lecture1Test);

    }

    private void givenCourse() {
        courseTest = Course.builder()
                .name(COURSE_NAME)
                .title(COURSE_TITLE)
                .price(COURSE_PRICE)
                .about(COURSE_ABOUT)
                .skillLevel(BEGINNER)
                .category(categoryTest).subCategory(subCategoryTest).instructor(userTest).build();
        courseTest = courseRepository.save(courseTest);
    }


    private void givenSubCategory() {
        subCategoryTest = CategoryTestContants.DEFAULT_SUB_CATEGORY;
        subCategoryTest.setCategory(categoryTest);
        subCategoryTest = subCategoryRepository.save(subCategoryTest);
    }

}