package com.example.chebimusicbookbe.domain.artist.service;

import com.example.chebimusicbookbe.domain.artist.request.CreateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.request.UpdateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListWithPagingResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import org.springframework.data.domain.Pageable;

public interface ArtistService {
    ArtistResponse createArtist(CreateArtistRequest request);
    ArtistResponse findArtistById(Long id);
    ArtistListResponse findAllArtists();
    ArtistListWithPagingResponse findAllArtistsWithPaging(Pageable pageable);
    ArtistResponse updateArtist(UpdateArtistRequest request);
    void deleteArtist(Long id);
}
