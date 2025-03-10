package com.example.chebimusicbookbe.infra.redis;

import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor

public class CategoryCacheService {
    private final RedisService redisService;

    private static final String CATEGORY_LIST_KEY = "category:all";

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
}
