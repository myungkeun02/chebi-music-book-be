package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class CategoryCacheService {
    private final RedisService redisService;

    private static final String CATEGORY_LIST_KEY = "category:all";
    private static final String CATEGORY_KEY_PREFIX = "category:";

    public void cacheCategoryList(CategoryListResponse categoryListResponse) {
        redisService.store(CATEGORY_LIST_KEY, categoryListResponse);
    }

    public void cacheCategoryListWithTTL(CategoryListResponse categoryListResponse, Duration duration) {
        redisService.storeWithTTL(CATEGORY_LIST_KEY, categoryListResponse, duration);
    }

    public CategoryListResponse getCachedCategoryList() {
        return redisService.load(CATEGORY_LIST_KEY, CategoryListResponse.class);
    }

    public void evictCategoryList() {
        redisService.remove(CATEGORY_LIST_KEY);
    }

    public void cacheCategory(CategoryResponse categoryResponse) {
        String key = CATEGORY_KEY_PREFIX + categoryResponse.getId();
        redisService.store(key, categoryResponse);
    }

    public void cacheCategoryWithTTL(CategoryResponse categoryResponse, Duration duration) {
        String key = CATEGORY_KEY_PREFIX + categoryResponse.getId();
        redisService.storeWithTTL(key, categoryResponse, duration);
    }

    public CategoryResponse getCachedCategory(Long categoryId) {
        String key = CATEGORY_KEY_PREFIX + categoryId;
        return redisService.load(key, CategoryResponse.class);
    }

    public void evictCategory(Long categoryId) {
        String key = CATEGORY_KEY_PREFIX + categoryId;
        redisService.remove(key);
    }
}
