package com.bitcamp.board_back.feature.auth.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.auth.dto.request.SignInRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignUpRequesetDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignInResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignUpResponseDto;

public interface AuthService {
    
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequesetDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
