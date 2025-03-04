package com.example.chebimusicbookbe.domain.music.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UpdateMusicByIdRequest {
    private String title;

    private String albumArtUrl;

    private String artist;

    private String category;

    private String youtubeUrl;

    private String soopUrl;
}
