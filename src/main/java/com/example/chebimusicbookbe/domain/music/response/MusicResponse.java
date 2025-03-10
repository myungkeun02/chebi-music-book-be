package com.example.chebimusicbookbe.domain.music.response;

import com.example.chebimusicbookbe.domain.music.model.Music;
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

    public static MusicResponse from(Music music) {
        return MusicResponse.builder()
                .id(music.getId())
                .title(music.getTitle())
                .artist(music.getArtist())
                .albumArtUrl(music.getAlbumArtUrl())
                .category(music.getCategory())
                .youtubeUrl(music.getYoutubeUrl())
                .soopUrl(music.getSoopUrl())
                .build();
    }
}
