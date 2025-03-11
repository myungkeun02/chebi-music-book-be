package com.example.chebimusicbookbe.domain.artist.exception;

import com.example.chebimusicbookbe.global.exeption.BadRequestException;

public class AlreadyRegisterArtistException extends BadRequestException {
    public AlreadyRegisterArtistException(String message) {
        super(message, 1502);
    }
}
