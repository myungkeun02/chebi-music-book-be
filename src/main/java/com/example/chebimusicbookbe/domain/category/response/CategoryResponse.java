package com.example.chebimusicbookbe.domain.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CategoryResponse {
    private Long id;
    private String name;
}
