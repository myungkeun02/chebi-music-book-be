package com.example.chebimusicbookbe.domain.music.exception;

import com.example.chebimusicbookbe.global.exeption.NotFoundException;

public class MusicNotFoundException extends NotFoundException {
    public MusicNotFoundException(String message) {
        super(message, 1301);
    }
}
