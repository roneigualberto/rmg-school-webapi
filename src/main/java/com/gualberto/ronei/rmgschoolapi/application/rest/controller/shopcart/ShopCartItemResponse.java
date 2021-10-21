package com.gualberto.ronei.rmgschoolapi.application.rest.controller.shopcart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = "item", collectionRelation = "items")
@Builder
public class ShopCartItemResponse {

    private Long courseId;

    private String name;

    private Double price;
}
