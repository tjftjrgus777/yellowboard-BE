package com.bitcamp.board_back.logs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "api_logs")
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private String endPoint;

    @Column(nullable = false)
    private int statusCode;

    @Column(nullable = false)
    private String apiStatusCode;

    @Column(nullable = false)
    private String message;

    @Column(columnDefinition = "LONGTEXT")
    private String requestPayload;

    @Column(columnDefinition = "LONGTEXT")
    private String responsePayload;

    @Column
    private String userId;
}
