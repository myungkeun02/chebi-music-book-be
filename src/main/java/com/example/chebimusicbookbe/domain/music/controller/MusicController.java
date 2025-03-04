package com.example.chebimusicbookbe.domain.music.controller;

import com.example.chebimusicbookbe.domain.music.exception.AlreadyRegisterMusicException;
import com.example.chebimusicbookbe.domain.music.exception.MusicNotFoundException;
import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.AllMusicResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import com.example.chebimusicbookbe.domain.music.service.MusicService;
import com.example.chebimusicbookbe.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/music")
@Tag(name = "01. Music - 음악", description = "음악 관련 API")

public class MusicController {
    private final MusicService musicService;

    @PostMapping
    @Operation(summary = "음악 등록", description = "request data 를 기반으로 새로운 음악을 등록합니다.")
    ResponseEntity<BaseResponse<MusicResponse>> registerMusic (@RequestBody @Valid CreateMusicRequest req) {
        try {
            MusicResponse res = musicService.createMusic(req);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new BaseResponse<>(res, HttpStatus.CREATED.value(), "음악이 등록되었습니다."));
        } catch (AlreadyRegisterMusicException e) {
            return ResponseEntity
                    .status(HttpStatus.ALREADY_REPORTED)
                    .body(new BaseResponse<>(null, HttpStatus.ALREADY_REPORTED.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "음악 정보 조회", description = "request param id의 음악 정보를 조회합니다.")
    ResponseEntity<BaseResponse<MusicResponse>> getMusic (@PathVariable Long id) {
        try {
            MusicResponse res = musicService.findMusicById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new BaseResponse<>(res, HttpStatus.OK.value(), "음악 정보를 가져왔습니다.."));
        } catch (MusicNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

    @GetMapping("/all")
    @Operation(summary = "음악 리스트 조회", description = "musicDB에 저장된 모든 음악 정보를 조회합니다.")
    ResponseEntity<BaseResponse<AllMusicResponse>> getAllMusic () {
        try {
            AllMusicResponse res = musicService.findAllMusic();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new BaseResponse<>(res, HttpStatus.OK.value(), "모든 음악 정보를 가져왔습니다.."));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "음악 정보 수정", description = "request param id의 음악 정보를 request data 기반으로 수정합니다.")
    ResponseEntity<BaseResponse<MusicResponse>> updateMusic (@PathVariable Long id, @RequestBody @Valid UpdateMusicByIdRequest req) {
        try {
            MusicResponse res = musicService.updateMusicById(id, req);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new BaseResponse<>(res, HttpStatus.OK.value(), "음악 정보를 수정했습니다.."));
        } catch (MusicNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (AlreadyRegisterMusicException e) {
            return ResponseEntity
                    .status(HttpStatus.ALREADY_REPORTED)
                    .body(new BaseResponse<>(null, HttpStatus.ALREADY_REPORTED.value(), e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new BaseResponse<>(null, HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "음악 정보 삭제", description = "request param id의 음악 정보를 삭제합니다.")
    ResponseEntity<BaseResponse<Void>> deleteMusic (@PathVariable Long id) {
        try {
            musicService.deleteMusicById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new BaseResponse<>(null, HttpStatus.OK.value(), "음악 정보를 삭제했습니다.."));
        } catch (MusicNotFoundException e) {
            return  ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(null, HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
    }

}

