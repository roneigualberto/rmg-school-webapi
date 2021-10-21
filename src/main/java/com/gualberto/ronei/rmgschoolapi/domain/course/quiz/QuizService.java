package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

public interface QuizService {

    Quiz create(Long sectionId, QuizForm quizForm);

    Question createQuestion(Long quizId, QuestionForm questionForm);

    Question updateQuestion(Long quizId, Long questionId, QuestionForm questionForm);

    Quiz findBySectionId(Long sectionId);
}
