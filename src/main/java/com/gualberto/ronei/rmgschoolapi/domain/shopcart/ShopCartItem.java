package com.gualberto.ronei.rmgschoolapi.domain.shopcart;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCartItem {

    private Long courseId;

    private String name;

    private Double price;
}
