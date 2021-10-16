package com.gualberto.ronei.rmgschoolapi.domain.course.lecture;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureForm {

    private Long sectionId;

    private String title;

    private LectureType type;

    private Integer order;
}

