package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuestionRequest {

    @NotBlank
    @NotNull
    private String statement;


    @NotNull
    private Integer order;


    @Builder.Default
    @Valid
    private List<AlternativeRequest> alternatives = new ArrayList<>();


}