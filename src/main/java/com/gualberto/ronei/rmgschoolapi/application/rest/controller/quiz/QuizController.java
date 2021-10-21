package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;


import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Question;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuestionForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.Quiz;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuizForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.quiz.QuizService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @Transactional
    @GetMapping("section/{sectionId}")
    public ResponseEntity<EntityModel<QuizResponse>> getQuiz(@PathVariable Long sectionId) {

        Quiz quiz = quizService.findBySectionId(sectionId);

        QuizResponse response = quizMapper.fromQuiz(quiz);

        Link selfLink = linkTo(methodOn(getClass()).getQuiz(sectionId)).withSelfRel();

        EntityModel<QuizResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.ok(entityModel);
    }

}
