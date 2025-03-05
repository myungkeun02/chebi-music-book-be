package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void store(String key, Object value) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, value);
    }

    @Override
    public void storeWithTTL(String key, Object value, Duration duration) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, value, duration);
    }

    @Override
    public Object load(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        Object value = values.get(key);
        return (value != null) ? value : null;
    }

    @Override
    public void remove(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        Object value = values.get(key);
        if (value != null) {
            redisTemplate.delete(key);
        }
    }

    @Override
    public void storeMusicList(String key, MusicListResponse musicList) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(musicList);
            redisTemplate.opsForValue().set(key, jsonResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize musicListResponse", e);
        }
    }

    @Override
    public void storeMusicListWithTTL(String key, MusicListResponse musicList, Duration duration) {
        try {
            String jsonResponse = objectMapper.writeValueAsString(musicList);
            redisTemplate.opsForValue().set(key, jsonResponse, duration);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize musicListResponse", e);
        }
    }

    @Override
    public MusicListResponse loadMusicList(String key) {
        String jsonResponse = (String) redisTemplate.opsForValue().get(key);
        if (jsonResponse != null) {
            try {
                return objectMapper.readValue(jsonResponse, MusicListResponse.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to deserialize musicListResponse", e);
            }
        }
        return null;
    }

    @Override
    public void removeMusicList(String key) {
        Set<String> keys = redisTemplate.keys(key + "*");
        if (keys != null && keys.isEmpty()) {
            redisTemplate.delete(keys);
        }
    }
}
