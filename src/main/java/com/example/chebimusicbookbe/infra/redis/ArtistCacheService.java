package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class ArtistCacheService {
    private final RedisService redisService;

    private static final String ARTIST_LIST_KEY = "artist:all";
    private static final String ARTIST_KEY_PREFIX = "artist:";

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

    public void cacheArtist(ArtistResponse artistResponse) {
        String key = ARTIST_KEY_PREFIX + artistResponse.getId();
        redisService.store(key, artistResponse);
    }

    public void cacheArtistWithTTL(ArtistResponse artistResponse, Duration duration) {
        String key = ARTIST_KEY_PREFIX + artistResponse.getId();
        redisService.storeWithTTL(key, artistResponse, duration);
    }

    public ArtistResponse getCachedArtist(String id) {
        String key = ARTIST_KEY_PREFIX + id;
        return redisService.load(key, ArtistResponse.class);
    }

    public void evictArtist(String id) {
        String key = ARTIST_KEY_PREFIX + id;
        redisService.remove(key);
    }
}
