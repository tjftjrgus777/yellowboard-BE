package com.bitcamp.board_back.feature.user.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.user.dto.request.PatchNicknameRequestDto;
import com.bitcamp.board_back.feature.user.dto.request.PatchProfileImageRequestDto;
import com.bitcamp.board_back.feature.user.dto.response.GetSignInUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.GetUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchNicknameResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchProfileImageResponseDto;

public interface UserService {
    
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);
}
