package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.common.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.common.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private MessageService messageService;

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
                throw messageService.toThrowable(CategoryMessageCodeEnum.CATEGORY_NOT_FOUND, DomainException::new);
            }
        }
    }

    private void validName(String categoryName) {
        validName(null, categoryName);
    }

    @Override
    public Category get(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        messageService.toThrowable(CategoryMessageCodeEnum.CATEGORY_NOT_FOUND, DomainException::new)
                );
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
