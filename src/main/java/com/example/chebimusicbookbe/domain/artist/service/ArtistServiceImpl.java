package com.example.chebimusicbookbe.domain.artist.service;

import com.example.chebimusicbookbe.domain.artist.repository.ArtistRepository;
import com.example.chebimusicbookbe.domain.artist.request.CreateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.request.UpdateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListWithPagingResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import com.example.chebimusicbookbe.infra.redis.ArtistCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistCacheService artistCacheService;

    @Override
    public ArtistResponse createArtist(CreateArtistRequest request) {
        return null;
    }

    @Override
    public ArtistResponse findArtistById(Long id) {
        return null;
    }

    @Override
    public ArtistListResponse findAllArtists() {
        return null;
    }

    @Override
    public ArtistListWithPagingResponse findAllArtistsWithPaging(Pageable pageable) {
        return null;
    }

    @Override
    public ArtistResponse updateArtist(UpdateArtistRequest request) {
        return null;
    }

    @Override
    public void deleteArtist(Long id) {

    }
}
