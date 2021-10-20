package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.QuizTestConstants;
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
import static com.gualberto.ronei.rmgschoolapi.infra.tests.QuizTestConstants.*;
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

    @Test
    void updateQuestion_shouldUpdateQuestion() {
        givenQuestionFormForUpdate();
        givenQuiz();
        whenCalling_QuizRepository_findById();
        whenCalling_QuestionRepository_findById();
        whenCalledUpdateQuestion();
        thenShouldUpdateQuestion();
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
        assertThat(questionResult.getAlternatives().size()).isEqualTo(3);
        verify(questionRepository, times(1)).save(any());
    }

    private void thenShouldUpdateQuestion() {
        assertThat(questionResult.getStatement()).isEqualTo(questionForm.getStatement());
        assertThat(questionResult.getOrder()).isEqualTo(questionForm.getOrder());
        assertThat(questionResult.getAlternatives().size()).isEqualTo(2);
        verify(questionRepository, times(1)).save(any());
    }

    private void givenQuizForm() {
        quizForm = QuizForm.builder()
                .title(QUIZ_TITLE)
                .description(QUIZ_DESCRIPTION)
                .percentageApproval(QUIZ_PERCENTAGE_APPROVAL)
                .build();
    }

    private void givenQuiz() {
        quizTest = QUIZ;
    }

    private void givenQuestionForm() {
        questionForm = QuestionForm.builder()
                .order(QUESTION_1_ORDER)
                .statement(QUESTION_1_STATEMENT)
                .build();

        AlternativeForm alt1 = AlternativeForm.builder().order(QUESTION_1_ALT_1_ORDER)
                .statement(QUESTION_1_ALT_1_STATEMENT)
                .build();

        questionForm.getAlternatives().add(alt1);

        AlternativeForm alt2 = AlternativeForm.builder().order(QUESTION_1_ALT_2_ORDER)
                .statement(QUESTION_1_ALT_2_STATEMENT)
                .build();

        questionForm.getAlternatives().add(alt2);

        AlternativeForm alt3 = AlternativeForm.builder().order(QUESTION_1_ALT_3_ORDER)
                .statement(QUESTION_1_ALT_3_STATEMENT)
                .build();

        questionForm.getAlternatives().add(alt3);

    }

    private void givenQuestionFormForUpdate() {
        questionForm = QuestionForm.builder()
                .order(QUESTION_1_ORDER)
                .statement(QUESTION_1_STATEMENT)
                .build();

        AlternativeForm alt1 = AlternativeForm.builder()
                .id(QUESTION_1_ALT_1_ID)
                .order(QUESTION_1_ALT_1_ORDER)
                .statement(QUESTION_1_ALT_1_STATEMENT)
                .build();

        questionForm.getAlternatives().add(alt1);

        AlternativeForm alt2 = AlternativeForm.builder().order(QUESTION_1_ALT_2_ORDER)
                .statement(QUESTION_1_ALT_2_STATEMENT)
                .build();

        questionForm.getAlternatives().add(alt2);

    }


    private void whenCalledCreate() {
        quizResult = quizService.create(SECTION_1_ID, quizForm);
    }

    private void whenCalledCreateQuestion() {
        questionResult = quizService.createQuestion(QUIZ_ID, questionForm);
    }

    private void whenCalledUpdateQuestion() {
        questionResult = quizService.updateQuestion(QUIZ_ID, QUESTION_1_ID, questionForm);
    }

    private void whenCalling_SectionRepository_findById() {
        when(sectionRepository.findById(SECTION_1_ID)).thenReturn(Optional.of(SECTION_1));
    }

    private void whenCalling_QuizRepository_findById() {
        when(quizRepository.findById(QUIZ_ID)).thenReturn(Optional.of(quizTest));
    }

    private void whenCalling_QuestionRepository_findById() {
        when(questionRepository.findById(QUESTION_1_ID)).thenReturn(Optional.of(QUESTION_1));
    }
}