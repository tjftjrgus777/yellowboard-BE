package com.bitcamp.board_back.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.board_back.dto.request.auth.SignInRequestDto;
import com.bitcamp.board_back.dto.request.auth.SignUpRequesetDto;
import com.bitcamp.board_back.dto.response.auth.SignInResponseDto;
import com.bitcamp.board_back.dto.response.auth.SignUpResponseDto;
import com.bitcamp.board_back.service.AuthService;

import lombok.RequiredArgsConstructor;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
        @RequestBody @Valid SignUpRequesetDto requestBody
        ) {
            ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
            return response;
    }
    
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @RequestBody @Valid SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
    
}
