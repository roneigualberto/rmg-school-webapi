package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;


import com.gualberto.ronei.rmgschoolapi.domain.category.*;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping
    public CollectionModel<?> all() {

        List<Category> categories = categoryService.findAll();

        List<CategoryResponse> responseList = CategoryResponse.fromCategories(categories);

        Link selfLink = linkTo(methodOn(getClass()).all()).withSelfRel();
        Link createLink = linkTo(methodOn(getClass()).create(null)).withRel("create");

        return CollectionModel.of(responseList, selfLink, createLink);
    }


    @Transactional
    @PostMapping()
    public ResponseEntity<EntityModel<CategoryResponse>> create(@RequestBody CategoryRequest request) {

        CategoryForm form = request.toCategoryForm();

        Category category = categoryService.create(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        CategoryResponse response = CategoryResponse.fromCategory(category);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();
        Link all = linkTo(methodOn(getClass()).all()).withRel("all-categories");
        Link delete = linkTo(methodOn(getClass()).delete(category.getId())).withRel("delete");
        Link update = linkTo(methodOn(getClass()).update(category.getId(), null)).withRel("update");

        EntityModel<CategoryResponse> entityModel = EntityModel.of(response, selfLink, all,update, delete);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<EntityModel<CategoryResponse>> update(@PathVariable Long id, @Valid @RequestBody CategoryRequest request) {

        CategoryForm form = request.toCategoryForm();

        Category category = categoryService.update(id, form);

        CategoryResponse response = CategoryResponse.fromCategory(category);

        Link selfLink = linkTo(methodOn(getClass()).update(id, null)).withSelfRel();
        Link all = linkTo(methodOn(getClass()).all()).withRel("all-categories");
        Link delete = linkTo(methodOn(getClass()).delete(category.getId())).withRel("delete");


        EntityModel<CategoryResponse> entityModel = EntityModel.of(response, selfLink, all, delete);

        return ResponseEntity.ok(entityModel);
    }

    @Transactional
    @PostMapping("{categoryId}/sub-categories")
    public ResponseEntity<EntityModel<SubCategoryResponse>> createSubCategory(@PathVariable Long categoryId, @RequestBody SubCategoryRequest request) {

        SubcategoryForm form = request.toSubCategoryForm();

        SubCategory subCategory = categoryService.createSubCategory(categoryId, form);


        SubCategoryResponse response = SubCategoryResponse.fromSubCategory(subCategory);

        Link selfLink = linkTo(methodOn(getClass()).createSubCategory(categoryId, null)).withSelfRel();
        Link subCategoriesLink = linkTo(methodOn(getClass()).getSubCategories(categoryId)).withRel("sub-categories");
        Link delete = linkTo(methodOn(getClass()).deleteSubCategory(categoryId, subCategory.getId())).withRel("delete-subcategory");

        EntityModel<SubCategoryResponse> entityModel = EntityModel.of(response, selfLink, subCategoriesLink, delete);

        return ResponseEntity.ok(entityModel);
    }

    @Transactional
    @GetMapping("{categoryId}/sub-categories")
    public CollectionModel<?> getSubCategories(@PathVariable Long categoryId) {

        List<SubCategory> subCategories = categoryService.getSubCategories(categoryId);

        List<SubCategoryResponse> response = SubCategoryResponse.fromSubCategories(subCategories);

        Link selfLink = linkTo(methodOn(getClass()).getSubCategories(categoryId)).withSelfRel();

        return CollectionModel.of(response, selfLink);
    }

    @Transactional
    @PutMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<?> updateSubCategory(@PathVariable Long categoryId, @PathVariable Long subCategoryId, @RequestBody SubCategoryRequest request) {

        SubcategoryForm form = request.toSubCategoryForm();

        SubCategory subCategory = categoryService.updateSubCategory(categoryId, subCategoryId, form);

        SubCategoryResponse response = SubCategoryResponse.fromSubCategory(subCategory);

        Link selfLink = linkTo(methodOn(getClass()).createSubCategory(categoryId, null)).withSelfRel();
        Link subCategoriesLink = linkTo(methodOn(getClass()).getSubCategories(categoryId)).withRel("sub-categories");
        Link delete = linkTo(methodOn(getClass()).deleteSubCategory(categoryId, subCategoryId)).withRel("delete-subcategory");
        Link update = linkTo(methodOn(getClass()).update(categoryId, null)).withRel("update-subcategory");


        EntityModel<SubCategoryResponse> entityModel = EntityModel.of(response, selfLink, subCategoriesLink, update,delete);

        return ResponseEntity.ok(entityModel);
    }

    @Transactional
    @DeleteMapping("{categoryId}/sub-categories/{subCategoryId}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable Long categoryId, @PathVariable Long subCategoryId) {

        categoryService.deleteSubCategory(categoryId, subCategoryId);

        return ResponseEntity.noContent().build();
    }


}
