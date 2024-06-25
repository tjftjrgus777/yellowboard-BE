package com.bitcamp.board_back.feature.board.dto.response;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

public class DeleteBoardResponseDto extends ApiResponse {

    private DeleteBoardResponseDto() {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
    }

    public static ResponseEntity<ApiResponse> success() {
        DeleteBoardResponseDto result = new DeleteBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
