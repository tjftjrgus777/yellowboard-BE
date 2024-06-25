package com.bitcamp.board_back.feature.auth.dto.response;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

import lombok.Getter;

@Getter
public class SignUpResponseDto extends ApiResponse {

    private SignUpResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        SignUpResponseDto result = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
