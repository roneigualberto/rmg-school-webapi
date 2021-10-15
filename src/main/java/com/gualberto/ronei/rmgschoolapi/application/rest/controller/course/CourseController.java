package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {


    private final CourseService courseService;

    @Transactional
    @PostMapping()
    public ResponseEntity<EntityModel<CourseResponse>> create(@RequestBody CourseRequest request) {

        CourseForm form = request.toCourseForm();

        Course course = courseService.createCourse(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();

        CourseResponse response = CourseResponse.fromCourse(course);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();


        EntityModel<CourseResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }


    @GetMapping("/me")
    public CollectionModel<?> getMyCourses() {

        List<Course> courses = courseService.getMyCourses();

        List<CourseResponse> responseList = CourseResponse.fromCourses(courses);

        Link selfLink = linkTo(methodOn(getClass()).getMyCourses()).withSelfRel();
        Link createLink = linkTo(methodOn(getClass()).create(null)).withRel("create");

        return CollectionModel.of(responseList, selfLink, createLink);
    }
}
