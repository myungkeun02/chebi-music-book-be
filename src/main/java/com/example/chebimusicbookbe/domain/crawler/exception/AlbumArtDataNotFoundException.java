package com.example.chebimusicbookbe.domain.crawler.exception;

import com.example.chebimusicbookbe.global.exeption.NotFoundException;

public class AlbumArtDataNotFoundException extends NotFoundException {
    public AlbumArtDataNotFoundException(String message) {
        super(message, 1000);
    }
}
