package com.example.chebimusicbookbe.domain.music.service;

import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.AllMusicResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;

public interface MusicService {
    MusicResponse createMusic(CreateMusicRequest request);
    MusicResponse findMusicById(Long id);
    AllMusicResponse findAllMusic();
    MusicResponse updateMusicById(Long id, UpdateMusicByIdRequest updateMusicByIdRequest);
    void deleteMusicById(Long id);
}
