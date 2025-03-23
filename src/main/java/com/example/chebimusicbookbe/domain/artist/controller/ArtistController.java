package com.example.chebimusicbookbe.domain.artist.controller;

import com.example.chebimusicbookbe.domain.artist.request.CreateArtistRequest;
import com.example.chebimusicbookbe.domain.artist.request.UpdateArtistByIdRequest;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistListWithPagingResponse;
import com.example.chebimusicbookbe.domain.artist.response.ArtistResponse;
import com.example.chebimusicbookbe.domain.artist.service.ArtistService;
import com.example.chebimusicbookbe.global.constant.ResponseMessage;
import com.example.chebimusicbookbe.global.response.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "02. Artist - 아티스트", description = "아티스트 관련 API")
@RestController
@RequestMapping("/api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @Operation(summary = "아티스트 등록", description = "새로운 아티스트를 등록합니다.")
    @PostMapping
    public ResponseEntity<BaseResponse<ArtistResponse>> registerArtist(@Valid @RequestBody CreateArtistRequest req) {
        ArtistResponse res = artistService.registerArtist(req);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new BaseResponse<>(res, HttpStatus.CREATED.value(), ResponseMessage.ARTIST_REGISTER_SUCCESS));
    }

    @Operation(summary = "아티스트 조회", description = "ID로 아티스트를 조회합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<ArtistResponse>> getArtistById(@PathVariable Long id) {
        ArtistResponse res = artistService.getArtistById(id);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.ARTIST_FETCH_SUCCESS));
    }

    @Operation(summary = "아티스트 목록 조회", description = "모든 아티스트 목록을 조회합니다.")
    @GetMapping
    public ResponseEntity<BaseResponse<ArtistListResponse>> getAllArtists() {
        ArtistListResponse res = artistService.getAllArtists();
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.ARTIST_FETCH_SUCCESS));
    }

    @Operation(summary = "아티스트 페이지 조회", description = "페이지네이션된 아티스트 목록을 조회합니다.")
    @GetMapping("/page")
    public ResponseEntity<BaseResponse<ArtistListWithPagingResponse>> getArtistsWithPaging(
            @Parameter(description = "페이지 정보") @PageableDefault(size = 10) Pageable pageable) {
        ArtistListWithPagingResponse res = artistService.getArtistsWithPaging(pageable);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.ARTIST_FETCH_SUCCESS));
    }

    @Operation(summary = "아티스트 수정", description = "아티스트 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<ArtistResponse>> updateArtistById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateArtistByIdRequest req) {
        ArtistResponse res = artistService.updateArtistById(id, req);
        return ResponseEntity
                .ok(new BaseResponse<>(res, HttpStatus.OK.value(), ResponseMessage.ARTIST_UPDATE_SUCCESS));
    }

    @Operation(summary = "아티스트 삭제", description = "아티스트를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteArtistById(@PathVariable Long id) {
        artistService.deleteArtistById(id);
        return ResponseEntity
                .ok(new BaseResponse<>(null, HttpStatus.OK.value(), ResponseMessage.ARTIST_DELETE_SUCCESS));
    }
} 