package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {


    private final QuizRepository quizRepository;

    private final QuestionRepository questionRepository;

    @Override
    public Quiz create(Long sectionId, QuizForm quizForm) {


        Quiz quiz = Quiz.builder()
                .section(Section.builder().id(sectionId).build())
                .percentageApproval(quizForm.getPercentageApproval())
                .title(quizForm.getTitle())
                .build();

        List<Question> questions = quizForm.getQuestions().stream().map((questionForm) -> {

            Question question = Question.builder()
                    .quiz(quiz)
                    .statement(questionForm.getStatement()).build();

            questionForm.getAlternatives().forEach(question::addAlternative);

            return question;

        }).collect(Collectors.toList());

        questionRepository.saveAll(questions);


        return null;
    }
}
