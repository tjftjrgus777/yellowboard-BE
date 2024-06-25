package com.bitcamp.board_back.feature.board.entity;

import com.bitcamp.board_back.feature.board.entity.primarykey.FavoritePk;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "favorite")
@Table(name = "favorite")
@IdClass(FavoritePk.class)
public class FavoriteEntity {
    @Id
    private String userEmail;
    @Id
    private int boardNumber;
}
