package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class MusicCacheService {
    private final RedisService redisService;

    private static final String MUSIC_LIST_KEY = "music:all";
    private static final String MUSIC_KEY_PREFIX = "music:";

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

    public void cacheMusic(MusicResponse music) {
        String key = MUSIC_KEY_PREFIX + music.getId();
        redisService.store(key, music);
    }

    public void cacheMusicWithTTL(MusicResponse music, Duration duration) {
        String key = MUSIC_KEY_PREFIX + music.getId();
        redisService.storeWithTTL(key, music, duration);
    }


    public MusicResponse getCachedMusic(String id) {
        String key = MUSIC_KEY_PREFIX + id;
        return redisService.load(key, MusicResponse.class);
    }

    public void evictMusic(String id) {
        String key = MUSIC_KEY_PREFIX + id;
        redisService.remove(key);
    }
}
