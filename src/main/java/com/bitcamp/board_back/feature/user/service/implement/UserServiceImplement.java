package com.bitcamp.board_back.feature.user.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.feature.user.dto.request.PatchNicknameRequestDto;
import com.bitcamp.board_back.feature.user.dto.request.PatchProfileImageRequestDto;
import com.bitcamp.board_back.feature.user.dto.response.GetSignInUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.GetUserResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchNicknameResponseDto;
import com.bitcamp.board_back.feature.user.dto.response.PatchProfileImageResponseDto;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.repository.UserRepository;
import com.bitcamp.board_back.feature.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplement  implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return GetUserResponseDto.success(userEntity);
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {

        UserEntity userEntity = null;

        try {

            userEntity = userRepository.findByEmail(email);
            if (userEntity ==null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return GetSignInUserResponseDto.success(userEntity);

    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email) {

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) PatchNicknameResponseDto.notExistUser();

            String nickname =dto.getNickname();
            boolean existedNickname =userRepository.existsByNickname(nickname);
            if (existedNickname) return PatchNicknameResponseDto.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email) {

        try {

            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return PatchProfileImageResponseDto.notExistUser();

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return PatchProfileImageResponseDto.success();
    }



}
