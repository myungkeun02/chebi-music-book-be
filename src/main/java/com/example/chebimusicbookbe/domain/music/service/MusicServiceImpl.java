package com.example.chebimusicbookbe.domain.music.service;

import com.example.chebimusicbookbe.domain.music.exception.MusicNotFoundException;
import com.example.chebimusicbookbe.domain.music.model.Music;
import com.example.chebimusicbookbe.domain.music.repository.MusicRepository;
import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicListWithPagingResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;

    /**
     * 음악 생성
     */
    @Transactional
    @Override
    public MusicResponse createMusic(CreateMusicRequest request) {
        Music music = musicRepository.save(
                Music.builder()
                        .title(request.getTitle())
                        .artist(request.getArtist())
                        .albumArtUrl(request.getAlbumArt())
                        .category(request.getCategory())
                        .youtubeUrl(request.getYoutubeUrl())
                        .soopUrl(request.getSoopUrl())
                        .build()
        );

        return MusicResponse.from(music);
    }

    /**
     * ID 기준 음악 조회
     */
    @Override
    public MusicResponse findMusicById(Long id) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));

        return MusicResponse.from(music);
    }

    /**
     * 전체 음악 조회 (페이징 없음)
     */
    @Override
    public MusicListResponse findAllMusic() {
        List<Music> musics = musicRepository.findAll();
        List<MusicResponse> musicResponses = musics.stream()
                .map(MusicResponse::from)
                .collect(Collectors.toList());

        return MusicListResponse.builder()
                .musicList(musicResponses)
                .build();
    }

    /**
     * 음악 수정 (ID 기준)
     */
    @Transactional
    @Override
    public MusicResponse updateMusicById(Long id, UpdateMusicByIdRequest request) {
        Music oldMusic = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));

        updateMusicFields(oldMusic, request);

        Music updatedMusic = musicRepository.save(oldMusic);

        return MusicResponse.from(updatedMusic);
    }

    /**
     * 페이지네이션 음악 조회
     */
    @Override
    public MusicListWithPagingResponse findAllMusicWithPaging(Pageable pageable) {
        Page<Music> pageResult = musicRepository.findAll(pageable);

        List<MusicResponse> musicResponses = pageResult.getContent().stream()
                .map(MusicResponse::from)
                .collect(Collectors.toList());

        return MusicListWithPagingResponse.builder()
                .musicList(musicResponses)
                .currentPage(pageResult.getNumber())
                .totalPages(pageResult.getTotalPages())
                .totalElements(pageResult.getTotalElements())
                .size(pageResult.getSize())
                .build();
    }

    /**
     * 음악 삭제 (ID 기준)
     */
    @Transactional
    @Override
    public void deleteMusicById(Long id) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));

        musicRepository.delete(music);
    }

    /**
     * 기존 Music 엔티티에 업데이트 값 적용
     */
    private void updateMusicFields(Music oldMusic, UpdateMusicByIdRequest request) {
        if (request.getTitle() != null && !request.getTitle().equals(oldMusic.getTitle())) {
            oldMusic.setTitle(request.getTitle());
        }
        if (request.getArtist() != null && !request.getArtist().equals(oldMusic.getArtist())) {
            oldMusic.setArtist(request.getArtist());
        }
        if (request.getAlbumArtUrl() != null && !request.getAlbumArtUrl().equals(oldMusic.getAlbumArtUrl())) {
            oldMusic.setAlbumArtUrl(request.getAlbumArtUrl());
        }
        if (request.getCategory() != null && !request.getCategory().equals(oldMusic.getCategory())) {
            oldMusic.setCategory(request.getCategory());
        }
        if (request.getYoutubeUrl() != null && !request.getYoutubeUrl().equals(oldMusic.getYoutubeUrl())) {
            oldMusic.setYoutubeUrl(request.getYoutubeUrl());
        }
        if (request.getSoopUrl() != null && !request.getSoopUrl().equals(oldMusic.getSoopUrl())) {
            oldMusic.setSoopUrl(request.getSoopUrl());
        }
    }
}
