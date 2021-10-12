package com.gualberto.ronei.rmgschoolapi.application.rest.controller.category;


import com.gualberto.ronei.rmgschoolapi.domain.category.Category;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryForm;
import com.gualberto.ronei.rmgschoolapi.domain.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

        Category savedCategory = categoryService.create(form);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();

        CategoryResponse response = CategoryResponse.fromCategory(savedCategory);

        Link selfLink = linkTo(methodOn(getClass()).create(null)).withSelfRel();
        Link all = linkTo(methodOn(getClass()).all()).withRel("all-categories");

        EntityModel<CategoryResponse> entityModel = EntityModel.of(response, all, selfLink);

        entityModel.add(selfLink);

        return ResponseEntity.created(location).body(entityModel);
    }

    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
