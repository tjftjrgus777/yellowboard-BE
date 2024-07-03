package com.bitcamp.board_back.feature.play.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.play.dto.request.PostPlayRequestDto;
import com.bitcamp.board_back.feature.play.dto.response.PostPlayResponseDto;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

public interface PlayService {

    ResponseEntity<? super PostPlayResponseDto> postPlay(PostPlayRequestDto dto, AccountUserDetails accountUserDetails);
}
