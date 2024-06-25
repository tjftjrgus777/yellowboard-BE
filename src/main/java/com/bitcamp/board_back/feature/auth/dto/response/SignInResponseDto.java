package com.bitcamp.board_back.feature.auth.dto.response;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ApiResponse {

    private String token;
    private int expirationTime;

    private SignInResponseDto(String token){
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success(String token){
        SignInResponseDto result = new SignInResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
