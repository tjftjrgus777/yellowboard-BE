package com.bitcamp.board_back.feature.play.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bitcamp.board_back.feature.play.entity.PlayEntity;

@Repository
public interface PlayRepository extends JpaRepository<PlayEntity, Integer> {

    boolean existsByPlayNumber(Integer playNumber);

    PlayEntity findByPlayNumber(Integer PlayNumber);

    // value=
    // "SELECT " +
    // "B.board_number AS boardNumber, " +
    // "B.title AS title, " +
    // "B.content AS content, " +
    // "B.write_datetime AS writeDatetime, " +
    // "B.writer_email AS writerEmail, " +
    // "U.nickname AS writerNickname, " +
    // "U.profile_image AS writerProfileImage " +
    // "FROM board AS B " +
    // "INNER JOIN user AS U " +
    // "ON B.writer_email = U.email " +
    // "WHERE board_number = ?1 ",
    // nativeQuery=true
    // )
    // GetBoardResultSet getBoard(Integer boardNumber);

}
