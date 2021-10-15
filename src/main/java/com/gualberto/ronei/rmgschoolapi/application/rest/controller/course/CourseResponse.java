package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.SubCategoryResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.user.UserResponse;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.fromCategory;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.SubCategoryResponse.fromSubCategory;
import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.user.UserResponse.fromUser;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = CourseResponse.COLLECTION_RELATION)
@Builder
public class CourseResponse {


    public static final String ITEM_RELATION = "course";

    public static final String COLLECTION_RELATION = "courses";

    private Long id;

    private String name;

    private String title;

    private String about;

    private CategoryResponse category;

    private SubCategoryResponse subCategory;

    private UserResponse instructor;

    private SkillLevelEnum skillLevel;

    private Double price;


    public static CourseResponse fromCourse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .title(course.getTitle())
                .about(course.getAbout())
                .category(fromCategory(course.getCategory()))
                .subCategory(fromSubCategory(course.getSubCategory()))
                .instructor(fromUser(course.getInstructor()))
                .price(course.getPrice())
                .build();
    }

    public static List<CourseResponse> fromCourses(List<Course> courses) {
        return courses.stream().map((CourseResponse::fromCourse)).collect(Collectors.toList());
    }
}