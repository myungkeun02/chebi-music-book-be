package com.example.chebimusicbookbe.global.exeption;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter

public class MainException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final int code;

    public MainException(HttpStatus status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
