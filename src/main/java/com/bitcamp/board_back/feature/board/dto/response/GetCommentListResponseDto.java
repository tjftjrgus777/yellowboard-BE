package com.bitcamp.board_back.feature.board.dto.response;

import java.util.List;


import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.board.dto.object.CommentListItem;
import com.bitcamp.board_back.feature.board.repository.resultSet.GetCommentListResultSet;

import lombok.Getter;

@Getter
public class GetCommentListResponseDto extends ApiResponse {

    private List<CommentListItem> commentList;

    private GetCommentListResponseDto(List<GetCommentListResultSet> resultSets) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.commentList = CommentListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetCommentListResponseDto> success(List<GetCommentListResultSet> resultSets) {
        GetCommentListResponseDto result = new GetCommentListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
