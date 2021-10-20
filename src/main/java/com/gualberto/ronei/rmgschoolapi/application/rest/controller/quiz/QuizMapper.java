package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;

import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.*;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Question;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuestionForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Quiz;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuizForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuizMapper {


    QuizResponse fromQuiz(Quiz quiz);

    QuizForm toQuizForm(QuizRequest request);

    QuestionForm toQuestionForm(QuestionRequest questionRequest);

    QuestionResponse fromQuestion(Question question);

}
