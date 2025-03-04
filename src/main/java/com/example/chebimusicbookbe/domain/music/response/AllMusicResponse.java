package com.example.chebimusicbookbe.domain.music.response;

import com.example.chebimusicbookbe.domain.music.model.Music;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AllMusicResponse {
    private List<Music> musicList;
}
