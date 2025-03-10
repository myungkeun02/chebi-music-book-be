package com.example.chebimusicbookbe.infra.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public <T> void store(String key, T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void storeWithTTL(String key, T value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    @Override
    public <T> T load(String key, Class<T> clazz) {
        Object value = redisTemplate.opsForValue().get(key);
        if (clazz.isInstance(value)) {
            return clazz.cast(value);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void removeByPattern(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        if (keys != null && !keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}