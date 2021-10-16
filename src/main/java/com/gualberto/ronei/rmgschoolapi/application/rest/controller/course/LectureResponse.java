package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.Lecture;
import com.gualberto.ronei.rmgschoolapi.domain.course.lecture.LectureType;
import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = LectureResponse.COLLECTION_RELATION)
@Builder
public class LectureResponse {


    public static final String ITEM_RELATION = "lecture";

    public static final String COLLECTION_RELATION = "lectures";

    private Long id;

    private String title;

    private SectionResponse section;

    private Integer order;

    private LectureType type;

    public static LectureResponse fromLecture(Lecture lecture) {
        return LectureResponse.builder()
                .id(lecture.getId())
                .section(SectionResponse.fromSection(lecture.getSection()))
                .title(lecture.getTitle())
                .order(lecture.getOrder())
                .type(lecture.getType())
                .build();
    }

    public static List<LectureResponse> fromSections(List<Lecture> lectures) {
        return lectures.stream().map((LectureResponse::fromLecture)).collect(Collectors.toList());
    }
}