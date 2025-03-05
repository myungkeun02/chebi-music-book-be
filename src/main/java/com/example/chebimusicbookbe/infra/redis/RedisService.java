package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;

import java.time.Duration;
import java.util.Objects;

public interface RedisService {
    // 기본적인 String key : Objects value 구조 레디스 템플릿
    void store(String key, Object value);
    void storeWithTTL(String key, Object value, Duration duration);
    Object load(String key);
    void remove(String key);

    // 전체 음악 데이터 캐싱
    void storeMusicList(String key, MusicListResponse musicListResponse);
    void storeMusicListWithTTL(String key, MusicListResponse musicList, Duration duration);
    MusicListResponse loadMusicList(String key);
    void removeMusicList(String key);
}
