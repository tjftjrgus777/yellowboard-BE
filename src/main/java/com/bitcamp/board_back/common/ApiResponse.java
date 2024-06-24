package com.bitcamp.board_back.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private String code;
    private String message;

    // ======================= 400 - BAD REQUEST ERRORS ======================= //
    public static ResponseEntity<ApiResponse> validationFailed() {
        ApiResponse responseBody = new ApiResponse(ResponseCode.VALIDATTION_FAILED, ResponseMessage.VALIDATTION_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
    public static ResponseEntity<ApiResponse> duplicateEmail(){
        ApiResponse result = new ApiResponse(ResponseCode.DUPLICATE_EMAIL, ResponseMessage.DUPLICATE_EMAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ApiResponse> duplicateNickname(){
        ApiResponse result = new ApiResponse(ResponseCode.DUPLICATE_NICKNAME, ResponseMessage.DUPLICATE_NICKNAME);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ApiResponse> duplicateTelNumber(){
        ApiResponse result = new ApiResponse(ResponseCode.DUPLICATE_TEL_NUMBER, ResponseMessage.DUPLICATE_TEL_NUMBER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ApiResponse> notExistBoard() {
        ApiResponse result = new ApiResponse(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ApiResponse> notExistUser() {
        ApiResponse result = new ApiResponse(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }


    // ======================= 401 - UNAUTHORIZED ERRORS ======================= //
    public static ResponseEntity<ApiResponse> signInFail(){
        ApiResponse result = new ApiResponse(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }


    // ======================= 403 - FORBIDDEN ERRORS ======================= //
    public static ResponseEntity<ApiResponse> noPermission() {
        ApiResponse result = new ApiResponse(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    // ======================= 500 - INTERNAL SERVER ERROR ======================= //
    public static ResponseEntity<ApiResponse> databaseError(){
        ApiResponse responseBody = new ApiResponse(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
