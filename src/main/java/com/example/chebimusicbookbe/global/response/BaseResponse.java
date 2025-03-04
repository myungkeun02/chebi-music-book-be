package com.example.chebimusicbookbe.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BaseResponse<T> {
    private T data;
    private int statusCode;
    private String message;
}
