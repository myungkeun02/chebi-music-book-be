package com.example.chebimusicbookbe.domain.music.controller;

import com.example.chebimusicbookbe.domain.music.request.CreateMusicRequest;
import com.example.chebimusicbookbe.domain.music.request.UpdateMusicByIdRequest;
import com.example.chebimusicbookbe.domain.music.response.MusicListResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicListWithPagingResponse;
import com.example.chebimusicbookbe.domain.music.response.MusicResponse;
import com.example.chebimusicbookbe.domain.music.service.MusicService;
import com.example.chebimusicbookbe.global.constant.ResponseMessage;
import com.example.chebimusicbookbe.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/music") // 버전 오타 수정
@Tag(name = "01. Music - 음악", description = "음악 관련 API")
public class MusicController {

    private final MusicService musicService;

    @PostMapping
    @Operation(summary = "음악 등록", description = "request data 를 기반으로 새로운 음악을 등록합니다.")
    public ResponseEntity<BaseResponse<MusicResponse>> registerMusic(@RequestBody @Valid CreateMusicRequest req) {
        MusicResponse res = musicService.createMusic(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(res, HttpStatus.CREATED.value(), ResponseMessage.MUSIC_REGISTER_SUCCESS));
    }

    @GetMapping("/{id}")
    @Operation(summary = "음악 정보 조회", description = "request param id의 음악 정보를 조회합니다.")
    public ResponseEntity<BaseResponse<MusicResponse>> getMusic(@PathVariable Long id) {
        MusicResponse res = musicService.findMusicById(id);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.MUSIC_FETCH_SUCCESS));
    }

    @GetMapping("/all")
    @Operation(summary = "전체 음악 리스트 조회", description = "전체 음악 정보 리스트를 조회합니다.")
    public ResponseEntity<BaseResponse<MusicListResponse>> getAllMusic() {
        MusicListResponse res = musicService.findAllMusic();
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.MUSIC_FETCH_SUCCESS));
    }

    @PageableAsQueryParam
    @GetMapping
    @Operation(summary = "페이지네이션 음악 리스트 조회", description = "페이지네이션으로 음악 리스트를 조회합니다.")
    public ResponseEntity<BaseResponse<MusicListWithPagingResponse>> getAllMusicWithPaging (
            @PageableDefault(page =0, size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        MusicListWithPagingResponse res = musicService.findAllMusicWithPaging(pageable);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.MUSIC_FETCH_SUCCESS));
    }

    @PutMapping("/{id}")
    @Operation(summary = "음악 정보 수정", description = "request param id의 음악 정보를 request data 기반으로 수정합니다.")
    public ResponseEntity<BaseResponse<MusicResponse>> updateMusic(@PathVariable Long id, @RequestBody @Valid UpdateMusicByIdRequest req) {
        MusicResponse res = musicService.updateMusicById(id, req);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.MUSIC_UPDATE_SUCCESS));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "음악 정보 삭제", description = "request param id의 음악 정보를 삭제합니다.")
    public ResponseEntity<BaseResponse<Void>> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusicById(id);
        return ResponseEntity
                .ok(new BaseResponse<>(null, HttpStatus.OK.value(), ResponseMessage.MUSIC_DELETE_SUCCESS));
    }
}
