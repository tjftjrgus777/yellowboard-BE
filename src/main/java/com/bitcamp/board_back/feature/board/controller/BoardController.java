package com.bitcamp.board_back.feature.board.controller;

import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
import jakarta.validation.Valid;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.board_back.feature.board.dto.request.PatchBoardRequestDto;
import com.bitcamp.board_back.feature.board.dto.request.PostBoardRequestDto;
import com.bitcamp.board_back.feature.board.dto.request.PostCommentRequestDto;
import com.bitcamp.board_back.feature.board.dto.response.DeleteBoardResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetBoardResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetCommentListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetFavoriteListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetLatestBoardListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetSearchBoardListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetTop3BoardListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.GetUserBoardListResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.IncreaseViewCountResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.PatchBoardResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.PostBoardResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.PostCommentResponseDto;
import com.bitcamp.board_back.feature.board.dto.response.PutFavoriteResponseDto;
import com.bitcamp.board_back.feature.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetFavoriteListResponseDto> response =boardService.getFavoriteList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardNumber);
        return response;
    }

    @GetMapping("/{boardNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super IncreaseViewCountResponseDto> response =boardService.increaseViewCount(boardNumber);
        return response;
    }

    @GetMapping("latest-list")
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
        return response;
    }

    @GetMapping("/top3")
    public  ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        ResponseEntity<? super GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
        return response;
    }

    @GetMapping(value ={"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearChWord}"})
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
        @PathVariable("searchWord") String searchWord,
        @PathVariable(value = "preSearchWord", required = false) String preSearChWord
    ) {
        ResponseEntity<? super GetSearchBoardListResponseDto> response =boardService.getSearchBoardList(searchWord, preSearChWord);
        return response;
    }

    @GetMapping("/user-board-list/{email}")
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardLsit(
        @PathVariable("email") String email
    ) {
        ResponseEntity<? super GetUserBoardListResponseDto> response = boardService.getUserResponseList(email);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto RequestBody,
            @AuthenticationPrincipal AccountUserDetails accountUserDetails
            ) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(RequestBody, accountUserDetails);
        return response;
    }

    @PostMapping("/{boardNumber}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
        @RequestBody @Valid PostCommentRequestDto RequestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(RequestBody, boardNumber, accountUserDetails);
        return response;
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putFavorite(boardNumber, accountUserDetails);
        return response;
    }

    @PatchMapping("/{boardNumber}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
        @RequestBody @Valid PatchBoardRequestDto RequestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(RequestBody, boardNumber, accountUserDetails);
        return response;
    }

    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardNumber, accountUserDetails);
        return response;
    }


}
