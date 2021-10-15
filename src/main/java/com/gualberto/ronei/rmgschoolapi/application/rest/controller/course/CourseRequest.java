package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.CourseForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.SkillLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseRequest {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String title;

    private String about;

    @NotBlank
    @NotNull
    private Long categoryId;

    @NotBlank
    @NotNull
    private Long subCategoryId;

    @NotNull
    private SkillLevelEnum skillLevel;


    @NotNull
    private Double price;

    public CourseForm toCourseForm() {
        return new CourseForm(name, title, about, categoryId, subCategoryId, skillLevel, price);
    }
}