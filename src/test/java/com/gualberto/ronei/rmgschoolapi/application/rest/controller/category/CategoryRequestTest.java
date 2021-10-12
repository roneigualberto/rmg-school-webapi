package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;

import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryRequestTest {

    @Test
    void toCategoryForm_shouldCreateCategoryForm() {

        CategoryRequest request = CategoryRequest.builder().name(CategoryTestContants.CATEGORY_NAME).build();

        CategoryForm categoryForm = request.toCategoryForm();

        assertThat(categoryForm.getName()).isEqualTo(request.getName());
    }
}