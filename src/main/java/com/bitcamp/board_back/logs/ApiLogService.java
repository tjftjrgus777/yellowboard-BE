package com.bitcamp.board_back.logs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiLogService {

    private final ApiLogRepository apiLogRepository;

    public void save(ApiLog apiLog) {
        apiLogRepository.save(apiLog);
    }
}
