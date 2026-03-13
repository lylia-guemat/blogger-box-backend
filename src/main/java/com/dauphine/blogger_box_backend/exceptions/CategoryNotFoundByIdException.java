package com.dauphine.blogger_box_backend.exceptions;

import java.util.UUID;

public class CategoryNotFoundByIdException extends Exception{
    public CategoryNotFoundByIdException(UUID id) {
        super("Category not found with id: " + id);
    }
}
