package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
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

    @Transactional
    @PostMapping("{courseId}/sections")
    public ResponseEntity<EntityModel<SectionResponse>> addSection(@PathVariable Long courseId, @RequestBody SectionRequest request) {

        SectionForm form = request.toSectionForm();

        Section section = courseService.addSection(courseId, form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(section.getId())
                .toUri();

        SectionResponse response = SectionResponse.fromSection(section);

        Link selfLink = linkTo(methodOn(getClass()).addSection(courseId, null)).withSelfRel();


        EntityModel<SectionResponse> entityModel = EntityModel.of(response, selfLink);

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
