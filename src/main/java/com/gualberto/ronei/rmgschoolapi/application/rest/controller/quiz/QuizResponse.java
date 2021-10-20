package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class QuizResponse {


    private Long id;


    private String title;


    private String description;

    private Double percentageApproval;


    @Builder.Default
    private List<QuestionResponse> questions = new ArrayList<>();


}