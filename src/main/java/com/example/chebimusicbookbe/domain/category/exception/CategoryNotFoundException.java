package com.example.chebimusicbookbe.domain.category.exception;

import com.example.chebimusicbookbe.global.exeption.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {
    public CategoryNotFoundException(String message) {
        super(message, 1401);
    }
}
