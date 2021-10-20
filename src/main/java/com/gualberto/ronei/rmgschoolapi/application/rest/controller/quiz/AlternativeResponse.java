package com.gualberto.ronei.rmgschoolapi.application.rest.controller.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.server.core.Relation;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Relation(itemRelation = ITEM_RELATION, collectionRelation = AlternativeResponse.COLLECTION_RELATION)
@SuperBuilder
public class AlternativeResponse {


    public static final String ITEM_RELATION = "alternative";

    public static final String COLLECTION_RELATION = "alternatives";

    private Long id;

    private String statement;

    private Integer order;


}