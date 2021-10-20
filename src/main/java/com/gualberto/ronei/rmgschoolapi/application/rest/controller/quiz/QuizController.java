package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;


import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.CourseRequest;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.CourseResponse;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/quiz")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
@Slf4j
public class QuizController {


    private final QuizService quizService;

    private final QuizMapper quizMapper;


    @Transactional
    @PostMapping()
    public ResponseEntity<EntityModel<QuizResponse>> create(@RequestBody QuizRequest request) {

        QuizForm form = quizMapper.toQuizForm(request);

        Quiz quiz = quizService.create(request.getSectionId(), form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quiz.getId())
                .toUri();

        QuizResponse response = quizMapper.fromQuiz(quiz);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();


        EntityModel<QuizResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Transactional
    @PostMapping("{quizId}/questions")
    public ResponseEntity<EntityModel<QuestionResponse>> create(@PathVariable Long quizId, @RequestBody QuestionRequest request) {

        QuestionForm form = quizMapper.toQuestionForm(request);

        Question question = quizService.createQuestion(quizId, form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(question.getId())
                .toUri();

        QuestionResponse response = quizMapper.fromQuestion(question);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();


        EntityModel<QuestionResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }
}
