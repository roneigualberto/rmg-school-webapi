package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;


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
public class SectionRequest {

    @NotBlank
    @NotNull
    private String name;


    @NotNull
    private Integer order;


}