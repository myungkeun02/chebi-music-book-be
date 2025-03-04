package com.example.chebimusicbookbe.domain.music.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CreateMusicRequest {
    @NotBlank(message = "음악 이름을 입력해주세요.")
    private String title;

    @NotBlank(message = "앨범아트 주소를 입력해주세요.")
    private String albumArt;

    @NotBlank(message = "아티스트 이름을 입력해주세요.")
    private String artist;

    @NotBlank(message = "카테고리 이름을 입력해주세요.")
    private String category;

    private String youtubeUrl;

    private String soopUrl;
}
