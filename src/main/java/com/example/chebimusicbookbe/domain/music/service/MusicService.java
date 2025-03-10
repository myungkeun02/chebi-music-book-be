package com.example.chebimusicbookbe.domain.music.service;

import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicListWithPagingResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import org.springframework.data.domain.Pageable;

public interface MusicService {
    MusicResponse createMusic(CreateMusicRequest request);
    MusicResponse findMusicById(Long id);
    MusicListResponse findAllMusic();
    MusicResponse updateMusicById(Long id, UpdateMusicByIdRequest updateMusicByIdRequest);
    MusicListWithPagingResponse findAllMusicWithPaging(Pageable pageable);
    void deleteMusicById(Long id);
}
