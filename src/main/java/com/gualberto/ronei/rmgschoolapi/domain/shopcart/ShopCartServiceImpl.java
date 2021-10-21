package com.gualberto.ronei.rmgschoolapi.domain.shopcart;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import com.gualberto.ronei.rmgschoolapi.domain.course.CourseService;
import com.gualberto.ronei.rmgschoolapi.domain.shared.cache.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ShopCartServiceImpl implements ShopCartService {

    private final CourseService courseService;

    private final CacheService cacheService;

    private final ObjectMapper objectMapper;

    @Override
    public Optional<ShopCart> get(Long userId) {
        try {
            String shopCartJson = cacheService.get("shop-cart:" + userId);
            ShopCart shopCart = null;
            if (shopCartJson != null) {
                shopCart = objectMapper.readValue(shopCartJson, ShopCart.class);
            }
            return Optional.ofNullable(shopCart);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Invalid format");
        }
    }

    @Override
    public ShopCartItem addItem(Long userId, Long courseId) {

        try {

            Course course = courseService.get(courseId);

            ShopCart shopCart = get(userId).orElse(new ShopCart());
            shopCart.setUserId(userId);
            ShopCartItem shopCartItem = shopCart.addItem(course);
            saveShopCart(userId, shopCart);

            return shopCartItem;
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Invalid format");
        }
    }

    private void saveShopCart(Long userId, ShopCart shopCart) {
        try {
            String shopCartJson = objectMapper.writeValueAsString(shopCart);
            cacheService.put("shop-cart:" + userId, shopCartJson);
        } catch (Exception ex) {
            throw new RuntimeException("Error to persist");
        }

    }

    @Override
    public void deleteItem(Long userId, Long courseId) {

        Optional<ShopCart> optShopCart = get(userId);

        optShopCart.ifPresent((shopCart) -> {
            shopCart.removeItem(courseId);
            saveShopCart(userId, shopCart);
        });


    }
}
