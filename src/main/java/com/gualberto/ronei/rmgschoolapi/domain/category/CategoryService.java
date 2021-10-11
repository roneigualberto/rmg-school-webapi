package com.gualberto.ronei.rmgschoolapi.domain.category;

import java.util.List;

public interface CategoryService {

    Category create(CategoryForm category);

    Category get(Long id);

    List<Category> findAll();

    Category update(Long id, CategoryForm category);

    void delete(Long id);


}
