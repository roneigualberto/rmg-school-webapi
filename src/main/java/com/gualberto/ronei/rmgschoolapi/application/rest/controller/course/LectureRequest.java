package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureForm;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.SectionForm;
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
public class LectureRequest {


    @NotNull
    private Long sectionId;

    @NotBlank
    @NotNull
    private String title;

    @NotNull
    private LectureType type;

    @NotNull
    private Integer order;


    public LectureForm toLectureForm() {
        return new LectureForm(sectionId, title, type, order);
    }
}