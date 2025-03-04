package com.example.chebimusicbookbe.global.exeption;

import org.springframework.http.HttpStatus;

public class BadRequestException extends MainException {
    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
