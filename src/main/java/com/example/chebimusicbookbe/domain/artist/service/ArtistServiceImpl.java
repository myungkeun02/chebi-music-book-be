package com.example.chebimusicbookbe.domain.artist.service;

import com.example.chebimusicbookbe.domain.artist.exception.AlreadyRegisterArtistException;
import com.example.chebimusicbookbe.domain.artist.exception.ArtistNotFoundException;
import com.example.chebimusicbookbe.domain.artist.model.Artist;
import com.example.chebimusicbookbe.domain.artist.repository.ArtistRepository;
import com.example.chebimusicbookbe.domain.artist.request.CreateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.request.UpdateArtistByIdRequest;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListWithPagingResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import com.example.chebimusicbookbe.infra.redis.ArtistCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistCacheService artistCacheService;

    @Override
    @Transactional
    public ArtistResponse registerArtist(CreateArtistRequest request) {
        if (artistRepository.findByName(request.getName()).isPresent()) {
            throw new AlreadyRegisterArtistException("이미 등록된 아티스트입니다.");
        }

        Artist artist = Artist.builder()
                .name(request.getName())
                .build();

        artist = artistRepository.save(artist);
        ArtistResponse response = ArtistResponse.from(artist);
        artistCacheService.cacheArtist(response);
        return response;
    }

    @Override
    public ArtistResponse getArtistById(Long id) {
        ArtistResponse cachedArtist = artistCacheService.getCachedArtist(String.valueOf(id));
        if (cachedArtist != null) {
            return cachedArtist;
        }

        Artist artist = findArtistOrThrow(id);
        ArtistResponse response = ArtistResponse.from(artist);
        artistCacheService.cacheArtistWithTTL(response, Duration.ofMinutes(30));
        return response;
    }

    @Override
    public ArtistListResponse getAllArtists() {
        ArtistListResponse cachedList = artistCacheService.getCachedArtistList();
        if (cachedList != null) {
            return cachedList;
        }

        List<ArtistResponse> artistList = artistRepository.findAll().stream()
                .map(ArtistResponse::from)
                .collect(Collectors.toList());

        ArtistListResponse response = ArtistListResponse.builder()
                .artistList(artistList)
                .build();

        artistCacheService.cacheArtistListWithTTL(response, Duration.ofMinutes(30));
        return response;
    }

    @Override
    public ArtistListWithPagingResponse getArtistsWithPaging(Pageable pageable) {
        Page<Artist> artistPage = artistRepository.findAll(pageable);
        List<ArtistResponse> artistList = artistPage.getContent().stream()
                .map(ArtistResponse::from)
                .collect(Collectors.toList());

        return ArtistListWithPagingResponse.builder()
                .artistList(artistList)
                .currentPage(artistPage.getNumber() + 1)
                .totalPages(artistPage.getTotalPages())
                .totalElements(artistPage.getTotalElements())
                .size(artistPage.getSize())
                .build();
    }

    @Override
    @Transactional
    public ArtistResponse updateArtistById(Long id, UpdateArtistByIdRequest request) {
        Artist artist = findArtistOrThrow(id);
        artist.updateName(request.getName());
        artist = artistRepository.save(artist);
        ArtistResponse response = ArtistResponse.from(artist);
        artistCacheService.cacheArtist(response);
        artistCacheService.evictArtistList();
        return response;
    }

    @Override
    @Transactional
    public void deleteArtistById(Long id) {
        Artist artist = findArtistOrThrow(id);
        artistRepository.delete(artist);
        artistCacheService.evictArtist(String.valueOf(id));
        artistCacheService.evictArtistList();
    }

    private Artist findArtistOrThrow(Long id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException("아티스트를 찾을 수 없습니다."));
    }
}
