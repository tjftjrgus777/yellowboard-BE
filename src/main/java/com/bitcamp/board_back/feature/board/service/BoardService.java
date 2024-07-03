package com.bitcamp.board_back.feature.board.service;

import org.springframework.http.ResponseEntity;

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
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);

    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);

    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);

    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();

    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();

    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);

    ResponseEntity<? super GetUserBoardListResponseDto> getUserResponseList(String email);

    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto,
            AccountUserDetails accountUserDetails);

    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardNumber,
            AccountUserDetails accountUserDetails);

    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber,
            AccountUserDetails accountUserDetails);

    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber,
            AccountUserDetails accountUserDetails);

    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);

    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber,
            AccountUserDetails accountUserDetails);
}
