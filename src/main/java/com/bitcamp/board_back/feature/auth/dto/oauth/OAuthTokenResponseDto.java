package com.bitcamp.board_back.feature.auth.dto.oauth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.Value;

@Value
public class OAuthTokenResponseDto {

    @JsonProperty("access_token")
    String accessToken;

    @JsonProperty("token_type")
    String tokenType;

    @JsonProperty("refresh_token")
    String refreshToken;

    @JsonProperty("expires_in")
    Long expiresIn;

    String scope;

    @Nullable
    @JsonProperty("refresh_token_expires_in")
    Long refreshTokenExpiresIn = 0L;

}
