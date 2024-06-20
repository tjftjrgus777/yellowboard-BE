package com.bitcamp.board_back.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.dto.request.board.PatchBoardRequestDto;
import com.bitcamp.board_back.dto.request.board.PostBoardRequestDto;
import com.bitcamp.board_back.dto.request.board.PostCommentRequesetDto;
import com.bitcamp.board_back.dto.response.board.DeleteBoardResponseDto;
import com.bitcamp.board_back.dto.response.board.GetBoardResponseDto;
import com.bitcamp.board_back.dto.response.board.GetCommentListResponseDto;
import com.bitcamp.board_back.dto.response.board.GetFavoriteListResponseDto;
import com.bitcamp.board_back.dto.response.board.GetLatestBoardListResponseDto;
import com.bitcamp.board_back.dto.response.board.GetTop3BoardListResponseDto;
import com.bitcamp.board_back.dto.response.board.IncreaseViewCountResponseDto;
import com.bitcamp.board_back.dto.response.board.PatchBoardResponseDto;
import com.bitcamp.board_back.dto.response.board.PostBoardResponseDto;
import com.bitcamp.board_back.dto.response.board.PostCommentResponseDto;
import com.bitcamp.board_back.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardNumber);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardNumber);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequesetDto dto, Integer boardNumber, String email); 
    ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardNumber, String email);
    ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(Integer boardNumber);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);
}
