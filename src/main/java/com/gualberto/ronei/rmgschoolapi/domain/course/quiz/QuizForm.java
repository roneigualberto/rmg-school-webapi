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
public class QuizForm {

    private String title;

    private String description;

    private Double percentageApproval;


}
