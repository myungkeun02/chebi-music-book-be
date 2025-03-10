package com.example.chebimusicbookbe.infra.redis;

import java.time.Duration;

public interface RedisService {
    <T> void store(String key, T value);
    <T> void storeWithTTL(String key, T value, Duration duration);
    <T> T load(String key, Class<T> clazz);
    void remove (String key);
    void removeByPattern(String pattern);
}
