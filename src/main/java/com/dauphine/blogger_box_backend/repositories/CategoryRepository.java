package com.dauphine.blogger_box_backend.repositories;

import com.dauphine.blogger_box_backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
