package com.example.chebimusicbookbe.domain.artist.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateArtistRequest {
    @NotBlank(message = "아티스트 이름을 입력해주세요")
    private String name;
}
