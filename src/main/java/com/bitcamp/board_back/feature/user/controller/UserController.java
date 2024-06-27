package com.bitcamp.board_back.feature.user.controller;

import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
import com.bitcamp.board_back.feature.user.dto.request.PatchNicknameRequestDto;
import com.bitcamp.board_back.feature.user.dto.request.PatchProfileImageRequestDto;
import com.bitcamp.board_back.feature.user.dto.response.GetSignInUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.GetUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchNicknameResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchProfileImageResponseDto;
import com.bitcamp.board_back.feature.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserResponseDto> getUser(
        @PathVariable("email") String email
    ) {
        return userService.getUser(email);
    }

    @GetMapping("")
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(
            @AuthenticationPrincipal AccountUserDetails accountUserDetails
            ){
        return userService.getSignInUser(accountUserDetails);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
        @RequestBody @Valid PatchNicknameRequestDto requestBody,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return userService.patchNickname(requestBody, accountUserDetails);
    }

    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
        @RequestBody @Valid PatchProfileImageRequestDto requestBody,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return userService.patchProfileImage(requestBody, accountUserDetails);
    }
}
