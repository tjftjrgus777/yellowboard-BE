package com.bitcamp.board_back.feature.user.service;

import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.user.dto.request.PatchNicknameRequestDto;
import com.bitcamp.board_back.feature.user.dto.request.PatchProfileImageRequestDto;
import com.bitcamp.board_back.feature.user.dto.response.GetSignInUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.GetUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchNicknameResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchProfileImageResponseDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional(readOnly = true)
    ResponseEntity<? super GetUserResponseDto> getUser(String email);

    @Transactional(readOnly = true)
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(AccountUserDetails accountUserDetails);

    @Transactional
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, AccountUserDetails accountUserDetails);

    @Transactional
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, AccountUserDetails accountUserDetails);
}
