package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class ArtistCacheService {
    private final RedisService redisService;

    private static final String ARTIST_LIST_KEY = "artist:all";

    public void cacheArtistList(ArtistListResponse artistListResponse) {
        redisService.store(ARTIST_LIST_KEY, artistListResponse);
    }

    public void cacheArtistListWithTTL(ArtistListResponse artistListResponse, Duration duration) {
        redisService.storeWithTTL(ARTIST_LIST_KEY, artistListResponse, duration);
    }

    public ArtistListResponse getCachedArtistList() {
        return redisService.load(ARTIST_LIST_KEY, ArtistListResponse.class);
    }

    public void evictArtistList() {
        redisService.remove(ARTIST_LIST_KEY);
    }
}
