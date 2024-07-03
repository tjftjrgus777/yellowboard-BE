package com.bitcamp.board_back.feature.play.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.board.dto.response.PostBoardResponseDto;
import com.bitcamp.board_back.feature.play.dto.request.PostPlayRequestDto;
import com.bitcamp.board_back.feature.play.dto.response.PostPlayResponseDto;
import com.bitcamp.board_back.feature.play.entity.PlayEntity;
import com.bitcamp.board_back.feature.play.repository.PlayRepository;
import com.bitcamp.board_back.feature.play.service.PlayService;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayServiceImplement implements PlayService {

    private final PlayRepository playRepository;

    @Override
    public ResponseEntity<? super PostPlayResponseDto> postPlay(PostPlayRequestDto dto,
            AccountUserDetails accountUserDetails) {

        try {
            String email = accountUserDetails.getUser().getEmail();
            // email 검증부분이 필요함

            PlayEntity playEntity = new PlayEntity(dto, email);
            playRepository.save(playEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();

        }

        return PostBoardResponseDto.success();

    }
}
