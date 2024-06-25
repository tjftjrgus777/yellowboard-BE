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
public class GetTop3BoardListResponseDto extends ApiResponse {

    private List<BoardListItem> top3List;

    private GetTop3BoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.top3List = BoardListItem.getList((boardListViewEntities));
    }

    public static ResponseEntity<GetTop3BoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities){
        GetTop3BoardListResponseDto result = new GetTop3BoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
