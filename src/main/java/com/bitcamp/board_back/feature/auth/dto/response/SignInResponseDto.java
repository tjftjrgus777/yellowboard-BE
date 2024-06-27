package com.bitcamp.board_back.feature.auth.dto.response;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.common.enums.ApiStatus;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.enums.UserRole;
import com.bitcamp.board_back.jwt.dto.JwtResponseDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.bitcamp.board_back.constant.AuthConstant.AUTHORIZATION_KEY;
import static com.bitcamp.board_back.constant.AuthConstant.BEARER_PREFIX;
import static com.bitcamp.board_back.util.CookieUtil.createCookie;

@Getter
public class SignInResponseDto extends ApiResponse {

    String nickname;
    UserRole userRole;
    String accessToken;
    String refreshToken;
    String grantType;
    Long expirationTime;

    @Builder
    private SignInResponseDto(
            String nickname,
            UserRole userRole,
            String accessToken,
            String refreshToken,
            String grantType,
            Long expirationTime
    ) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.nickname = nickname;
        this.userRole = userRole;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.grantType = grantType;
        this.expirationTime = expirationTime;
    }

    public static ResponseEntity<SignInResponseDto> success(UserEntity user, JwtResponseDto jwtResponseDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.SET_COOKIE, createCookie(AUTHORIZATION_KEY, jwtResponseDto.getRefreshToken()).toString())
                .header(HttpHeaders.AUTHORIZATION, BEARER_PREFIX + jwtResponseDto.getAccessToken())
                .body(
                        SignInResponseDto.builder()
                                .nickname(user.getNickname())
                                .userRole(user.getUserRole())
                                .grantType(jwtResponseDto.getGrantType())
                                .accessToken(jwtResponseDto.getAccessToken())
                                .refreshToken(jwtResponseDto.getRefreshToken())
                                .expirationTime(jwtResponseDto.getAccessTokenExpiresIn())
                                .build()
                );
    }
}
