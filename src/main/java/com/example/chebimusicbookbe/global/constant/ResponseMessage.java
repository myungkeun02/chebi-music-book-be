package com.example.chebimusicbookbe.global.constant;

/**
 * 전역 응답 메시지 상수 관리 클래스.
 *
 * 사용 예:
 *   return new BaseResponse<>(data, HttpStatus.OK.value(), ResponseMessage.MUSIC_REGISTER_SUCCESS);
 */
public class ResponseMessage {

    // 생성자 private: 인스턴스화 방지
    private ResponseMessage() {
        throw new IllegalStateException("Utility class");
    }

    // 🎵 MUSIC 관련 메시지
    public static final String MUSIC_REGISTER_SUCCESS = "음악이 등록되었습니다.";
    public static final String MUSIC_FETCH_SUCCESS = "음악 정보를 가져왔습니다.";
    public static final String MUSIC_UPDATE_SUCCESS = "음악 정보를 수정했습니다.";
    public static final String MUSIC_DELETE_SUCCESS = "음악 정보를 삭제했습니다.";

    // ✅ 공통 메시지 (추후 사용 시)
    public static final String INVALID_REQUEST = "잘못된 요청입니다.";
    public static final String INTERNAL_SERVER_ERROR = "서버 오류가 발생했습니다.";
    public static final String ACCESS_DENIED = "접근 권한이 없습니다.";

    // 👤 ARTIST 관련 메시지 (예시)
    public static final String ARTIST_REGISTER_SUCCESS = "아티스트가 등록되었습니다.";
    public static final String ARTIST_FETCH_SUCCESS = "아티스트 정보를 가져왔습니다.";
    public static final String ARTIST_UPDATE_SUCCESS = "아티스트 정보를 수정했습니다.";
    public static final String ARTIST_DELETE_SUCCESS = "아티스트 정보를 삭제했습니다.";

    // 🗂️ CATEGORY 관련 메시지 (예시)
    public static final String CATEGORY_REGISTER_SUCCESS = "카테고리가 등록되었습니다.";
    public static final String CATEGORY_FETCH_SUCCESS = "카테고리 정보를 가져왔습니다.";
    public static final String CATEGORY_UPDATE_SUCCESS = "카테고리 정보를 수정했습니다.";
    public static final String CATEGORY_DELETE_SUCCESS = "카테고리 정보를 삭제했습니다.";
}
