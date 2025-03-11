package com.example.chebimusicbookbe.domain.music.exception;

import com.example.chebimusicbookbe.global.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MusicExceptionHandler {

    @ExceptionHandler(MusicNotFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleMusicNotFoundException(MusicNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponse<>(null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(AlreadyRegisterMusicException.class)
    public ResponseEntity<BaseResponse<Void>> handleAlreadyRegisterMusicException(AlreadyRegisterMusicException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new BaseResponse<>(null, HttpStatus.CONFLICT.value(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
