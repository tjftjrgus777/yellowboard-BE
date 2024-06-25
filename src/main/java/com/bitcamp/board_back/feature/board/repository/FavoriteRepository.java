package com.bitcamp.board_back.feature.board.repository;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.bitcamp.board_back.feature.board.entity.FavoriteEntity;
import com.bitcamp.board_back.feature.board.entity.primarykey.FavoritePk;
import com.bitcamp.board_back.feature.board.repository.resultSet.GetFavoriteListResultSet;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);

    @Query(
        value=
        "SELECT " +
        "   U.email AS email, " +
        "   U.nickname AS nickname, " +
        "   U.profile_image AS profileImage " +
        "FROM favorite AS F " +
        "INNER JOIN user AS U " +
        "ON F.user_email = U.email " +
        "WHERE F.board_number = ?1",
        nativeQuery=true
    )
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardNumber);
    
    @Transactional
    void deleteByBoardNumber(Integer boardNumber);
}
