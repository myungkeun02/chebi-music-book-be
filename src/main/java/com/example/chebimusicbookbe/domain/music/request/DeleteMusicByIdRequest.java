package com.example.chebimusicbookbe.domain.music.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class DeleteMusicByIdRequest {
    private String id;
}
