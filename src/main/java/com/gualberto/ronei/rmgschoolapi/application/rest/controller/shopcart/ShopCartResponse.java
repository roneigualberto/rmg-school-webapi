package com.gualberto.ronei.rmgschoolapi.application.rest.controller.shopcart;

import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = "shop-cart", collectionRelation = "shop-cart")
@Builder
public class ShopCartResponse {

    private Long userId;

    @Builder.Default
    private List<ShopCartItem> items = new ArrayList<>();

}
