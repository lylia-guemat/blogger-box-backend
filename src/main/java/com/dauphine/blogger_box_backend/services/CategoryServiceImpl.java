package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.blogger_box_backend.models.Category;
import com.dauphine.blogger_box_backend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) throws CategoryNotFoundByIdException {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundByIdException(id));
    }

    @Override
    public Category create(String name) {
        Category category = new Category(name);
        return repository.save(category);
    }

    @Override
    public Category update(UUID id, String name) throws CategoryNotFoundByIdException{
    Category category = getById(id);
    if (category == null){
        return null;
    }
    category.setName(name);
    return repository.save(category);
    }

    @Override
    public boolean deleteById(UUID id){
        repository.deleteById(id);
        return true ;
    }

    @Override
    public List<Category> getAllLikeName(String name){
        return repository.findAllLikeName(name);
    }


}
