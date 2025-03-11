package com.example.chebimusicbookbe.domain.category.controller;

import com.example.chebimusicbookbe.domain.category.request.CreateCategoryRequest;
import com.example.chebimusicbookbe.domain.category.request.UpdateCategoryByIdRequest;
import com.example.chebimusicbookbe.domain.category.response.CategoryListResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryListWithPagingResponse;
import com.example.chebimusicbookbe.domain.category.response.CategoryResponse;
import com.example.chebimusicbookbe.domain.category.service.CategoryService;
import com.example.chebimusicbookbe.global.constant.ResponseMessage;
import com.example.chebimusicbookbe.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@Tag(name = "02. Category - 카테고리", description = "카테고리 관련 API")

public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "카테고리 등록", description = "request data 를 기반으로 새로운 카테고리를 등록합니다.")
    public ResponseEntity<BaseResponse<CategoryResponse>> registerCategory(
            @RequestBody @Valid CreateCategoryRequest req
    ) {
        CategoryResponse res = categoryService.createCategory(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(res, HttpStatus.CREATED.value(), ResponseMessage.CATEGORY_REGISTER_SUCCESS));
    }

    @GetMapping("/{id}")
    @Operation(summary = "카테고리 정보 조회", description = "request param id의 카테고리 정보를 조회합니다.")
    public ResponseEntity<BaseResponse<CategoryResponse>> getCategory(
            @PathVariable Long id
    ) {
        CategoryResponse res = categoryService.findCategoryById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.CATEGORY_FETCH_SUCCESS));
    }

    @GetMapping("/all")
    @Operation(summary = "전체 카테고리 리스트 조회", description = "전체 카테고리 정보 리스트를 조회합니다.")
    public ResponseEntity<BaseResponse<CategoryListResponse>> getAllCategory() {
        CategoryListResponse res = categoryService.findAllCategory();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.CATEGORY_FETCH_SUCCESS));
    }

    @PageableAsQueryParam
    @GetMapping
    @Operation(summary = "페이지네이션 카테고리 리스트 조회", description = "페이지네이션으로 카테고리 리스트를 조회합니다.")
    public ResponseEntity<BaseResponse<CategoryListWithPagingResponse>> getCategoryWithPaging(
            @PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        CategoryListWithPagingResponse res = categoryService.findAllCategoryWithPaging(pageable);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.CATEGORY_FETCH_SUCCESS));
    }

    @PutMapping("/{id}")
    @Operation(summary = "카테고리 정보 수정", description = "request parma id의 카테고리 정보를 request data 기반으로 수정합니다.")
    public ResponseEntity<BaseResponse<CategoryResponse>> updateCategoryById(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCategoryByIdRequest req
    ) {
        CategoryResponse res = categoryService.updateCategoryById(id, req);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.CATEGORY_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "카테고리 정보 삭제", description = "request param id의 카테고리 정보를 삭제합니다.")
    public ResponseEntity<BaseResponse<Void>> deleteCategoryById(
            @PathVariable Long id
    ) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity
                .ok(new BaseResponse<>(null, HttpStatus.OK.value(), ResponseMessage.CATEGORY_DELETE_SUCCESS));
    }
}
