package com.example.chebimusicbookbe.domain.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CreateCategoryRequest {
    @NotBlank(message = "카테고리 이름을 입력해주세요")
    private String name;
}
