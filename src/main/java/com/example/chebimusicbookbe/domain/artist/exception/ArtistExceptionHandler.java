package com.example.chebimusicbookbe.domain.artist.exception;

import com.example.chebimusicbookbe.global.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArtistExceptionHandler {

    @ExceptionHandler(ArtistNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleArtistNotFoundException(ArtistNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse<>(null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(AlreadyRegisterArtistException.class)
    public ResponseEntity<BaseResponse<Void>> handleAlreadyRegisterArtistException(AlreadyRegisterArtistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new BaseResponse<>(null, HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
