package com.bitcamp.board_back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    private final RedisProperties redisProperties;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        String host = redisProperties.getHost();
        int port = redisProperties.getPort();
        //String user = redisProperties.getUser();
        //String password = redisProperties.getPassword();

        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                host, port
        );

        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }


    /**
     * <b>StringRedisTemplate</b><p>
     * - 문자열(String) 데이터를 주로 처리할 때 사용<p>
     * - 모든 키와 값이 문자열로 직렬화되므로 간단한 키-값 저장소로 Redis를 사용할 때 적합<p>
     * <p>
     * <b>RedisTemplate<String, Object> 사용 기준</b><p>
     * - 다양한 데이터 유형을 처리할 때 사용<p>
     * - 키는 문자열(String)로 직렬화되고, 값은 다양한 객체(Object) 유형을 담을 때 적합<p>
     * - 해시 구조를 포함한 복잡한 데이터 구조를 다루고자 할 때 유용<p>
     *<p>
     * - [캐시] 간단한 문자열 캐시를 구현할 때는 `StringRedisTemplate`을 사용
     * <p>
     * - [세션 저장소] 사용자의 세션 정보를 저장할 때는 `RedisTemplate<String, Object>`를 사용
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }
}
