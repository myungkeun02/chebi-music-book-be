package com.example.chebimusicbookbe.domain.artist.exception;

import com.example.chebimusicbookbe.global.exeption.NotFoundException;

public class ArtistNotFoundException extends NotFoundException {
    public ArtistNotFoundException(String message) {
        super(message, 1501);
    }
}
