package com.example.chebimusicbookbe.domain.music.exception;

import com.example.chebimusicbookbe.global.exeption.BadRequestException;

public class AlreadyRegisterMusicException extends BadRequestException {
    public AlreadyRegisterMusicException(String message) {
        super(message, 1302);
    }
}
