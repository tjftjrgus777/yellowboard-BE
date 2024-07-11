package com.bitcamp.board_back.feature.auth.service.implement;

import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.bitcamp.board_back.feature.auth.entity.CustomOAuth2User;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OAuth2UserServiceImplement extends DefaultOAuth2UserService{
    
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(request);
        String oauthClientName = request.getClientRegistration().getClientName();

        try {
            System.out.println(new ObjectMapper().writeValueAsString(oAuth2User.getAttributes()));

        } catch (Exception exception) {
            exception.printStackTrace();
        }

        UserEntity userEntity = null;
        String socialId = null;
        String nickname = null;
        String email = null;
   

        if (oauthClientName.equals("kakao")) {
            socialId = "kakao" + oAuth2User.getAttributes().get("id");
            nickname = (String) ((Map)oAuth2User.getAttributes().get("properties")).get("nickname");
            email = (String) ((Map)oAuth2User.getAttributes().get("properties")).get("account_email");
            userEntity = new UserEntity(socialId, email, nickname,  "kakao");

        }

        if (oauthClientName.equals("naver")) {
            Map<String, String> responseMap = (Map<String, String>) oAuth2User.getAttributes().get("response");
            
            socialId = "naver_" + responseMap.get("id").substring(0,14);
            email = responseMap.get("email");
            userEntity = new UserEntity(socialId , email, nickname, "naver");
        }

        userRepository.save(userEntity);

        return new CustomOAuth2User(socialId, oAuth2User.getAttributes());
    }
}
