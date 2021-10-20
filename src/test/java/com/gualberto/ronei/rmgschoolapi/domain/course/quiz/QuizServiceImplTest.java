package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.SECTION_1;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CourseTestConstants.SECTION_1_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {


    @InjectMocks
    private QuizServiceImpl quizService;

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private QuestionRepository questionRepository;


    private Quiz quizResult;
    private QuizForm quizForm;
    private QuestionForm questionForm;
    private Question questionResult;
    private Quiz quizTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create_shouldCreateQuiz() {
        givenQuizForm();
        whenCalling_SectionRepository_findById();
        whenCalledCreate();
        thenShouldCreateQuiz();
    }

    @Test
    void createQuestion_shouldCreateQuestion() {
        givenQuestionForm();
        givenQuiz();
        whenCalling_QuizRepository_findById();
        whenCalledCreateQuestion();
        thenShouldCreateQuestion();
    }

    private void thenShouldCreateQuiz() {
        assertThat(quizResult.getTitle()).isEqualTo(quizForm.getTitle());
        assertThat(quizResult.getDescription()).isEqualTo(quizForm.getDescription());
        assertThat(quizResult.getPercentageApproval()).isEqualTo(quizForm.getPercentageApproval());
        verify(quizRepository, times(1)).save(any());
    }

    private void thenShouldCreateQuestion() {
        assertThat(questionResult.getStatement()).isEqualTo(questionForm.getStatement());
        assertThat(questionResult.getOrder()).isEqualTo(questionForm.getOrder());
        verify(questionRepository, times(1)).save(any());
    }

    private void givenQuizForm() {
        quizForm = QuizForm.builder()
                .title("Quiz Title")
                .description("Quiz Description")
                .percentageApproval(70.0)
                .build();
    }

    private void givenQuiz() {
        quizTest = Quiz.builder()
                .id(1L)
                .title("Quiz Title")
                .description("Quiz Description")
                .percentageApproval(70.0)
                .build();
    }

    private void givenQuestionForm() {
        questionForm = QuestionForm.builder()
                .order(1)
                .statement("Question 01")
                .build();
    }

    private void whenCalledCreate() {
        quizResult = quizService.create(SECTION_1_ID, quizForm);
    }

    private void whenCalledCreateQuestion() {
        questionResult = quizService.createQuestion(1L, questionForm);
    }

    private void whenCalling_SectionRepository_findById() {
        when(sectionRepository.findById(SECTION_1_ID)).thenReturn(Optional.of(SECTION_1));
    }

    private void whenCalling_QuizRepository_findById() {
        when(quizRepository.findById(1L)).thenReturn(Optional.of(quizTest));
    }
}