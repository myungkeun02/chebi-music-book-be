package com.example.chebimusicbookbe.domain.music.service;

import com.example.chebimusicbookbe.domain.music.exception.MusicNotFoundException;
import com.example.chebimusicbookbe.domain.music.model.Music;
import com.example.chebimusicbookbe.domain.music.repository.MusicRepository;
import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.AllMusicResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

// todo: redis 연동
// todo: authorization 접근 제한 (생성, 수정, 삭제 등)
// todo: 유틸리티 클래스
// todo: pagination 구현

public class MusicServiceImpl implements MusicService {
    private final MusicRepository musicRepository;

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
                        .build());
        return MusicResponse.builder()
                .id(music.getId())
                .title(music.getTitle())
                .artist(music.getArtist())
                .albumArtUrl(music.getAlbumArtUrl())
                .category(music.getCategory())
                .youtubeUrl(music.getYoutubeUrl())
                .soopUrl(music.getSoopUrl())
                .build();
    }

    @Override
    public MusicResponse findMusicById(Long id) {
        Music music = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));
        return MusicResponse.builder()
                .id(music.getId())
                .title(music.getTitle())
                .artist(music.getArtist())
                .albumArtUrl(music.getAlbumArtUrl())
                .category(music.getCategory())
                .youtubeUrl(music.getYoutubeUrl())
                .soopUrl(music.getSoopUrl())
                .build();
    }

    @Override
    public AllMusicResponse findAllMusic() {
        List<Music> musics = musicRepository.findAll();
        return AllMusicResponse.builder()
                .musicList(musics)
                .build();
    }

    @Transactional
    @Override
    public MusicResponse updateMusicById(Long id, UpdateMusicByIdRequest updateMusicByIdRequest) {
        Music oldMusic = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));
        if (updateMusicByIdRequest.getTitle() != null && !updateMusicByIdRequest.getTitle().equals(oldMusic.getTitle())) {
            oldMusic.setTitle(updateMusicByIdRequest.getTitle());
        }
        if (updateMusicByIdRequest.getArtist() != null && !updateMusicByIdRequest.getArtist().equals(oldMusic.getArtist())) {
            oldMusic.setArtist(updateMusicByIdRequest.getArtist());
        }
        if (updateMusicByIdRequest.getAlbumArtUrl() != null && !updateMusicByIdRequest.getAlbumArtUrl().equals(oldMusic.getAlbumArtUrl())) {
            oldMusic.setAlbumArtUrl(updateMusicByIdRequest.getAlbumArtUrl());
        }
        if (updateMusicByIdRequest.getCategory() != null && !updateMusicByIdRequest.getCategory().equals(oldMusic.getCategory())) {
            oldMusic.setCategory(updateMusicByIdRequest.getCategory());
        }
        if (updateMusicByIdRequest.getYoutubeUrl() != null && !updateMusicByIdRequest.getYoutubeUrl().equals(oldMusic.getYoutubeUrl())) {
            oldMusic.setYoutubeUrl(updateMusicByIdRequest.getYoutubeUrl());
        }
        if (updateMusicByIdRequest.getSoopUrl() != null && !updateMusicByIdRequest.getSoopUrl().equals(oldMusic.getSoopUrl())) {
            oldMusic.setSoopUrl(updateMusicByIdRequest.getSoopUrl());
        }
        Music updatedMusic = musicRepository.save(oldMusic);

        return MusicResponse.builder()
                .id(updatedMusic.getId())
                .title(updatedMusic.getTitle())
                .albumArtUrl(updatedMusic.getAlbumArtUrl())
                .category(updatedMusic.getCategory())
                .artist(updatedMusic.getArtist())
                .youtubeUrl(updatedMusic.getYoutubeUrl())
                .soopUrl(updatedMusic.getSoopUrl())
                .build();
    }

    @Transactional
    @Override
    public void deleteMusicById(Long id) {
        Music oldMusic = musicRepository.findById(id)
                .orElseThrow(() -> new MusicNotFoundException("[ERROR] Music Not Found"));
        musicRepository.delete(oldMusic);
    }
}
