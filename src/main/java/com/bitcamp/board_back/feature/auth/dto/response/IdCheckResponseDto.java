package com.bitcamp.board_back.feature.auth.dto.response;

import static com.bitcamp.board_back.common.enums.ApiStatus.SUCCESS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;



public class IdCheckResponseDto extends ApiResponse{

    private IdCheckResponseDto(String code, String message) {
        super(code, message);
        
    }

    public static ResponseEntity<ApiResponse > success() {
        IdCheckResponseDto responseBody = new IdCheckResponseDto("su", "success");
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

 
    

    
    
}
