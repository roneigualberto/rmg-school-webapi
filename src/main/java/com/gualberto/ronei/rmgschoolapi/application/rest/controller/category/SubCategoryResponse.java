package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

import static com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryResponse.ITEM_RELATION;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Relation(itemRelation = ITEM_RELATION, collectionRelation = SubCategoryResponse.COLLECTION_RELATION)
@Builder
public class SubCategoryResponse {


    public static final String ITEM_RELATION = "sub-category";

    public static final String COLLECTION_RELATION = "sub-categories";

    private Long id;

    private String name;

    public static SubCategoryResponse fromSubCategory(SubCategory subCategory) {
        return new SubCategoryResponse(subCategory.getId(), subCategory.getName());
    }

    public static List<SubCategoryResponse> fromSubCategories(List<SubCategory> subCategories) {
        return subCategories.stream().map((SubCategoryResponse::fromSubCategory)).collect(Collectors.toList());
    }
}