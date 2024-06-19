package com.bitcamp.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitcamp.board_back.entity.FavoriteEntity;
import com.bitcamp.board_back.entity.primarykey.FavoritePk;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk>{

    FavoriteEntity findByBoardNumberAndUserEmail(Integer boardNumber,String userEmail);
    
}
