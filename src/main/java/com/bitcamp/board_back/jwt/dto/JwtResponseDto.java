package com.bitcamp.board_back.jwt.dto;

import com.bitcamp.board_back.common.ApiResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter
public class JwtResponseDto {

    String grantType;
    String accessToken;
    String refreshToken;
    Long accessTokenExpiresIn;

    @Builder
    public JwtResponseDto(String grantType, String accessToken, String refreshToken, Long accessTokenExpiresIn) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }
}
