package com.gualberto.ronei.rmgschoolapi.domain.category;

import com.gualberto.ronei.rmgschoolapi.domain.shared.exception.DomainException;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageCode;
import com.gualberto.ronei.rmgschoolapi.domain.shared.message.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.CATEGORY_NOT_FOUND;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_ID;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_NAME;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_NAME_FOR_UPDATE;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.MSG_CATEGORY_NOT_FOUND;
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

    //data
    private CategoryForm categoryForm;
    private Category categoryResult;
    private Category categoryTest;
    private List<Category> categoriesExpected;
    private List<Category> categoriesResult;

    @Test
    void create_shouldCreateCategory() {
        //given
        givenCategoryForm();
        //when
        whenCalledCreate();
        //then
        thenCreateOrUpdateCategory();
    }

    @Test
    void create_shouldNotCreateCategory_IfNameAlreadyExists() {
        //given
        givenCategoryForm();
        givenCategory();
        //when
        whenCallingMessageService_toThrowable(CATEGORY_NOT_FOUND, MSG_CATEGORY_NOT_FOUND);
        whenCallingCategoryRepository_findByName();

        //then
        thenThrowIfNameExists();
    }

    @Test
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
    void get_shouldNotReturnCategory_IfIdNotExists() {
        //given
        givenCategory();
        whenCallingMessageService_toThrowable(CATEGORY_NOT_FOUND, MSG_CATEGORY_NOT_FOUND);
        //then
        thenThrowGetIfIdExists();
    }

    @Test
    void findAll_shouldReturnAllCategories() {
        givenCategory();
        givenCategories();
        whenCallingCategoryRepository_findAll();
        whenCalled_findAll();
        thenReturnAllCategories();
    }

    @Test
    void delete_shouldDeleteCategory() {
        givenCategory();
        whenCallingCategoryRepository_findById();
        whenCalled_delete();
        thenShouldDeleteCategory();
    }

    @Test
    void delete_shouldNotDeleteCategory_IfCategoryNotFound() {
        givenCategory();
        whenCallingMessageService_toThrowable(CATEGORY_NOT_FOUND, MSG_CATEGORY_NOT_FOUND);
        thenThrowDeleteIfIdExists();
    }

    @Test
    void update_shouldUpdate() {
        givenCategory();
        givenCategoryForm(CATEGORY_NAME_FOR_UPDATE);
        whenCallingCategoryRepository_findById();
        whenCalledUpdate(CATEGORY_ID);
        thenCreateOrUpdateCategory();
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

    private void givenCategories() {
        categoriesExpected = List.of(categoryTest);
    }


    private void whenCallingCategoryRepository_findByName() {
        when(categoryRepository.findByName(CATEGORY_NAME)).thenReturn(Optional.of(categoryTest));
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

    private void thenShouldReturnCategory() {
        assertThat(categoryResult).isEqualTo(categoryTest);
    }

    private void thenThrowIfNameExists() {
        assertCategoryNotFound(() -> categoryService.create(categoryForm));
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