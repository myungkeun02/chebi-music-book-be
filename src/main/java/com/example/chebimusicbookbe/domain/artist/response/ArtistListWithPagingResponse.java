package com.example.chebimusicbookbe.domain.artist.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ArtistListWithPagingResponse {
    private List<ArtistResponse> artistList;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int size;
}
