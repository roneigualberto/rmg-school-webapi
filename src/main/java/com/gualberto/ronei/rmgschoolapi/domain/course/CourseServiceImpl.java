package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureRepository;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionRepository;
import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.user.LoggedUserContext;
import com.gualberto.ronei.rmgschoolapi.domain.user.User;
import com.gualberto.ronei.rmgschoolapi.domain.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {


    private final CourseRepository courseRepository;

    private final CategoryService categoryService;

    private final SectionRepository sectionRepository;

    private final LectureRepository lectureRepository;

    private LoggedUserContext loggedUserContext;


    @Override
    public Course createCourse(CourseForm courseForm) {

        User instrutor = loggedUserContext.getLoggedUser();
        Category category = categoryService.get(courseForm.getCategoryId());
        SubCategory subCategory = categoryService.getSubCategory(courseForm.getSubCategoryId());

        Course course = Course.builder()
                .name(courseForm.getName())
                .title(courseForm.getTitle())
                .about(courseForm.getAbout())
                .instructor(instrutor)
                .category(category)
                .subCategory(subCategory)
                .skillLevel(courseForm.getSkillLevel())
                .price(courseForm.getPrice())
                .build();

        courseRepository.save(course);

        return course;
    }

    @Override
    public List<Course> getMyCourses() {
        User instrutor = loggedUserContext.getLoggedUser();
        return courseRepository.findByInstructor(instrutor);
    }

    @Override
    public Section addSection(Long courseId, SectionForm sectionForm) {
        User instrutor = loggedUserContext.getLoggedUser();
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new DomainException("Course not found"));

        if (!course.getInstructor().equals(instrutor)) {
            throw new DomainException("Instructor does not have this course");
        }

        boolean orderAlreadyExists = sectionRepository.existsByCourseAndOrder(course, sectionForm.getOrder());

        if (orderAlreadyExists) {
            throw new DomainException("Order already exists");
        }

        Section section = course.createSection(sectionForm.getName(), sectionForm.getOrder());

        sectionRepository.save(section);

        return section;
    }

    @Override
    public Lecture addLecture(Long courseId, LectureForm lectureForm) {

        Long sectionId = lectureForm.getSectionId();

        User instrutor = loggedUserContext.getLoggedUser();
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new DomainException("Course not found"));
        Section section = sectionRepository.findById(sectionId).orElseThrow(() -> new DomainException("Section not found"));

        if (!course.getInstructor().equals(instrutor)) {
            throw new DomainException("Instructor does not have this course");
        }

        if (!course.hasSection(section)) {
            throw new DomainException("Course does not have this section");
        }

        boolean orderAlreadyExists = lectureRepository.existsBySectionAndOrder(section, lectureForm.getOrder());

        if (orderAlreadyExists) {
            throw new DomainException("Order already exists");
        }

        Lecture lecture = Lecture.builder()
                .order(lectureForm.getOrder())
                .title(lectureForm.getTitle())
                .type(lectureForm.getType())
                .build();

        section.addLecture(lecture);

        lectureRepository.save(lecture);

        return lecture;
    }
}
