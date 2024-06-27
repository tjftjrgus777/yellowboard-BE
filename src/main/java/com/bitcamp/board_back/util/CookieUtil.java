package com.bitcamp.board_back.util;

import org.springframework.http.ResponseCookie;

import static com.bitcamp.board_back.constant.AuthConstant.REFRESH_TOKEN_EXPIRE_TIME;

public class CookieUtil {

    private CookieUtil() {
        throw new IllegalStateException("유틸리티 클래스는 인스턴스화할 수 없습니다.");
    }

    public static ResponseCookie createCookie(String cookieName, String cookieValue) {
        return ResponseCookie.from(cookieName, cookieValue)
                .httpOnly(true)
                //.secure(true) https를 사용할 경우에만 활성화
                .path("/")
                .maxAge(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }
}
