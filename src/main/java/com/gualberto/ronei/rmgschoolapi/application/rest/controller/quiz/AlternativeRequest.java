package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;

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
public class AlternativeRequest {


    @NotBlank
    @NotNull
    private String statement;

    @NotNull
    private Integer order;

    @NotNull
    private Boolean correct;
}
