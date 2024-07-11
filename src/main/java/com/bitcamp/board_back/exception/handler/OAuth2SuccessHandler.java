package com.bitcamp.board_back.exception.handler;


import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bitcamp.board_back.feature.auth.entity.CustomOAuth2User;
import com.bitcamp.board_back.jwt.JwtProvider;
import com.bitcamp.board_back.jwt.dto.JwtResponseDto;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        String socialId = oAuth2User.getName();
        String email = (String) ((Map)oAuth2User.getAttributes().get("properties")).get("account_email");
        JwtResponseDto jwtResponseDto = jwtProvider.createJwtToken(email);
        String token = jwtResponseDto.getAccessToken();

        response.sendRedirect("http://localhost:3000/auth/oauth-response/" + token + "/3600");
       
      
    }

}
