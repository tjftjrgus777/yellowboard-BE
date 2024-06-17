package com.bitcamp.board_back.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.dto.request.auth.SignInRequestDto;
import com.bitcamp.board_back.dto.request.auth.SignUpRequesetDto;
import com.bitcamp.board_back.dto.response.auth.SignInResponseDto;
import com.bitcamp.board_back.dto.response.auth.SignUpResponseDto;

public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequesetDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
