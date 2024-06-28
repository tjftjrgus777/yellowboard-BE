package com.bitcamp.board_back.feature.auth.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

public class CheckCertificationResponseDto extends ApiResponse{

    private CheckCertificationResponseDto (String code , String message) {
        super(code , message);
    }

    public static ResponseEntity<ApiResponse> success() {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto("su", "success");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
    
}
