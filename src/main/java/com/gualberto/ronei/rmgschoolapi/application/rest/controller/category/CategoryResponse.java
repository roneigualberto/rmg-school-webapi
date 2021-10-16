package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = CategoryResponse.COLLECTION_RELATION)
@Builder
public class CategoryResponse {


    public static final String ITEM_RELATION = "category";

    public static final String COLLECTION_RELATION = "categories";

    private Long id;

    private String name;

    public static CategoryResponse fromCategory(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }

    public static List<CategoryResponse> fromCategories(List<Category> categories) {
        return categories.stream().map((CategoryResponse::fromCategory)).collect(Collectors.toList());
    }
}