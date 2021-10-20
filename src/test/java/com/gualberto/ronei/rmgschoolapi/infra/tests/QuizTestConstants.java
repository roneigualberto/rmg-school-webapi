package com.gualberto.ronei.rmgschoolapi.infra.tests;

import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Alternative;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Question;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Quiz;

public class QuizTestConstants {

    public static final Long QUIZ_ID = 11L;
    public static final String QUIZ_TITLE = "Quiz Title";
    public static final String QUIZ_DESCRIPTION = "Quiz Description";
    public static final Double QUIZ_PERCENTAGE_APPROVAL = 60.0;
    public static final Quiz QUIZ;


    public static final Long QUESTION_1_ID = 110L;
    public static final String QUESTION_1_STATEMENT = "Question 01";
    public static final Integer QUESTION_1_ORDER = 1;
    public static final Question QUESTION_1;

    public static final Long QUESTION_1_ALT_1_ID = 1101L;
    public static final String QUESTION_1_ALT_1_STATEMENT = "Alternative 1.1";
    public static final Integer QUESTION_1_ALT_1_ORDER = 1;
    public static final Alternative QUESTION_1_ALT_1;

    public static final Long QUESTION_1_ALT_2_ID = 1102L;
    public static final String QUESTION_1_ALT_2_STATEMENT = "Alternative 1.2";
    public static final Integer QUESTION_1_ALT_2_ORDER = 2;
    public static final Alternative QUESTION_1_ALT_2;

    public static final Long QUESTION_1_ALT_3_ID = 1103L;
    public static final String QUESTION_1_ALT_3_STATEMENT = "Alternative 1.3";
    public static final Integer QUESTION_1_ALT_3_ORDER = 3;
    public static final Alternative QUESTION_1_ALT_3;



    public static final Long QUESTION_2_ID = 120L;
    public static final String QUESTION_2_STATEMENT = "Question 02";
    public static final Integer QUESTION_2_ORDER = 2;
    public static final Question QUESTION_2;


    public static final Long QUESTION_2_ALT_1_ID = 1201L;
    public static final String QUESTION_2_ALT_1_STATEMENT = "Alternative 2.1";
    public static final Integer QUESTION_2_ALT_1_ORDER = 1;
    public static final Alternative QUESTION_2_ALT_1;

    public static final Long QUESTION_2_ALT_2_ID = 1202L;
    public static final String QUESTION_2_ALT_2_STATEMENT = "Alternative 2.2";
    public static final Integer QUESTION_2_ALT_2_ORDER = 2;
    public static final Alternative QUESTION_2_ALT_2;

    public static final Long QUESTION_2_ALT_3_ID = 1203L;
    public static final String QUESTION_2_ALT_3_STATEMENT = "Alternative 2.3";
    public static final Integer QUESTION_2_ALT_3_ORDER = 3;
    public static final Alternative QUESTION_2_ALT_3;

    static {

        QUIZ = Quiz.builder()
                .id(QUIZ_ID)
                .title(QUIZ_TITLE)
                .description(QUIZ_DESCRIPTION)
                .percentageApproval(QUIZ_PERCENTAGE_APPROVAL)
                .build();

        QUESTION_1 = Question.builder()
                .quiz(QUIZ)
                .id(QUESTION_1_ID)
                .statement(QUESTION_1_STATEMENT)
                .order(QUESTION_1_ORDER)
                .build();

        QUESTION_1_ALT_1 = Alternative.builder()
                .id(QUESTION_1_ALT_1_ID)
                .question(QUESTION_1)
                .statement(QUESTION_1_ALT_1_STATEMENT)
                .order(QUESTION_1_ALT_1_ORDER)
                .build();
        QUESTION_1.getAlternatives().add(QUESTION_1_ALT_1);

        QUESTION_1_ALT_2 = Alternative.builder()
                .id(QUESTION_1_ALT_2_ID)
                .question(QUESTION_1)
                .statement(QUESTION_1_ALT_2_STATEMENT)
                .order(QUESTION_1_ALT_2_ORDER)
                .build();

        QUESTION_1.getAlternatives().add(QUESTION_1_ALT_2);

        QUESTION_1_ALT_3 = Alternative.builder()
                .id(QUESTION_1_ALT_3_ID)
                .question(QUESTION_1)
                .statement(QUESTION_1_ALT_3_STATEMENT)
                .order(QUESTION_1_ALT_3_ORDER)
                .build();

        QUESTION_1.getAlternatives().add(QUESTION_1_ALT_3);



        QUESTION_2 = Question.builder()
                .quiz(QUIZ)
                .id(QUESTION_2_ID)
                .statement(QUESTION_2_STATEMENT)
                .order(QUESTION_2_ORDER)
                .build();

        QUESTION_2_ALT_1 = Alternative.builder()
                .id(QUESTION_2_ALT_1_ID)
                .question(QUESTION_2)
                .statement(QUESTION_2_ALT_1_STATEMENT)
                .order(QUESTION_2_ALT_1_ORDER)
                .build();
        QUESTION_2.getAlternatives().add(QUESTION_2_ALT_1);

        QUESTION_2_ALT_2 = Alternative.builder()
                .id(QUESTION_2_ALT_2_ID)
                .question(QUESTION_2)
                .statement(QUESTION_2_ALT_2_STATEMENT)
                .order(QUESTION_2_ALT_2_ORDER)
                .build();

        QUESTION_2.getAlternatives().add(QUESTION_2_ALT_2);

        QUESTION_2_ALT_3 = Alternative.builder()
                .id(QUESTION_2_ALT_3_ID)
                .question(QUESTION_2)
                .statement(QUESTION_2_ALT_3_STATEMENT)
                .order(QUESTION_2_ALT_3_ORDER)
                .build();

        QUESTION_2.getAlternatives().add(QUESTION_2_ALT_3);




    }
}
