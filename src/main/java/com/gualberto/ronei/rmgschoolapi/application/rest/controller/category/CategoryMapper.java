package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;

import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CategoryMapper {


    CategoryResponse fromCategory(Category category);

    List<CategoryResponse> fromCategories(List<Category> categories);

    CategoryForm toCategoryForm(CategoryRequest categoryRequest);
}
