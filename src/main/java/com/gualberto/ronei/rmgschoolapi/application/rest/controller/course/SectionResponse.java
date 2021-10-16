package com.gualberto.ronei.rmgschoolapi.application.rest.controller.course;

import com.gualberto.ronei.rmgschoolapi.domain.course.section.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Relation(itemRelation = ITEM_RELATION, collectionRelation = SectionResponse.COLLECTION_RELATION)
@SuperBuilder
public class SectionResponse {


    public static final String ITEM_RELATION = "section";

    public static final String COLLECTION_RELATION = "sections";

    private Long id;

    private String name;

    private Integer order;

    public static SectionResponse fromSection(Section section) {
        return new SectionResponse(section.getId(), section.getName(), section.getOrder());
    }

    public static List<SectionResponse> fromSections(List<Section> sections) {
        return sections.stream().map((SectionResponse::fromSection)).collect(Collectors.toList());
    }
}