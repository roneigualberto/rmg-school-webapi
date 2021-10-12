package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.OTHER_CATEGORY_ID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryResponseTest {


    private Category category;

    private List<Category> categories;


    @BeforeEach
    void setUp() {

        category = Category.builder()
                .id(CATEGORY_ID)
                .name(CategoryTestContants.CATEGORY_NAME)
                .build();

        Category secondCategory = Category.builder()
                .id(OTHER_CATEGORY_ID)
                .name(CategoryTestContants.OTHER_CATEGORY_NAME)
                .build();

        categories = List.of(category, secondCategory);


    }

    @Test
    void fromCategory_shouldBuildResponse() {

        CategoryResponse categoryResponse = CategoryResponse.fromCategory(category);

        assertThat(categoryResponse.getName()).isEqualTo(category.getName());
        assertThat(categoryResponse.getId()).isEqualTo(category.getId());
    }

    @Test
    void fromCategories_shouldBuildResponse() {

        List<CategoryResponse> categoryResponses = CategoryResponse.fromCategories(categories);

        assertThat(categoryResponses.size()).isEqualTo(2);
    }
}