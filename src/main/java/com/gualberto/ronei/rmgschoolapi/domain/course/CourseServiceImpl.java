package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
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

    private final UserService userService;

    private final CategoryService categoryService;

    private final SectionRepository sectionRepository;

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

        Section section = Section.builder()
                .course(course)
                .name(sectionForm.getName())
                .order(sectionForm.getOrder())
                .build();

        sectionRepository.save(section);


        return section;
    }
}
