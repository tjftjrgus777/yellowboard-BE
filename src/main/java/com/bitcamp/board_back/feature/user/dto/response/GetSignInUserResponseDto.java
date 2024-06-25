package com.bitcamp.board_back.feature.user.dto.response;


import com.bitcamp.board_back.common.enums.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.user.entity.UserEntity;

import lombok.Getter;

@Getter
public class GetSignInUserResponseDto extends ApiResponse {

    private String email;
    private String nickname;
    private String profileImage;

    private GetSignInUserResponseDto(UserEntity userEntity){
        super(ApiStatus.SUCCESS.getCode(), ApiStatus.SUCCESS.getMessage());
        this.email = userEntity.getEmail();
        this.nickname = userEntity.getNickname();
        this.profileImage = userEntity.getProfileImage();
    }

    public static ResponseEntity<GetSignInUserResponseDto> success(UserEntity userEntity){
        GetSignInUserResponseDto result = new GetSignInUserResponseDto(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
