package com.bitcamp.board_back.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bitcamp.board_back.common.ApiResponse;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResponse> validationExceptionHandler (Exception exception){
        return ApiResponse.validationFailed();

    }
}
