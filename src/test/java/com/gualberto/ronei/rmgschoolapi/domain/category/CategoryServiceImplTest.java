package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.*;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private MessageService messageService;

    @Mock
    private SubCategoryRepository subCategoryRepository;

    //data
    private CategoryForm categoryForm;
    private Category categoryResult;
    private Category categoryTest;
    private SubCategory subCategoryTest;
    private List<Category> categoriesExpected;
    private List<Category> categoriesResult;
    private SubcategoryForm subcategoryForm;
    private SubCategory subCategoryResult;

    @Test
    @DisplayName("Creating a category with mandatory fields")
    void create_shouldCreateCategory() {
        //given
        givenCategoryForm();
        //when
        whenCalledCreate();
        //then
        thenCreateOrUpdateCategory();
    }

    @Test
    @DisplayName("It is not possible saving category if it is name already exists")
    void create_ThrowsException_IfCategoryNameAlreadyExists() {
        //given
        givenCategoryForm();
        givenCategory();
        //when
        whenCallingMessageService_toThrowable(CATEGORY_NAME_ALREADY_EXISTS, MSG_CATEGORY_NAME_EXISTS);
        whenCallingCategoryRepository_findByName();

        //then
        thenThrowIfCategoryNameAlreadyExists();
    }

    @Test
    @DisplayName("Retrieving category by id")
    void get_shouldReturnCategory() {
        //given
        givenCategory();
        //when
        whenCallingCategoryRepository_findById();
        whenCalledGet();

        //then
        thenShouldReturnCategory();
    }

    @Test
    @DisplayName("Throws Exception if there is no category with given id")
    void get_ThrowsException_IfIdNotExists() {
        //given
        givenCategory();
        whenCallingMessageService_toThrowable(CATEGORY_NOT_FOUND, MSG_CATEGORY_NOT_FOUND);
        //then
        thenThrowGetIfIdExists();
    }

    @Test
    @DisplayName("Retrieving all categories")
    void findAll_shouldReturnAllCategories() {
        givenCategory();
        givenCategories();
        whenCallingCategoryRepository_findAll();
        whenCalled_findAll();
        thenReturnAllCategories();
    }

    @Test()
    @DisplayName("Deleting Category by id")
    void delete_shouldDeleteCategory() {
        givenCategory();
        whenCallingCategoryRepository_findById();
        whenCalled_delete();
        thenShouldDeleteCategory();
    }

    @Test()
    @DisplayName("It is not possible delete category if it is not exists")
    void delete_ThrowsException_IfCategoryNotFound() {
        givenCategory();
        whenCallingMessageService_toThrowable(CATEGORY_NOT_FOUND, MSG_CATEGORY_NOT_FOUND);
        thenThrowDeleteIfIdExists();
    }

    @Test
    @DisplayName("Updating category")
    void update_shouldUpdate() {
        givenCategory();
        givenCategoryForm(CATEGORY_NAME_FOR_UPDATE);
        whenCallingCategoryRepository_findById();
        whenCalledUpdate(CATEGORY_ID);
        thenCreateOrUpdateCategory();
    }

    @Test
    @DisplayName("Creating SubCategory")
    void createSubCategory_shouldCreateSubcategory() {
        givenCategory();
        whenCallingCategoryRepository_findById();
        givenSubcategoryForm();
        whenCalledCreateSubCategory();
        thenCreateOrUpdateSubCategory();
    }

    @Test
    @DisplayName("Should not Creating Sub Category if name already exists")
    void createSubCategory_throwsException_IFNameAlreadyExistsInCategory() {
        givenCategory();
        givenSubCategory();
        givenSubcategoryForm();

        whenCallingMessageService_toThrowable(SUB_CATEGORY_NAME_ALREADY_EXISTS, MSG_SUB_CATEGORY_NAME_EXISTS);
        whenCallingCategoryRepository_findById();
        whenCallingSubCategoryRepository_findByCategoryAndName();

        thenThrowIfSubCategoryNameAlreadyExists();
    }

    private void whenCalledCreateSubCategory() {
        subCategoryResult = categoryService.createSubCategory(CATEGORY_ID, subcategoryForm);
    }

    private void givenSubcategoryForm() {
        subcategoryForm = SubcategoryForm.builder()
                .name(SUB_CATEGORY_NAME)
                .build();
    }

    private void givenCategoryForm(String categoryName) {
        categoryForm = CategoryForm.builder()
                .name(categoryName)
                .build();
    }

    private void givenCategoryForm() {
        givenCategoryForm(CATEGORY_NAME);
    }

    private void givenCategory() {
        categoryTest = Category
                .builder()
                .id(CATEGORY_ID)
                .name(CATEGORY_NAME)
                .build();
    }

    private void givenSubCategory() {
        subCategoryTest = SubCategory
                .builder()
                .name(SUB_CATEGORY_NAME)
                .build();
    }

    private void givenCategories() {
        categoriesExpected = List.of(categoryTest);
    }


    private void whenCallingCategoryRepository_findByName() {
        when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(Optional.of(categoryTest));
    }

    private void whenCallingSubCategoryRepository_findByCategoryAndName() {
        when(subCategoryRepository.findByCategoryAndName(any(), eq(SUB_CATEGORY_NAME))).thenReturn(Optional.ofNullable(subCategoryTest));
    }

    private void whenCallingCategoryRepository_findById() {
        when(categoryRepository.findById(CATEGORY_ID)).thenReturn(Optional.ofNullable(categoryTest));
    }

    private void whenCallingMessageService_toThrowable(MessageCode code, String message) {
        when(messageService.toThrowable(eq(code), any())).thenReturn(new DomainException(message));
    }

    private void whenCalledCreate() {
        categoryResult = categoryService.create(categoryForm);
    }

    private void whenCalledGet() {
        categoryResult = categoryService.get(CATEGORY_ID);
    }

    private void whenCallingCategoryRepository_findAll() {
        when(categoryRepository.findAll()).thenReturn(categoriesExpected);
    }

    private void whenCalled_findAll() {
        categoriesResult = categoryService.findAll();
    }

    private void whenCalled_delete() {
        categoryService.delete(CATEGORY_ID);
    }

    private void whenCalledUpdate(Long categoryId) {
        categoryResult = categoryService.update(categoryId, categoryForm);
    }

    private void thenCreateOrUpdateCategory() {
        verify(categoryRepository, times(1)).save(any());
    }

    private void thenCreateOrUpdateSubCategory() {
        verify(subCategoryRepository, times(1)).save(any());
        assertThat(subCategoryResult.getName()).isEqualTo(subcategoryForm.getName());
    }

    private void thenShouldReturnCategory() {
        assertThat(categoryResult).isEqualTo(categoryTest);
    }

    private void thenThrowIfNameExists() {
        assertCategoryNotFound(() -> categoryService.create(categoryForm));
    }

    private void thenThrowIfSubCategoryNameAlreadyExists() {
        DomainException exception = assertThrows(DomainException.class, () -> categoryService.createSubCategory(CATEGORY_ID, subcategoryForm));
        assertThat(exception.getMessage()).isEqualTo(MSG_SUB_CATEGORY_NAME_EXISTS);
    }


    private void thenThrowIfCategoryNameAlreadyExists() {
        DomainException exception = assertThrows(DomainException.class, () -> categoryService.create(categoryForm));
        assertThat(exception.getMessage()).isEqualTo(MSG_CATEGORY_NAME_EXISTS);
    }

    private void assertCategoryNotFound(Executable executable) {
        DomainException exception = assertThrows(DomainException.class, executable);
        assertThat(exception.getMessage()).isEqualTo(MSG_CATEGORY_NOT_FOUND);
    }

    private void thenThrowGetIfIdExists() {
        assertCategoryNotFound(() -> categoryService.get(CATEGORY_ID));
    }

    private void thenThrowDeleteIfIdExists() {
        assertCategoryNotFound(() -> categoryService.delete(CATEGORY_ID));
    }

    private void thenReturnAllCategories() {
        assertThat(categoriesResult).isEqualTo(categoriesExpected);
    }

    private void thenShouldDeleteCategory() {
        verify(categoryRepository, times(1)).delete(any());
    }

}