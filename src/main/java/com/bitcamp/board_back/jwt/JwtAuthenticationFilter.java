package com.bitcamp.board_back.jwt;

import static com.bitcamp.board_back.common.enums.ApiStatus.*;
import static com.bitcamp.board_back.constant.AuthConstant.*;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bitcamp.board_back.exception.AuthException;
import com.bitcamp.board_back.service.RedisService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String userToken = jwtProvider.resolveToken(request);

        // 토큰이 존재할 때만 검증 - 인증을 요구하지 않는 API에 대한 처리
        if (userToken != null) {
            checkIfTokenInvalidated(userToken);
            jwtProvider.validateToken(userToken);

            Claims userInfo = jwtProvider.getInfoFromToken(userToken);
            setAuthentication(userInfo.getSubject());
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String email) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtProvider.createAuthentication(email);
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private void checkIfTokenInvalidated(String token) {
        if (redisService.hasKey(BLACK_LIST_KEY_PREFIX + token)) {
            throw new AuthException(BLACKLISTED_TOKEN);
        }
    }

}
