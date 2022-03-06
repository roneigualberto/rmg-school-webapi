package com.gualberto.ronei.rmgschoolapi.application.rest.controller.wishlist;

import com.gualberto.ronei.rmgschoolapi.application.rest.controller.course.CourseResponse;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = WishListItemResponse.COLLECTION_RELATION)
@Builder
public class WishListItemResponse {

    public static final String ITEM_RELATION = "wish-list";

    public static final String COLLECTION_RELATION = "wish-lists";

    private Long id;

    private CourseResponse course;
}