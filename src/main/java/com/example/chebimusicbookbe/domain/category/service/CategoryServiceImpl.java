package com.example.chebimusicbookbe.domain.category.service;

import com.example.chebimusicbookbe.domain.category.repository.CategoryRepository;
import com.example.chebimusicbookbe.domain.category.request.CreateCategoryRequest;
import com.example.chebimusicbookbe.domain.category.request.UpdateCategoryByIdRequest;
import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryListWithPagingResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryResponse;
import com.example.chebimusicbookbe.infra.redis.CategoryCacheService;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCacheService categoryCacheService;
    @Override
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse findCategoryById(Long id) {
        return null;
    }

    @Override
    public CategoryListResponse findAllCategory() {
        return null;
    }

    @Override
    public CategoryListWithPagingResponse findAllCategoryWithPaging(Pageable pageable) {
        return null;
    }

    @Override
    public CategoryResponse updateCategoryById(UpdateCategoryByIdRequest updateCategoryByIdRequest) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
