package com.bitcamp.board_back.feature.board.repository.resultSet;

public interface GetBoardResultSet {
    Integer getBoardNumber();
    String getTitle();
    String getContent();
    String getWriterDatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();


    
}
