package com.gualberto.ronei.rmgschoolapi.application.rest.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gualberto.ronei.rmgschoolapi.application.rest.controller.category.CategoryRequest;
import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryRepository;
import com.gualberto.ronei.rmgschoolapi.infra.tests.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.CATEGORY_NAME_ALREADY_EXISTS;
import static com.gualberto.ronei.rmgschoolapi.domain.category.CategoryMessageCode.CATEGORY_NOT_FOUND;
import static com.gualberto.ronei.rmgschoolapi.infra.tests.CategoryTestContants.CATEGORY_NAME;
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


    private Category categoryTest;

    private CategoryRequest categoryRequest;


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
                .andExpect(jsonPath("$.name").value(CATEGORY_NAME));
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

    private void givenCategoryRequest() {
        categoryRequest = CategoryRequest.builder()
                .name(CATEGORY_NAME)
                .build();
    }

    private void givenCategory() {
        categoryTest = Category.builder().name(CATEGORY_NAME).build();
        categoryTest = categoryRepository.save(categoryTest);
    }


    public void tearDown() {
        if (categoryTest != null) {
            categoryRepository.delete(categoryTest);
        }
    }
}
