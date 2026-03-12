package com.dauphine.blogger_box_backend.controllers;

import com.dauphine.blogger_box_backend.dto.CategoryRequest;
import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.services.CategoryService;
import org.springframework.web.bind.annotation.*;

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
    public List<Category> retrieveAllCategories() {
        return service.getAll();
    }

    @GetMapping("{id}")
    public Category retrieveCategoryById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PostMapping
    public Category create(@RequestBody CategoryRequest categoryRequest){
        return service.create(categoryRequest.getName());
    }

    @PutMapping("{id}")
    public Category update(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest){
        return service.update(id, categoryRequest.getName());
    }

    @DeleteMapping("{id}")
    public boolean deleteById(@PathVariable UUID id){
        return service.deleteById(id);
    }

}
