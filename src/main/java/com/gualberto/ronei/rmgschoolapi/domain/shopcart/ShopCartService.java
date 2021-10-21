package com.gualberto.ronei.rmgschoolapi.domain.shopcart;

import java.util.Optional;

public interface ShopCartService {


    Optional<ShopCart> get(Long userId);


    ShopCartItem addItem(Long userId, Long courseId);

    void deleteItem(Long userId, Long courseId);

}
