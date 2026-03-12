package com.dauphine.blogger_box_backend.services;

import com.dauphine.blogger_box_backend.models.Category;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> getAll();

    Category getById(UUID id);

    Category create(String name);

    Category update(UUID id, String name);

    boolean deleteById(UUID id) ;

    List<Category> getAllLikeName(@Param("name") String name);

}
