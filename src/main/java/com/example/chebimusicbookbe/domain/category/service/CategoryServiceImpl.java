package com.example.chebimusicbookbe.domain.category.service;

import com.example.chebimusicbookbe.domain.category.exception.CategoryNotFoundException;
import com.example.chebimusicbookbe.domain.category.model.Category;
import com.example.chebimusicbookbe.domain.category.repository.CategoryRepository;
import com.example.chebimusicbookbe.domain.category.request.CreateCategoryRequest;
import com.example.chebimusicbookbe.domain.category.request.UpdateCategoryByIdRequest;
import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryListWithPagingResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryResponse;
import com.example.chebimusicbookbe.infra.redis.CategoryCacheService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCacheService categoryCacheService;

    /**
     * 카테고리 생성
     */
    @Override
    @Transactional
    public CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryRepository.save(
                Category.builder()
                        .name(createCategoryRequest.getName())
                        .build()
        );
        categoryCacheService.evictCategoryList();
        return CategoryResponse.from(category);
    }

    /**
     * ID 기준 카테고리 조회
     */
    @Override
    public CategoryResponse findCategoryById(Long id) {
        CategoryResponse cachedCategory = categoryCacheService.getCachedCategory(String.valueOf(id));
        if (cachedCategory != null) {
            return cachedCategory;
        }
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("[ERROR] Category Not Found"));
        CategoryResponse response = CategoryResponse.from(category);

        categoryCacheService.cacheCategoryWithTTL(response, Duration.ofMinutes(30));
        return response;
    }

    /**
     * 전체 카테고리 조회 (페이징 없음)
     */
    @Override
    public CategoryListResponse findAllCategory() {

        CategoryListResponse cachedList = categoryCacheService.getCachedCategoryList();
        if (cachedList != null) {
            return cachedList;
        }

        List<Category> categoryList = categoryRepository.findAll();
        List<CategoryResponse> categoryResponses = categoryList.stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());

        CategoryListResponse response = CategoryListResponse.builder()
                .categoryList(categoryResponses)
                .build();
        categoryCacheService.cacheCategoryListWithTTL(response, Duration.ofMinutes(60));

        return response;
    }

    /**
     * ID 기준 카테고리 수정
     */
    @Override
    public CategoryResponse updateCategoryById(Long id, UpdateCategoryByIdRequest request) {
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("[ERROR] Category Not Found"));

        updateCategoryFields(oldCategory, request);

        Category updateCategory = categoryRepository.save(oldCategory);

        categoryCacheService.evictCategoryList();
        categoryCacheService.evictCategory(String.valueOf(id));
        categoryCacheService.cacheCategoryWithTTL(CategoryResponse.from(updateCategory), Duration.ofMinutes(30));

        return CategoryResponse.from(updateCategory);
    }

    /**
     * 페이지네이션 카테고리 조회
     */
    @Override
    public CategoryListWithPagingResponse findAllCategoryWithPaging(Pageable pageable) {
        Page<Category> pageResult = categoryRepository.findAll(pageable);

        List<CategoryResponse> categoryResponses = pageResult.getContent()
                .stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());

        return CategoryListWithPagingResponse.builder()
                .categoryList(categoryResponses)
                .currentPage(pageResult.getNumber())
                .totalPages(pageResult.getTotalPages())
                .size(pageResult.getSize())
                .build();
    }

    /**
     * ID 기준 카테고리 삭제
     */
    @Override
    @Transactional
    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("[ERROR] Category Not Found"));

        categoryRepository.delete(category);

        categoryCacheService.evictCategoryList();
    }

    /**
     * 기존 Music 엔티티에 업데이트 값 적용
     */
    private void updateCategoryFields(Category oldCategory, UpdateCategoryByIdRequest request) {
        if (oldCategory.getName() != null && !request.getName().equals(oldCategory.getName())) {
            oldCategory.setName(request.getName());
        }
    }
}
