package com.bitcamp.board_back.feature.auth.entity;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User{

    // private String socialId;
    private Map<String, Object> attributes;  // 사용자 속성을 저장하는 필드
    private String email;


    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
      
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
      
    }
    @Override
    public String getName() {
        return this.email;
      
    }
    
}
