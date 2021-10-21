package com.gualberto.ronei.rmgschoolapi.domain.shopcart;

import com.gualberto.ronei.rmgschoolapi.domain.course.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopCart {

    private Long userId;

    @Builder.Default
    private List<ShopCartItem> items = new ArrayList<>();

    public ShopCartItem addItem(Course course) {

        ShopCartItem shopCartItem = ShopCartItem.builder()
                .name(course.getName())
                .courseId(course.getId())
                .price(course.getPrice())
                .build();
        items.add(shopCartItem);

        return shopCartItem;

    }

    public void removeItem(Long courseId) {
        items.removeIf((item) -> item.getCourseId().equals(courseId));
    }
}
