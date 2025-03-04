package com.example.chebimusicbookbe.domain.artist.service;

import java.util.List;

public interface CrawlerService {
    List<String> albumArtCrawl(String keyword);
}
