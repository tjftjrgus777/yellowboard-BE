package com.bitcamp.board_back.feature.auth.dto.response;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class SignOutResponseDto extends ApiResponse {

    private SignOutResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        SignOutResponseDto result = new SignOutResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
