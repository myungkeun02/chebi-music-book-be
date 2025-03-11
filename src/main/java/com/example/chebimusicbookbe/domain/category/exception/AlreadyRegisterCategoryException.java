package com.example.chebimusicbookbe.domain.category.exception;

import com.example.chebimusicbookbe.global.exeption.BadRequestException;

public class AlreadyRegisterCategoryException extends BadRequestException {
    public AlreadyRegisterCategoryException(String message) {
        super(message, 1402);
    }
}
