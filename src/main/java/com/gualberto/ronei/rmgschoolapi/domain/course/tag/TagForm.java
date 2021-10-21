package com.gualberto.ronei.rmgschoolapi.domain.course.tag;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TagForm {

    private Long id;

    private String name;
}
