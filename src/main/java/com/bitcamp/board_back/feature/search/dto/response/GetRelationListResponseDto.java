package com.bitcamp.board_back.feature.search.dto.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ResponseCode;
import com.bitcamp.board_back.common.ResponseDto;
import com.bitcamp.board_back.common.ResponseMessage;
import com.bitcamp.board_back.feature.search.repository.resultSet.GetRelationListResultSet;

import lombok.Getter;

@Getter
public class GetRelationListResponseDto extends ResponseDto{
    
    private List<String> relativeWordList;

    private GetRelationListResponseDto(List<GetRelationListResultSet> resultSets) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> relativeWordList = new ArrayList<>();
        for (GetRelationListResultSet resultSet: resultSets) {
            String relativeWord = resultSet.getSearchWord();
            relativeWordList.add(relativeWord);
        }

        this.relativeWordList = relativeWordList;
    }

    public static ResponseEntity<GetRelationListResponseDto> success(List<GetRelationListResultSet> resultSets) {
        GetRelationListResponseDto result = new GetRelationListResponseDto(resultSets);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
