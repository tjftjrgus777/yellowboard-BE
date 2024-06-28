package com.bitcamp.board_back.common;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private String code;
    private String message;

    public static ResponseEntity<ApiResponse> of(ApiStatus status) {
        ApiResponse responseBody = new ApiResponse(status.getCode(), status.getMessage());
        return ResponseEntity.status(HttpStatus.valueOf(status.getStatusCode())).body(responseBody);
    }

    // 200 - Success
    public static ResponseEntity<ApiResponse> success() {
        return of(ApiStatus.SUCCESS);
    }

    // 400 - Bad Request Errors
    public static ResponseEntity<ApiResponse> validationFailed() {
        return of(ApiStatus.VALIDATION_FAILED);
    }

    public static ResponseEntity<ApiResponse> duplicateEmail() {
        return of(ApiStatus.DUPLICATE_EMAIL);
    }

    public static ResponseEntity<ApiResponse> duplicateNickname() {
        return of(ApiStatus.DUPLICATE_NICKNAME);
    }

    public static ResponseEntity<ApiResponse> duplicateTelNumber() {
        return of(ApiStatus.DUPLICATE_TEL_NUMBER);
    }

    public static ResponseEntity<ApiResponse> notExistUser() {
        return of(ApiStatus.NOT_EXISTED_USER);
    }

    public static ResponseEntity<ApiResponse> notExistBoard() {
        return of(ApiStatus.NOT_EXISTED_BOARD);
    }

    // 401 - Unauthorized Errors
    public static ResponseEntity<ApiResponse> signInFail() {
        return of(ApiStatus.SIGN_IN_FAIL);
    }

    // 403 - Forbidden Errors
    public static ResponseEntity<ApiResponse> noPermission() {
        return of(ApiStatus.NO_PERMISSION);
    }

    // 500 - Internal Server Error
    public static ResponseEntity<ApiResponse> databaseError() {
        return of(ApiStatus.DATABASE_ERROR);
    }

    public static ResponseEntity<ApiResponse> mailSendFail(){
        return of(ApiStatus.FAIL_TO_SEND_EMAIL);
    }
}
