package com.gualberto.ronei.rmgschoolapi.domain.course.quiz;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AlternativeForm {


    private String statement;

    private boolean correct;
}
