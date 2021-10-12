package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;


import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubcategoryForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SubCategoryRequest {

    private String name;

    public SubcategoryForm toSubCategoryForm() {
        return new SubcategoryForm(name);
    }
}