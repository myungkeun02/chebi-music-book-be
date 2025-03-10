package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class MusicCacheService {
    private final RedisService redisService;

    private static final String MUSIC_LIST_KEY = "music:all";

    public void cacheMusicList(MusicListResponse musicList) {
        redisService.store(MUSIC_LIST_KEY, musicList);
    }

    public void cacheMusicListWithTTL(MusicListResponse musicList, Duration duration) {
        redisService.storeWithTTL(MUSIC_LIST_KEY, musicList, duration);
    }

    public MusicListResponse getCachedMusicList() {
        return redisService.load(MUSIC_LIST_KEY, MusicListResponse.class);
    }

    public void evictMusicList() {
        redisService.remove(MUSIC_LIST_KEY);
    }
}
