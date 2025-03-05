package com.example.chebimusicbookbe.domain.category.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateCategoryByIdRequest {
    @NotBlank(message = "카테고리 이름을 입력해 주세요")
    private String name;
}
