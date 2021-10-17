package com.gualberto.ronei.rmgschoolapi.domain.course.lecture;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LectureType {

    VIDEO("mp4"),
    PDF("pdf"),
    HTML("html");

    private final String extension;
}
