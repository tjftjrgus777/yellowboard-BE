package com.bitcamp.board_back.feature.user.dto.response;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

public class PatchNicknameResponseDto extends ApiResponse {

    private PatchNicknameResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        PatchNicknameResponseDto result = new PatchNicknameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
