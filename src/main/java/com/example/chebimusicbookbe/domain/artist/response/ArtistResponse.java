package com.example.chebimusicbookbe.domain.artist.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ArtistResponse {
    private Long id;
    private String name;
}
