package com.bitcamp.board_back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.board_back.dto.response.user.GetSignInUserResponseDto;
import com.bitcamp.board_back.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
        @AuthenticationPrincipal String email 
    ){
        ResponseEntity<? super GetSignInUserResponseDto> response = userService.getSignInUser(email);
        return response;
    }
    
}
