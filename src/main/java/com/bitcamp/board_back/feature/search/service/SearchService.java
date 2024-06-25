package com.bitcamp.board_back.feature.search.service;

import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.feature.search.dto.response.GetPopularListResponseDto;
import com.bitcamp.board_back.feature.search.dto.response.GetRelationListResponseDto;


public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelatioList(String seawrchWord);
}
