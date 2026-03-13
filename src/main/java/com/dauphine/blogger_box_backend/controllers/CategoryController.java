package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.dto.CategoryRequest;
import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter like name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required = false) String name) {
        List<Category> categories = name == null || name.isBlank()
                ? service.getAll()
                : service.getAllLikeName(name);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get category by id",
            description = "Retrieve a category by id"
    )
    public ResponseEntity<Category> getById(@PathVariable UUID id) {
        try {
            Category category = service.getById(id);
            return ResponseEntity.ok(category);
        } catch (CategoryNotFoundByIdException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    @Operation(
            summary = "Create new category",
            description = "Create new category, only required field is the name of category to create"
    )
    public ResponseEntity<Category> create(@RequestBody CategoryRequest categoryRequest){
        Category category = service.create(categoryRequest.getName());
        return ResponseEntity
                .created(URI.create("v1/categories/" + category.getId()))
                .body(category);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update an existing category",
            description = "Update new category, only the name can be updated"
    )
    public ResponseEntity<Category> update(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest){
        try {
            Category category = service.update(id, categoryRequest.getName());
            return ResponseEntity.ok(category);
        }
        catch (CategoryNotFoundByIdException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category",
            description = ""
    )
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
         service.deleteById(id);
         return ResponseEntity.noContent().build();
    }

}
