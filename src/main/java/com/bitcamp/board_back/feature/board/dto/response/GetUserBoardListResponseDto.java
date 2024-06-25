package com.bitcamp.board_back.feature.board.dto.response;

import java.util.List;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.board.dto.object.BoardListItem;
import com.bitcamp.board_back.feature.board.entity.BoardListViewEntity;

import lombok.Getter;

@Getter
public class GetUserBoardListResponseDto extends ApiResponse {

    private List<BoardListItem> userBoardList;

    private GetUserBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.userBoardList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
