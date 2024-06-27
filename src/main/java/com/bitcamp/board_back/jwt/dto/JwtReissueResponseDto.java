package com.bitcamp.board_back.jwt.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.bitcamp.board_back.constant.AuthConstant.AUTHORIZATION_HEADER;
import static com.bitcamp.board_back.constant.AuthConstant.BEARER_PREFIX;

@Builder
public record JwtReissueResponseDto (
        String accessToken,
        String refreshToken,
        Long accessTokenExpiresIn
){

    public static ResponseEntity<JwtReissueResponseDto> success(JwtRequestDto requestDto, JwtResponseDto responseDto) {
        return ResponseEntity
                .ok()
                .header(AUTHORIZATION_HEADER, BEARER_PREFIX + responseDto.getAccessToken())
                .body(
                        JwtReissueResponseDto.builder()
                                .accessToken(responseDto.getAccessToken())
                                .refreshToken(requestDto.getRefreshToken())
                                .accessTokenExpiresIn(responseDto.getAccessTokenExpiresIn())
                                .build()
                );
    }
}
