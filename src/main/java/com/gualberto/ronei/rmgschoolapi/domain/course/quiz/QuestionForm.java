package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;

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
public class QuestionForm {

    private String statement;

    private Integer order;

    @Builder.Default
    private List<AlternativeForm> alternatives = new ArrayList<>();
}
