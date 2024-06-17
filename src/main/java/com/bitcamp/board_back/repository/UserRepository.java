package com.bitcamp.board_back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitcamp.board_back.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{
    
}
