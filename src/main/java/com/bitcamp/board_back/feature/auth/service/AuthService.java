package com.bitcamp.board_back.feature.auth.service;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.jwt.dto.JwtReissueResponseDto;
import com.bitcamp.board_back.jwt.dto.JwtRequestDto;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.auth.dto.request.CheckCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.EmailCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.IdCheckRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignInRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignUpRequestDto;
import com.bitcamp.board_back.feature.auth.dto.response.CheckCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.EmailCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.IdCheckResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignInResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignUpResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {

    @Transactional
    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    @Transactional
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    @Transactional
    ResponseEntity<ApiResponse> signOut(JwtRequestDto dto);

    @Transactional
    ResponseEntity<JwtReissueResponseDto> reissue(final JwtRequestDto tDto);

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);
}
