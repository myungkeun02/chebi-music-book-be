package com.example.chebimusicbookbe.domain.category.service;

import com.example.chebimusicbookbe.domain.category.request.CreateCategoryRequest;
import com.example.chebimusicbookbe.domain.category.request.UpdateCategoryByIdRequest;
import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryListWithPagingResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryResponse;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponse createCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse findCategoryById(Long id);
    CategoryListResponse findAllCategory();
    CategoryListWithPagingResponse findAllCategoryWithPaging(Pageable pageable);
    CategoryResponse updateCategoryById(UpdateCategoryByIdRequest updateCategoryByIdRequest);
    void deleteCategoryById(Long id);
}
