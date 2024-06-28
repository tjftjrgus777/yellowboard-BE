package com.bitcamp.board_back.feature.board.controller;

import com.bitcamp.board_back.feature.board.dto.request.PatchBoardRequestDto;
import com.bitcamp.board_back.feature.board.dto.request.PostBoardRequestDto;
import com.bitcamp.board_back.feature.board.dto.request.PostCommentRequestDto;
import com.bitcamp.board_back.feature.board.dto.response.*;
import com.bitcamp.board_back.feature.board.service.BoardService;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        return boardService.getBoard(boardNumber);
    }

    @GetMapping("/{boardNumber}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.getFavoriteList(boardNumber);
    }

    @GetMapping("/{boardNumber}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(
        @PathVariable("boardNumber") Integer boardNumber
    ){
        return boardService.getCommentList(boardNumber);
    }

    @GetMapping("/{boardNumber}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(
        @PathVariable("boardNumber") Integer boardNumber
    ) {
        return boardService.increaseViewCount(boardNumber);
    }

    @GetMapping("latest-list")
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        return boardService.getLatestBoardList();
    }

    @GetMapping("/top3")
    public  ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        return boardService.getTop3BoardList();
    }

    @GetMapping(value ={"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(
        @PathVariable("searchWord") String searchWord,
        @PathVariable(value = "preSearchWord", required = false) String preSearchWord
    ) {
        return boardService.getSearchBoardList(searchWord, preSearchWord);
    }

    @GetMapping("/user-board-list/{email}")
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardLsit(
        @PathVariable("email") String email
    ) {
        return boardService.getUserResponseList(email);
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(
            @RequestBody @Valid PostBoardRequestDto requestBody,
            @AuthenticationPrincipal AccountUserDetails accountUserDetails
            ) {
        return boardService.postBoard(requestBody, accountUserDetails);
    }

    @PostMapping("/{boardNumber}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(
        @RequestBody @Valid PostCommentRequestDto requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return boardService.postComment(requestBody, boardNumber, accountUserDetails);
    }

    @PutMapping("/{boardNumber}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return boardService.putFavorite(boardNumber, accountUserDetails);
    }

    @PatchMapping("/{boardNumber}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(
        @RequestBody @Valid PatchBoardRequestDto requestBody,
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return boardService.patchBoard(requestBody, boardNumber, accountUserDetails);
    }

    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(
        @PathVariable("boardNumber") Integer boardNumber,
        @AuthenticationPrincipal AccountUserDetails accountUserDetails
    ) {
        return boardService.deleteBoard(boardNumber, accountUserDetails);
    }

}
