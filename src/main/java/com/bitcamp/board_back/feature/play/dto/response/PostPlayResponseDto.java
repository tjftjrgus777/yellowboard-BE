package com.bitcamp.board_back.feature.play.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.common.enums.ApiStatus;

import lombok.Getter;

@Getter
public class PostPlayResponseDto extends ApiResponse {

    private PostPlayResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        PostPlayResponseDto result = new PostPlayResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
