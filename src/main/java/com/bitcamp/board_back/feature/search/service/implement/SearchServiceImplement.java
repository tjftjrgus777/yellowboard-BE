package com.bitcamp.board_back.feature.search.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.search.dto.response.GetPopularListResponseDto;
import com.bitcamp.board_back.feature.search.dto.response.GetRelationListResponseDto;
import com.bitcamp.board_back.feature.search.repository.SearchLogRepository;
import com.bitcamp.board_back.feature.search.repository.resultSet.GetPopularListResultSet;
import com.bitcamp.board_back.feature.search.repository.resultSet.GetRelationListResultSet;
import com.bitcamp.board_back.feature.search.service.SearchService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService{

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<GetPopularListResultSet> resultSets = new ArrayList<>();

       try {

        resultSets = searchLogRepository.getPopularList();

       } catch (Exception exception) {
        exception.printStackTrace();
        return ApiResponse.databaseError();
       }

       return GetPopularListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelatioList(String seawrchWord) {

        List<GetRelationListResultSet> resultSets = new ArrayList<>();

        try {

            resultSets = searchLogRepository.getRelationList(seawrchWord);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return GetRelationListResponseDto.success(resultSets);
    }

}
