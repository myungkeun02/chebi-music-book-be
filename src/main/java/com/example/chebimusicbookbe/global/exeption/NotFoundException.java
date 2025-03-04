package com.example.chebimusicbookbe.global.exeption;

import org.springframework.http.HttpStatus;

public class NotFoundException extends MainException {
    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }
}
