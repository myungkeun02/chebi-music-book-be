package com.example.chebimusicbookbe.domain.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CategoryListWithPagingResponse {
    private List<CategoryResponse> categoryList;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
}
