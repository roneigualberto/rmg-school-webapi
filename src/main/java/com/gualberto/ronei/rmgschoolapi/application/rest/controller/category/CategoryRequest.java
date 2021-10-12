package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;


import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryRequest {

    private String name;

    public CategoryForm toCategoryForm() {
        return new CategoryForm(name);
    }
}