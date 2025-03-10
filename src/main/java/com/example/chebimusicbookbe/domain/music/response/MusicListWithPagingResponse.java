package com.example.chebimusicbookbe.domain.music.response;

import com.example.chebimusicbookbe.domain.music.model.Music;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MusicListWithPagingResponse {
    private List<MusicResponse> musicList;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
}
