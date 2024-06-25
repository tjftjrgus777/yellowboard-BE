package com.bitcamp.board_back.feature.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcamp.board_back.feature.search.dto.response.GetPopularListResponseDto;
import com.bitcamp.board_back.feature.search.dto.response.GetRelationListResponseDto;
import com.bitcamp.board_back.feature.search.service.SearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        ResponseEntity<? super GetPopularListResponseDto> response = searchService.getPopularList();
        return response;
    }

    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(
        @PathVariable("searchWord") String searchWord
    ) {
        ResponseEntity<? super GetRelationListResponseDto> response = searchService.getRelatioList(searchWord);
        return response;
    }
    
}
