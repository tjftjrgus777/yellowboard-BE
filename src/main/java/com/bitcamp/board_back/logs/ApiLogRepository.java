package com.bitcamp.board_back.logs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
}
