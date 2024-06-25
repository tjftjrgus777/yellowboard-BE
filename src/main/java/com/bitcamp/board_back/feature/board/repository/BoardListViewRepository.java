package com.bitcamp.board_back.feature.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitcamp.board_back.feature.board.entity.BoardListViewEntity;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {

    List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
    List<BoardListViewEntity> findTop3ByWriteDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDescWriteDatetimeDesc(String writeDatetime);
    List<BoardListViewEntity> findByTitleContainsOrContentContainsOrderByWriteDatetimeDesc(String title, String content);
    List<BoardListViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String writerEmail);
}
