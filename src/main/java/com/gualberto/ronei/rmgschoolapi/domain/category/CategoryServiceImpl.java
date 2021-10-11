package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.common.DomainException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryConstants.CATEGORY_NAME_ALREADY_EXISTS;
import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryConstants.CATEGORY_NOT_FOUND;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(CategoryForm categoryForm) {

        String categoryName = categoryForm.getName();
        validName(categoryName);

        Category category = Category.builder()
                .name(categoryForm.getName())
                .build();

        categoryRepository.save(category);

        return category;
    }

    private void validName(Category category, String categoryName) {
        Optional<Category> optCategory = categoryRepository.findByName(categoryName);

        if (optCategory.isPresent()) {
            Category categoryFind = optCategory.get();
            if (category == null || !Objects.equals(categoryFind, category)) {
                throw new DomainException(CATEGORY_NAME_ALREADY_EXISTS);
            }
        }
    }

    private void validName(String categoryName) {
        validName(null, categoryName);
    }

    @Override
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DomainException(CATEGORY_NOT_FOUND));
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Long id, CategoryForm categoryForm) {

        Category category = get(id);

        String categoryName = categoryForm.getName();

        validName(category, categoryName);

        category.setName(categoryName);

        categoryRepository.save(category);

        return category;
    }

    @Override
    public void delete(Long id) {
        Category category = get(id);
        categoryRepository.delete(category);
    }

}
