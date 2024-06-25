package com.bitcamp.board_back.feature.board.dto.response;

import java.util.List;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.board.dto.object.FavoriteListItem;
import com.bitcamp.board_back.feature.board.repository.resultSet.GetFavoriteListResultSet;

import lombok.Getter;

@Getter
public class GetFavoriteListResponseDto extends ApiResponse {

    private List<FavoriteListItem> favoriteList;

    private GetFavoriteListResponseDto(List<GetFavoriteListResultSet> resultSets) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.favoriteList = FavoriteListItem.copyList(resultSets);
    }

    public static ResponseEntity<GetFavoriteListResponseDto> success(List<GetFavoriteListResultSet> resultSets) {
        GetFavoriteListResponseDto result = new GetFavoriteListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
