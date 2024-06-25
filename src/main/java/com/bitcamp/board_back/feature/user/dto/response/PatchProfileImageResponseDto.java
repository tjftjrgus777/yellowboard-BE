package com.bitcamp.board_back.feature.user.dto.response;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

import lombok.Getter;

@Getter
public class PatchProfileImageResponseDto extends ApiResponse {

    private PatchProfileImageResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        PatchProfileImageResponseDto result = new PatchProfileImageResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
