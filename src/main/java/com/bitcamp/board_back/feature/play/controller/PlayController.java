package com.bitcamp.board_back.feature.play.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.board_back.feature.play.dto.request.PostPlayRequestDto;
import com.bitcamp.board_back.feature.play.dto.response.PostPlayResponseDto;
import com.bitcamp.board_back.feature.play.service.PlayService;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v2/play")
@RequiredArgsConstructor
public class PlayController {
    private final PlayService playService;

    @PostMapping("")
    public ResponseEntity<? super PostPlayResponseDto> postPlay(
            @RequestBody @Valid PostPlayRequestDto requestBody,
            @AuthenticationPrincipal AccountUserDetails accountUserDetails) {
        return playService.postPlay(requestBody, accountUserDetails);
    }
}
