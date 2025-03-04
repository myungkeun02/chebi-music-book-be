package com.example.chebimusicbookbe.domain.artist.controller;

import com.example.chebimusicbookbe.domain.artist.exception.AlbumArtDataNotFoundException;
import com.example.chebimusicbookbe.domain.artist.service.CrawlerService;
import com.example.chebimusicbookbe.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/crawl")
@Tag(name = "06. Crawler - Bugs Music 크롤링", description = "Bugs Music 에서 앨범아트를 크롤링하는 API")

public class CrawlerController {
    private final CrawlerService crawlerService;

    @GetMapping()
    public ResponseEntity<BaseResponse<List<String>>> getAlbumArt(
            @RequestBody String keyword
    ) {
        try {
            List<String> response = crawlerService.albumArtCrawl(keyword);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new BaseResponse<>(response, HttpStatus.OK.value(), "앨범아트 리스트를 가져옵니다."));
        } catch (AlbumArtDataNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(null, HttpStatus.NOT_EXTENDED.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
