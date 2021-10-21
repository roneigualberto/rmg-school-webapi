package com.gualberto.ronei.rmgschoolapi.domain.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.tag.TagForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @Builder.Default
    private List<TagForm> tags = new ArrayList<>();

}
