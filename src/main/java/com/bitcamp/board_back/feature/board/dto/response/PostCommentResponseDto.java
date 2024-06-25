package com.bitcamp.board_back.feature.board.dto.response;


import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

import lombok.Getter;

@Getter
public class PostCommentResponseDto extends ApiResponse {

    private PostCommentResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        PostCommentResponseDto result = new PostCommentResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
