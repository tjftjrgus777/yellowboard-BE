package com.bitcamp.board_back.service;


import com.bitcamp.board_back.feature.auth.dto.oauth.OAuthTokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.bitcamp.board_back.constant.AuthConstant.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * Redis에 저장된 데이터(Key:Value) 가져오기
     * @param key 저장할 데이터의 Key
     * @return 데이터
     */
    public String getData(String key) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    /**
     * Redis에 데이터 저장 및 만료 시간 설정
     * @param key 저장할 데이터의 Key
     * @param value 저장할 데이터
     * @param duration 만료 시간
     */
    public void setDataExpire(String key, String value, long duration) {
        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);

        try {
            log.debug("Setting data in Redis: key={}, value={}, duration={} seconds", key, value, duration);
            valueOperations.set(key, value, expireDuration);
            log.debug("Data set successfully in Redis: key={}", key);
        } catch (Exception e) {
            log.error("Failed to set data in Redis: key={}, value={}, duration={} seconds", key, value, duration, e);
            throw e; // 혹은 적절한 예외 처리
        }
    }

    /**
     * Redis에 해당 Key를 가진 데이터가 존재하는지 확인
     * @param key 확인할 데이터의 Key
     * @return 데이터 존재 여부
     */
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    /**
     * Redis에 저장된 데이터 삭제
     * @param key 삭제할 데이터의 Key
     */
    public void deleteData(String key) {
        stringRedisTemplate.delete(key);
    }


    // ==================== REDIS - OAUTH ==================== //

    /**
     * 카카오 OAuth 인증 토큰을 Redis에 저장한다.
     *
     * @param oAuthTokenResponseDto OAuth 인증 토큰
     * @param email                 사용자 이메일
     */
    public void saveKakaoTokenToRedis(
            final OAuthTokenResponseDto oAuthTokenResponseDto,
            final String email
    ) {
        String accessToken = oAuthTokenResponseDto.getAccessToken();
        Long expiresIn = oAuthTokenResponseDto.getExpiresIn();

        String refreshToken = oAuthTokenResponseDto.getRefreshToken();
        Long refreshTokenExpiresIn = oAuthTokenResponseDto.getRefreshTokenExpiresIn();

        setDataExpire(OAUTH_KAKAO_PREFIX + email, accessToken, expiresIn);
        setDataExpire(OAUTH_KAKAO_REFRESH_PREFIX + email, refreshToken, refreshTokenExpiresIn);
    }

    /**
     * 네이버 OAuth 인증 토큰을 Redis에 저장한다.
     *
     * @param oAuthTokenResponseDto OAuth 인증 토큰
     * @param email                 사용자 이메일
     */
    public void saveNaverTokenToRedis(
            final OAuthTokenResponseDto oAuthTokenResponseDto,
            final String email
    ) {
        String accessToken = oAuthTokenResponseDto.getAccessToken();
        Long expiresIn = oAuthTokenResponseDto.getExpiresIn();

        String refreshToken = oAuthTokenResponseDto.getRefreshToken();

        setDataExpire(OAUTH_NAVER_PREFIX + email, accessToken, expiresIn);
        setDataExpire(OAUTH_NAVER_REFRESH_PREFIX + email, refreshToken, REFRESH_TOKEN_EXPIRE_TIME);
    }

    /**
     * Redis에 저장된 카카오 OAuth 인증 토큰을 삭제한다.
     *
     * @param email 사용자 이메일
     */
    public void deleteKakaoTokenFromRedis(final String email) {
        deleteData(OAUTH_KAKAO_PREFIX + email);
        deleteData(OAUTH_KAKAO_REFRESH_PREFIX + email);
    }

    /**
     * Redis에 저장된 네이버 OAuth 인증 토큰을 삭제한다.
     *
     * @param email 사용자 이메일
     */
    public void deleteNaverTokenFromRedis(final String email) {
        deleteData(OAUTH_NAVER_PREFIX + email);
        deleteData(OAUTH_NAVER_REFRESH_PREFIX + email);
    }
}
