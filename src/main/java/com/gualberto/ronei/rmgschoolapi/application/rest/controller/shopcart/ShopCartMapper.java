package com.gualberto.ronei.rmgschoolapi.application.rest.controller.shopcart;

import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCart;
import com.gualberto.ronei.rmgschoolapi.domain.shopcart.ShopCartItem;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ShopCartMapper {

    ShopCartItemResponse fromShopCartItem(ShopCartItem shopCartItem);

    ShopCartResponse fromShopCart(ShopCart shopCart);
}
