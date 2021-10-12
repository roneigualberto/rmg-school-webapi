package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;


import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CategoryRequest {

    @NotBlank
    @NotNull
    private String name;

    public CategoryForm toCategoryForm() {
        return new CategoryForm(name);
    }
}