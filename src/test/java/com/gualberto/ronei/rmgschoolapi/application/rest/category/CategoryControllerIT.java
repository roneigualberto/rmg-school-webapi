package com.gualberto.ronei.rmgschoolapi.application.rest.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryRequest;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.SubCategoryRequest;
import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryRepository;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategory;
import com.gualberto.ronei.rmgschoolapi.domain.category.SubCategoryRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.CATEGORY_NAME_ALREADY_EXISTS;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerIT extends BaseIntegrationTest {


    public static final String BASE_URI = "/api/v1/categories";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;


    private Category categoryTest;

    private CategoryRequest categoryRequest;
    private SubCategoryRequest subCategoryRequest;
    private SubCategory subCategoryTest;


    @Test
    @Transactional
    void shouldReturnAllCategories() throws Exception {

        givenCategory();

        mockMvc.perform(get(BASE_URI))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.categories").isArray())
                .andExpect(jsonPath("$._embedded.categories[0].name").value(categoryTest.getName()));

    }

    @Test
    @Transactional
    void shouldCreatedCategory() throws Exception {
        givenCategoryRequest();

        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(CATEGORY_NAME))
                .andExpect(jsonPath("$._links").isMap());
    }

    @Test
    @Transactional
    void shouldNotCreatedCategory_IFNameAlreadyExists() throws Exception {

        givenCategory();
        givenCategoryRequest();

        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.code").value(CATEGORY_NAME_ALREADY_EXISTS.getCode()))
                .andExpect(jsonPath("$.message").value(getMessage(CATEGORY_NAME_ALREADY_EXISTS)));
    }

    @Test
    @Transactional
    void shouldDeleteCategory() throws Exception {

        givenCategory();

        mockMvc.perform(delete(BASE_URI + "/{id}", categoryTest.getId()))
                .andExpect(status().isNoContent());


    }

    @Test
    @Transactional
    void shouldNotUpdateCategory_IfNameIsNull() throws Exception {

        givenCategory();
        givenCategoryRequest(OTHER_CATEGORY_NAME);

        mockMvc.perform(put(BASE_URI + "/{id}", categoryTest.getId()))
                .andExpect(status().isBadRequest());


    }

    @Test
    @Transactional
    void shouldUpdateCategory() throws Exception {

        givenCategory();
        givenCategoryRequest(OTHER_CATEGORY_NAME);

        mockMvc.perform(put(BASE_URI + "/{id}", categoryTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(categoryTest.getId()))
                .andExpect(jsonPath("$.name").value(OTHER_CATEGORY_NAME))
                .andExpect(jsonPath("$._links").isMap());
    }

    @Test
    @Transactional
    void shouldCreateSubcategory() throws Exception {

        givenCategory();
        givenSubCategoryRequest();

        mockMvc.perform(post(BASE_URI + "/{categoryId}/sub-categories", categoryTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(subCategoryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(subCategoryRequest.getName()))
                .andExpect(jsonPath("$._links").isMap());
    }

    @Test
    @Transactional
    void shouldDeleteSubcategory() throws Exception {

        givenCategory();
        givenSubCategory();

        mockMvc.perform(delete(BASE_URI + "/{categoryId}/sub-categories/{subCategoryId}", categoryTest.getId(), subCategoryTest.getId()))
                .andExpect(status().isNoContent());

    }

    @Test
    @Transactional
    void shouldUpdateSubcategory() throws Exception {

        givenCategory();
        givenSubCategory();
        givenSubCategoryRequest(OTHER_SUB_CATEGORY_NAME);

        mockMvc.perform(put(BASE_URI + "/{categoryId}/sub-categories/{subCategoryId}", categoryTest.getId(), subCategoryTest.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(subCategoryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(subCategoryTest.getId()))
                .andExpect(jsonPath("$.name").value(OTHER_SUB_CATEGORY_NAME))
                .andExpect(jsonPath("$._links").isMap());
    }

    @Test
    @Transactional
    void shouldReturnSubCategoriesFromCategory() throws Exception {

        givenCategory();
        givenSubCategory();

        mockMvc.perform(get(BASE_URI + "/{categoryId}/sub-categories", categoryTest.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.sub-categories").isArray());
    }

    private void givenCategoryRequest() {
        givenCategoryRequest(CATEGORY_NAME);
    }

    private void givenCategoryRequest(String categoryName) {
        categoryRequest = CategoryRequest.builder()
                .name(categoryName)
                .build();
    }

    private void givenSubCategoryRequest() {
        givenSubCategoryRequest(SUB_CATEGORY_NAME);
    }

    private void givenSubCategoryRequest(String subCategoryName) {
        subCategoryRequest = SubCategoryRequest.builder()
                .name(subCategoryName)
                .build();
    }

    private void givenCategory() {
        categoryTest = Category.builder().name(CATEGORY_NAME).build();
        categoryTest = categoryRepository.save(categoryTest);
    }

    private void givenSubCategory() {
        subCategoryTest = SubCategory.builder()
                .category(categoryTest)
                .name(SUB_CATEGORY_NAME).build();
        subCategoryTest = subCategoryRepository.saveAndFlush(subCategoryTest);
    }


    public void tearDown() {
        if (categoryTest != null) {
            categoryRepository.delete(categoryTest);
        }
    }
}
