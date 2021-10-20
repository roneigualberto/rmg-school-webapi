package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionTest {

    private Question questionTest;
    private AlternativeForm alternativeForm1;
    private Alternative alternative1Test;

    @Test
    void addAlternative_shouldAddAlternative() {

        givenQuestion();
        givenAlternative1Form(null, false);

        questionTest.addAlternative(alternativeForm1);

        assertThat(questionTest.getAlternatives()).isNotEmpty();
        assertThat(questionTest.getCorrect()).isNull();

    }

    @Test
    void addAlternative_shouldAddAlternativeCorrect() {

        givenQuestion();
        givenAlternative1Form(null, true);

        questionTest.addAlternative(alternativeForm1);

        assertThat(questionTest.getAlternatives()).isNotEmpty();
        assertThat(questionTest.getCorrect()).isNotNull();

    }

    @Test
    void updateAlternative_shouldUpdateAlternativeCorrect() {

        givenQuestion();
        givenAlternative1Form(1L, true);
        givenAlternative1();

        questionTest.updateAlternative(alternativeForm1);

        assertThat(questionTest.getAlternatives()).isNotEmpty();
        assertThat(questionTest.getCorrect()).isNotNull();

    }

    @Test
    void updateAlternative_shouldNotUpdateAlternative() {
        givenQuestion();
        givenAlternative1Form(1L, true);
        assertThrows(DomainException.class, ()-> questionTest.updateAlternative(alternativeForm1));
    }

    private void givenAlternative1Form(Long id, boolean correct) {
        alternativeForm1 = AlternativeForm.builder()
                .id(id)
                .statement("Alternative 01")
                .order(1)
                .correct(correct)
                .build();
    }

    private void givenQuestion() {
        questionTest = Question.builder().order(1)
                .statement("Question 01")
                .build();
    }


    public void givenAlternative1() {
        alternative1Test = Alternative.builder()
                .id(1L)
                .statement("Alternative")
                .order(1)
                .question(questionTest).build();

        questionTest.getAlternatives().add(alternative1Test);
    }
    @Test
    void updateAlternatives() {
    }

    @Test
    void updateAlternative() {
    }
}