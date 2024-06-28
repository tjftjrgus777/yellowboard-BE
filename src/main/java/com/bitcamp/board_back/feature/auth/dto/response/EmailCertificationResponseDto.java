package com.bitcamp.board_back.feature.auth.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;

import lombok.Getter;

@Getter
public class EmailCertificationResponseDto extends ApiResponse {

    private EmailCertificationResponseDto(String code, String message) {
        super(code, message);
    }

    public static ResponseEntity<ApiResponse> success() {
        EmailCertificationResponseDto responseBody = new EmailCertificationResponseDto("su", "success");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    
}
