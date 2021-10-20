package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.List;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Relation(itemRelation = ITEM_RELATION, collectionRelation = QuestionResponse.COLLECTION_RELATION)
@SuperBuilder
public class QuestionResponse {


    public static final String ITEM_RELATION = "question";

    public static final String COLLECTION_RELATION = "questions";

    private Long id;

    private String statement;

    private Integer order;

    @Builder.Default
    private List<AlternativeResponse> alternatives = new ArrayList<>();


}