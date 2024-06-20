package com.bitcamp.board_back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bitcamp.board_back.entity.SearchLogEntity;
import com.bitcamp.board_back.repository.resultSet.GetPopularListResultSet;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity,Integer>{

    @Query(
        value=
        "SELECT search_word as searchWord, count(search_word) AS count " +
        "FROM search_log " +
        "WHERE relation IS FALSE " +
        "GROUP BY search_word " +
        "ORDER BY count DESC " +
        "LIMIT 15 ",
        nativeQuery=true
    )

    List<GetPopularListResultSet> getPopularList();

    
}
