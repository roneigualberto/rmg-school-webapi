package com.gualberto.ronei.rmgschoolapi.domain.category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryForm category);

    Category get(Long id);

    List<Category> findAll();

    Category update(Long id, CategoryForm category);

    void delete(Long id);

    SubCategory createSubCategory(Long categoryId, SubcategoryForm subcategoryForm);


    SubCategory updateSubCategory(Long categoryId, Long subCategoryId, SubcategoryForm subcategoryForm);

    void deleteSubCategory(Long categoryId, Long subCategoryId);
}
