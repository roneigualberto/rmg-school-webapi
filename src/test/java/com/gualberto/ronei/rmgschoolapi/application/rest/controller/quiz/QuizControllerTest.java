package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuestionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Quiz;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuizRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum.BEGINNER;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.*;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.COURSE_ABOUT;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.QuizTestConstants.*;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.UserTestConstants.USER_EMAIL;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuizControllerTest extends BaseIntegrationTest {


    public static final String BASE_URI = "/api/v1/quiz";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionRepository questionRepository;


    private Category categoryTest;

    private SubCategory subCategoryTest;
    private Course courseTest;
    private User userTest;
    private Section section1Test;
    private QuizRequest quizRequest;
    private Long quizId;
    private Quiz quizTest;
    private QuestionRequest questionRequest;
    private Long questionId;


    @AfterEach
    void tearDown() {


        if (section1Test != null) {
            sectionRepository.delete(section1Test);
        }

        if (courseTest != null) {
            courseTest.getSections().remove(section1Test);
            courseRepository.delete(courseTest);
        }

        if (quizId != null) {
            quizRepository.deleteById(quizId);
        }

        if (questionId != null) {
            questionRepository.deleteById(questionId);
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
    void shouldCreatedQuiz() throws Exception {
        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSection1();
        givenQuizRequest();

        String id = "$.id";

        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(quizRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(id).isNotEmpty())
                .andExpect(jsonPath("$.title").value(quizRequest.getTitle()))
                .andExpect(jsonPath("$.description").value(quizRequest.getDescription()))
                .andExpect(jsonPath("$.percentageApproval").value(quizRequest.getPercentageApproval()))
                .andExpect(jsonPath("$._links").isMap())
                .andDo((result) -> {
                    var contentMap = objectMapper.readValue(result.getResponse().getContentAsString(), Map.class);
                    quizId = Long.parseLong(contentMap.get("id").toString());

                });
    }


    @Test
    @Transactional
    @WithMockUser(username = USER_EMAIL)
    void shouldCreatedQuestion() throws Exception {


        givenUser();
        givenCategory();
        givenSubCategory();
        givenCourse();
        givenSection1();
        givenQuiz();

        givenQuestionRequest();

        String id = "$.id";

        mockMvc.perform(post(BASE_URI + "/{quizId}/questions", quizTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(questionRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(id).isNotEmpty())
                .andExpect(jsonPath("$.statement").value(questionRequest.getStatement()))
                .andExpect(jsonPath("$.order").value(questionRequest.getOrder()))
                .andExpect(jsonPath("$.alternatives", hasSize(3)))
                .andExpect(jsonPath("$._links").isMap())
                .andDo((result) -> {
                    var contentMap = objectMapper.readValue(result.getResponse().getContentAsString(), Map.class);
                    questionId = Long.parseLong(contentMap.get("id").toString());

                });
    }

    private void givenQuizRequest() {

        quizRequest = QuizRequest.builder()
                .sectionId(section1Test.getId())
                .title(QUIZ_TITLE)
                .description(QUIZ_DESCRIPTION)
                .percentageApproval(QUIZ_PERCENTAGE_APPROVAL)
                .build();

    }

    private void givenQuestionRequest() {

        questionRequest = QuestionRequest.builder()
                .statement(QUESTION_1_STATEMENT)
                .order(QUESTION_1_ORDER)
                .build();

        AlternativeRequest alt1 = AlternativeRequest.builder()
                .statement(QUESTION_1_ALT_1_STATEMENT)
                .order(QUESTION_1_ALT_1_ORDER)
                .correct(true)
                .build();
        questionRequest.getAlternatives().add(alt1);


        AlternativeRequest alt2 = AlternativeRequest.builder()
                .statement(QUESTION_1_ALT_2_STATEMENT)
                .order(QUESTION_1_ALT_2_ORDER)
                .build();
        questionRequest.getAlternatives().add(alt2);

        AlternativeRequest alt3 = AlternativeRequest.builder()
                .statement(QUESTION_1_ALT_3_STATEMENT)
                .order(QUESTION_1_ALT_3_ORDER)
                .build();
        questionRequest.getAlternatives().add(alt3);

    }


    private void givenQuiz() {

        quizTest = Quiz.builder()
                .section(section1Test)
                .title(QUIZ_TITLE)
                .description(QUIZ_DESCRIPTION)
                .percentageApproval(QUIZ_PERCENTAGE_APPROVAL)
                .build();

        quizTest = quizRepository.save(quizTest);

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
        subCategoryTest.setCategory(categoryTest);
        subCategoryTest = subCategoryRepository.save(subCategoryTest);
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


    private void givenSection1() {
        section1Test = CourseTestConstants.SECTION_1;
        section1Test.setCourse(courseTest);
        section1Test = sectionRepository.save(section1Test);
        courseTest.addSection(section1Test);

    }

}