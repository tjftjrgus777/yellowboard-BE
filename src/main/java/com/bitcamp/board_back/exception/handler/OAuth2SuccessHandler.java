package com.bitcamp.board_back.exception.handler;


import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bitcamp.board_back.feature.auth.entity.CustomOAuth2User;
import com.bitcamp.board_back.jwt.JwtProvider;
import com.bitcamp.board_back.jwt.dto.JwtResponseDto;
import com.bitcamp.board_back.service.RedisService;

import static com.bitcamp.board_back.constant.AuthConstant.REFRESH_TOKEN_EXPIRE_TIME;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;
    private final RedisService redisService;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication
    ) throws IOException, ServletException {

        CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();

        String email = oAuth2User.getName();
        
        JwtResponseDto jwtResponseDto = jwtProvider.createJwtToken(email);
        redisService.setDataExpire(email, jwtResponseDto.getRefreshToken(), REFRESH_TOKEN_EXPIRE_TIME);
        String token = jwtResponseDto.getAccessToken();

        response.sendRedirect("http://localhost:3000/auth/oauth-response/" + token + "/3600");

       
      
    }

}
