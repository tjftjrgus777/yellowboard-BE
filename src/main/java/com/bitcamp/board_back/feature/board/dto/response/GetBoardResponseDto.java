package com.bitcamp.board_back.feature.board.dto.response;
import java.util.ArrayList;
import java.util.List;

import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.board.entity.ImageEntity;
import com.bitcamp.board_back.feature.board.repository.resultSet.GetBoardResultSet;

import lombok.Getter;

@Getter
public class GetBoardResponseDto extends ApiResponse {

    private int boardNumber;
    private String title;
    private String content;
    private List<String> boardImageList;
    private String writeDatetime;
    private String writerEmail;
    private String writerNickname;
    private String writerProfileImage;

    private GetBoardResponseDto(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity: imageEntities){
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }


        this.boardNumber = resultSet.getBoardNumber();
        this.title = resultSet.getTitle();
        this.content = resultSet.getContent();
        this.boardImageList = boardImageList;
        this.writeDatetime = resultSet.getWriterDatetime();
        this.writerEmail = resultSet.getWriterEmail();
        this.writerNickname = resultSet.getWriterNickname();
        this.writerProfileImage = resultSet.getWriterProfileImage();
    }

    public static ResponseEntity<GetBoardResponseDto> success(GetBoardResultSet resultSet, List<ImageEntity> imageEntities) {
        GetBoardResponseDto result = new GetBoardResponseDto(resultSet,imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
