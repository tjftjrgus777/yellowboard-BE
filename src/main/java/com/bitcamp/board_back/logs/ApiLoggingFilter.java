package com.bitcamp.board_back.logs;


import com.bitcamp.board_back.common.enums.ApiStatus;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ApiLoggingFilter extends OncePerRequestFilter implements HandlerInterceptor {

    private final ApiLogService apiLogService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);

        LocalDateTime startTime = LocalDateTime.now();

        filterChain.doFilter(wrappedRequest, wrappedResponse);

        LocalDateTime endTime = LocalDateTime.now();

        String requestPayload = getRequestPayload(wrappedRequest);
        String responsePayload = getResponsePayload(wrappedResponse);

        ApiStatus status = determineApiStatus(response.getStatus());

        ApiLog apiLog = ApiLog.builder()
                .timestamp(startTime)
                .method(request.getMethod())
                .endPoint(request.getRequestURI())
                .statusCode(response.getStatus())
                .apiStatusCode(status.getCode())
                .message(status.getMessage())
                .requestPayload(requestPayload)
                .responsePayload(responsePayload)
                .userId(request.getRemoteUser())
                .build();


        apiLogService.save(apiLog);

        wrappedResponse.copyBodyToResponse();
    }

    private String getRequestPayload(ContentCachingRequestWrapper request) {
        byte[]  buf = request.getContentAsByteArray();
        return buf.length > 0 ? new String(buf, 0, buf.length, StandardCharsets.UTF_8) : null;
    }

    private String getResponsePayload(ContentCachingResponseWrapper response) {
        byte[] buf = response.getContentAsByteArray();
        return buf.length > 0 ? new String(buf, 0, buf.length, StandardCharsets.UTF_8) : null;
    }
    private ApiStatus determineApiStatus(int statusCode) {
        switch (statusCode) {
            case 200:
                return ApiStatus.SUCCESS;
            case 400:
                return ApiStatus.VALIDATION_FAILED;
            case 401:
                return ApiStatus.SIGN_IN_FAIL;
            case 403:
                return ApiStatus.NO_PERMISSION;
            case 404:
                return ApiStatus.NOT_FOUND_ACCOUNT;
            case 500:
                return ApiStatus.DATABASE_ERROR;
            default:
                return ApiStatus.DATABASE_ERROR;
        }
    }
}
