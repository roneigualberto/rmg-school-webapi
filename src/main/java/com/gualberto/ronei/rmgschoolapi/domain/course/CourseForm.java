package com.gualberto.ronei.rmgschoolapi.domain.course;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CourseForm {

    private String name;

    private String title;

    private String about;

    private Long categoryId;

    private Long subCategoryId;

    private SkillLevelEnum skillLevel;

    private Double price;
}
