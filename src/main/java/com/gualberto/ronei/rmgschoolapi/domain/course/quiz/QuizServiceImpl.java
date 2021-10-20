package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {


    private final QuizRepository quizRepository;

    private final SectionRepository sectionRepository;

    private final QuestionRepository questionRepository;

    @Override
    public Quiz create(Long sectionId, QuizForm quizForm) {

        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new DomainException("Section not found"));

        Quiz quiz = Quiz.builder()
                .section(section)
                .description(quizForm.getDescription())
                .percentageApproval(quizForm.getPercentageApproval())
                .title(quizForm.getTitle())
                .build();

        quizRepository.save(quiz);

        return quiz;
    }

    @Override
    public Question createQuestion(Long quizId, QuestionForm questionForm) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new DomainException("Quiz not found"));

        Question question = Question.builder().quiz(quiz)
                .statement(questionForm.getStatement())
                .order(questionForm.getOrder())
                .build();

        questionForm.getAlternatives().forEach(question::addAlternative);

        questionRepository.save(question);

        return question;
    }

    @Override
    public Question updateQuestion(Long quizId,Long questionId,QuestionForm questionForm) {

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new DomainException("Quiz not found"));
        Question question =  questionRepository.findById(questionId).orElseThrow(() -> new DomainException("Question not found"));

        question.setQuiz(quiz);
        question.setStatement(questionForm.getStatement());
        question.setOrder(questionForm.getOrder());
        question.updateAlternatives(questionForm.getAlternatives());

        questionRepository.save(question);

        return question;
    }
}
