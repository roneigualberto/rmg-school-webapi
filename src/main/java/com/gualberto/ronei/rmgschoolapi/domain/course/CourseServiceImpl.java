package com.gualberto.ronei.rmgschoolapi.domain.course;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
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


    @Override
    public Course createCourse(Long instructorId, CourseForm courseForm) {

        User instrutor = userService.get(instructorId);
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
    public List<Course> findByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }
}
