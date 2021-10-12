package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

;import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.*;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final SubCategoryRepository subCategoryRepository;

    private final MessageService messageService;

    @Override
    public Category create(CategoryForm categoryForm) {

        final String categoryName = categoryForm.getName();
        validCategoryName(categoryName);

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryRepository.save(category);

        return category;
    }

    @Override
    public Category get(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        messageService.toThrowable(CATEGORY_NOT_FOUND, (msg) -> new DomainException(CATEGORY_NOT_FOUND, msg))
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

        validCategoryName(categoryName, category);

        category.setName(categoryName);

        categoryRepository.save(category);

        return category;
    }

    @Override
    public void delete(Long id) {
        final Category category = get(id);
        categoryRepository.delete(category);
    }

    @Override
    public SubCategory createSubCategory(Long categoryId, SubcategoryForm subcategoryForm) {

        final Category category = get(categoryId);
        final String subcategoryName = subcategoryForm.getName();

        validateSubCategoryName(category, subcategoryName);

        final SubCategory subCategory = SubCategory.builder()
                .category(category)
                .name(subcategoryName)
                .build();

        subCategoryRepository.save(subCategory);

        return subCategory;
    }

    @Override
    public List<SubCategory> getSubCategories(Long categoryId) {
        return subCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public SubCategory updateSubCategory(Long categoryId, Long subCategoryId, SubcategoryForm subcategoryForm) {

        final Category category = get(categoryId);
        final SubCategory subCategory = getSubCategory(subCategoryId);
        final String subcategoryName = subcategoryForm.getName();

        if (!subCategory.belongsTo(category)) {
            messageService.toThrowable(SUB_CATEGORY_NOT_FOUND, DomainException::new);
        }

        validateSubCategoryName(category, subcategoryName, subCategory);

        subCategory.setCategory(category);
        subCategory.setName(subcategoryName);

        subCategoryRepository.save(subCategory);

        return subCategory;
    }

    @Override
    public void deleteSubCategory(Long categoryId, Long subCategoryId) {

        final Category category = get(categoryId);
        final SubCategory subCategory = getSubCategory(subCategoryId);

        if (!subCategory.belongsTo(category)) {
            messageService.toThrowable(SUB_CATEGORY_NOT_FOUND, DomainException::new);
        }

        subCategoryRepository.delete(subCategory);

    }

    private SubCategory getSubCategory(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() ->
                        messageService.toThrowable(SUB_CATEGORY_NOT_FOUND, DomainException::new)
                );
    }

    private void validateSubCategoryName(Category category, String subcategoryName, SubCategory subCategory) {
        Optional<SubCategory> optSubCategory = subCategoryRepository.findByCategoryAndName(category, subcategoryName);

        if (optSubCategory.isEmpty()) {
            return;
        }

        boolean nameAlreadyExists = subCategory == null || !Objects.equals(optSubCategory.get(), subCategory);

        if (nameAlreadyExists) {
            throw messageService.toThrowable(SUB_CATEGORY_NAME_ALREADY_EXISTS, DomainException::new);
        }

    }

    private void validateSubCategoryName(Category category, String subcategoryName) {
        validateSubCategoryName(category, subcategoryName, null);
    }

    private void validCategoryName(String categoryName, Category category) {
        Optional<Category> optCategory = categoryRepository.findByName(categoryName);

        if (optCategory.isEmpty()) {
            return;
        }

        boolean nameAlreadyExists = category == null || !Objects.equals(optCategory.get(), category);

        if (nameAlreadyExists) {
            throw messageService.toThrowable(CATEGORY_NAME_ALREADY_EXISTS, (msg) -> new DomainException(CATEGORY_NAME_ALREADY_EXISTS, msg));
        }
    }

    private void validCategoryName(String categoryName) {
        validCategoryName(categoryName, null);
    }

}
