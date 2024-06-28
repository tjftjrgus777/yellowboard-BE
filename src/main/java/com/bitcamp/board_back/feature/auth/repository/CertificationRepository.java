package com.bitcamp.board_back.feature.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitcamp.board_back.feature.auth.entity.CertificationEntity;

public interface CertificationRepository extends JpaRepository<CertificationEntity, String>{

    CertificationEntity findByEmail(String email);
    
}
