package com.bitcamp.board_back.exception;


import com.bitcamp.board_back.common.enums.ApiStatus;
import lombok.Getter;


@Getter
public class AuthException extends RuntimeException {

    private final int statusCode;
    private final String code;
    private final String message;

    public AuthException(ApiStatus status) {
        this.statusCode = status.getStatusCode();
        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
