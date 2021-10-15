package com.gualberto.ronei.rmgschoolapi.domain.course.section;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionForm {

    String name;
    Integer order;
}
