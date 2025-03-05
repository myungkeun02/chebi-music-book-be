package com.example.chebimusicbookbe.domain.category.response;

import com.example.chebimusicbookbe.domain.category.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CategoryListResponse {
    private List<Category> categories;
}
