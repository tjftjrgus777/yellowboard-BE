package com.bitcamp.board_back.constant;

public class AuthConstant {

    /**
     * 인증 관련 상수를 정의하는 유틸리티 클래스입니다.
     * <p>
     * 이 클래스는 인스턴스화할 수 없습니다. 클래스의 인스턴스를 생성하려고 하면 예외가 발생합니다.
     */

    public static final String BLACK_LIST_KEY_PREFIX = "JWT::BLACK_LIST::";
    public static final String AUTHORIZATION_HEADER="Authorization";
    public static final String AUTHORIZATION_KEY = "refreshToken";
    public static final String BEARER_PREFIX = "Bearer ";

    public static final long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 30 ; // 30분
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60; // 1시간

    // OAuth Constants - KAKAO
    public static final String KAKAO_AUTH_URL =  "https://kauth.kakao.com/oauth/token";
    public static final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    public static final String KAKAO_USER_ADDRESS_URL = "https://kapi.kakao.com/v1/user/shipping_address";
    public static final String KAKAO_USER_LOGOUT_URL = "https://kapi.kakao.com/v1/user/logout";

    public static final String KAKAO_REDIRECT_URI = "http://localhost:4000/oauth";
    public static final String OAUTH_KAKAO_PREFIX = "OAUTH::KAKAO::";
    public static final String OAUTH_KAKAO_REFRESH_PREFIX = "OAUTH::KAKAO::REFRESH::";
    public static final String GRANT_TYPE_AUTHORIZATION = "authorization_code";

    // OAuth Constants - NAVER
    public static final String OAUTH_NAVER_PREFIX = "OAUTH::NAVER::";
    public static final String OAUTH_NAVER_REFRESH_PREFIX = "OAUTH::NAVER::REFRESH::";
    public static final String NAVER_AUTH_URL = "https://nid.naver.com/oauth2.0/token";
    public static final String NAVER_USER_INFO_URL = "https://openapi.naver.com/v1/nid/me";

    private AuthConstant() {
        throw new IllegalStateException("유틸리티 클래스는 인스턴스화할 수 없습니다.");
    }

}
