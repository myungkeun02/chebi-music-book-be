package com.example.chebimusicbookbe.domain.music.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MusicResponse {
    private Long id;
    private String title;
    private String artist;
    private String albumArtUrl;
    private String category;
    private String youtubeUrl;
    private String soopUrl;
}
