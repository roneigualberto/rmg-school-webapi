package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import static com.gualberto.ronei.rmgschoolapi.infra.constants.SecurityConstants.SCHEME_BEARER_AUTH;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
@SecurityRequirement(name = SCHEME_BEARER_AUTH)
public class CourseController {


    private final CourseService courseService;

    private final CourseMapper courseMapper;

    @Transactional
    @PostMapping()
    public ResponseEntity<EntityModel<CourseResponse>> create(@RequestBody CourseRequest request) {

        CourseForm form = courseMapper.toCourseForm(request);

        Course course = courseService.createCourse(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();

        CourseResponse response = courseMapper.fromCourse(course);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();


        EntityModel<CourseResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Transactional
    @PostMapping("{courseId}/sections")
    public ResponseEntity<EntityModel<SectionResponse>> addSection(@PathVariable Long courseId, @RequestBody SectionRequest request) {

        SectionForm form = courseMapper.toSectionForm(request);

        Section section = courseService.addSection(courseId, form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(section.getId())
                .toUri();

        SectionResponse response = courseMapper.fromSection(section);

        Link selfLink = linkTo(methodOn(getClass()).addSection(courseId, null)).withSelfRel();


        EntityModel<SectionResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Transactional
    @PostMapping("{courseId}/lectures")
    public ResponseEntity<EntityModel<LectureResponse>> addLecture(@PathVariable Long courseId, @RequestBody LectureRequest request) {

        LectureForm form = courseMapper.toLectureForm(request);

        Lecture lecture = courseService.addLecture(courseId, form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lecture.getId())
                .toUri();

        LectureResponse response = courseMapper.fromLecture(lecture);

        Link selfLink = linkTo(methodOn(getClass()).addLecture(courseId, null)).withSelfRel();


        EntityModel<LectureResponse> entityModel = EntityModel.of(response, selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }


    @Transactional
    @PostMapping("{courseId}/lectures/{lectureId}/content")
    public ResponseEntity<?> storeContent(@PathVariable Long courseId, @PathVariable Long lectureId, @RequestParam("content") MultipartFile content) throws IOException {

        courseService.storeLectureContent(courseId, lectureId, content.getInputStream());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.ok().build();
    }


    @Transactional(readOnly = true)
    @GetMapping("{courseId}/lectures/{lectureId}/content")
    public ResponseEntity<?> getContent(@PathVariable Long courseId, @PathVariable Long lectureId) {

        InputStream content = courseService.getLectureContent(courseId, lectureId);

        return ResponseEntity.ok().body(content);
    }


    @GetMapping("/me")
    public CollectionModel<?> getMyCourses() {

        List<Course> courses = courseService.getMyCourses();

        List<CourseResponse> responseList = courseMapper.fromCourses(courses);

        Link selfLink = linkTo(methodOn(getClass()).getMyCourses()).withSelfRel();
        Link createLink = linkTo(methodOn(getClass()).create(null)).withRel("create");

        return CollectionModel.of(responseList, selfLink, createLink);
    }
}
