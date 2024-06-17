package com.bitcamp.board_back.provider;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtProvider {
    
    @Value("${secret-Key}")
    private String secretKey;

    public String create(String email) { //jwt 생성하는 매서드

        Date expiredDate  = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
        .compact();

        return jwt;
    }

    public String validate(String jwt){ // jwt 검증하는 매서드 
        
        Claims claims =null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(jwt).getBody();
        } catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    return  claims.getSubject();

    }

}
