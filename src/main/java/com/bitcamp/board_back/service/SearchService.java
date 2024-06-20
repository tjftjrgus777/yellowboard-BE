package com.bitcamp.board_back.service;

import org.springframework.http.ResponseEntity;
import com.bitcamp.board_back.dto.response.search.GetPopularListResponseDto;
import com.bitcamp.board_back.dto.response.search.GetRelationListResponseDto;


public interface SearchService {
    
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelatioList(String seawrchWord);
}
