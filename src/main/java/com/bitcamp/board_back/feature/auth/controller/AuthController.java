package com.bitcamp.board_back.feature.auth.controller;

import com.bitcamp.board_back.feature.auth.dto.request.CheckCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.EmailCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.IdCheckRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignInRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignUpRequestDto;
import com.bitcamp.board_back.feature.auth.dto.response.CheckCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.EmailCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.IdCheckResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignInResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignOutResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignUpResponseDto;
import com.bitcamp.board_back.feature.auth.service.AuthService;
import com.bitcamp.board_back.jwt.dto.JwtReissueResponseDto;
import com.bitcamp.board_back.jwt.dto.JwtRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ) {
        return authService.signUp(requestBody);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody
    ) {
        return authService.signIn(requestBody);
    }

    @PostMapping("/sign-out")
    public ResponseEntity<? super SignOutResponseDto> signOut(
            final @RequestBody JwtRequestDto requestBody
    ) {
        return authService.signOut(requestBody);
    }

    @PostMapping("/email-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
        @RequestBody @Valid IdCheckRequestDto requestBody
    ){
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification (
        @RequestBody @Valid EmailCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification (
        @RequestBody @Valid CheckCertificationRequestDto requestBody
    ) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("/re-issue")
    public ResponseEntity<JwtReissueResponseDto> reissue(
            final @RequestBody JwtRequestDto requestBody
    ) {
        return authService.reissue(requestBody);
    }
}
