package com.example.chebimusicbookbe.domain.artist.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistListResponse {
    private List<ArtistResponse> artistList;
}
