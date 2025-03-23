package com.example.chebimusicbookbe.domain.artist.service;

import com.example.chebimusicbookbe.domain.artist.model.Artist;
import com.example.chebimusicbookbe.domain.artist.request.CreateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.request.UpdateArtistByIdRequest;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListWithPagingResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import org.springframework.data.domain.Pageable;

public interface ArtistService {
    ArtistResponse registerArtist(CreateArtistRequest request);
    ArtistResponse getArtistById(Long id);
    ArtistListResponse getAllArtists();
    ArtistListWithPagingResponse getArtistsWithPaging(Pageable pageable);
    ArtistResponse updateArtistById(Long id, UpdateArtistByIdRequest request);
    void deleteArtistById(Long id);
}
