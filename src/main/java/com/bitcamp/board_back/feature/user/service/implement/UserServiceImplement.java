package com.bitcamp.board_back.feature.user.service.implement;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.exception.DuplicatedException;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.bitcamp.board_back.common.enums.ApiStatus.DUPLICATE_EMAIL;

@Service
@RequiredArgsConstructor
public class UserServiceImplement  implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserResponseDto> getUser(String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            validateUserEntity(userEntity);
            return GetUserResponseDto.success(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(AccountUserDetails accountUserDetails) {
        try {
            UserEntity userEntity = userRepository.findByEmail(accountUserDetails.getUser().getEmail());
            validateUserEntity(userEntity);
            return GetSignInUserResponseDto.success(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, AccountUserDetails accountUserDetails) {
        try {
            UserEntity userEntity = userRepository.findByEmail(accountUserDetails.getUser().getEmail());
            validateUserEntity(userEntity);

            String nickname =dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname) return ApiResponse.duplicateNickname();

            userEntity.setNickname(nickname);
            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return PatchNicknameResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, AccountUserDetails accountUserDetails) {
        try {
            UserEntity userEntity = userRepository.findByEmail(accountUserDetails.getUser().getEmail());
            validateUserEntity(userEntity);

            String profileImage = dto.getProfileImage();
            userEntity.setProfileImage(profileImage);
            userRepository.save(userEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }
        return PatchProfileImageResponseDto.success();
    }


    // ======================= UTILITY METHODS ======================= //

    /**
     * UserEntity를 검증합니다.
     * @param userEntity 검증할 사용자 엔티티
     * @return 사용자가 존재하지 않는 경우 에러 응답을 포함하는 ResponseEntity를 반환하고, 그렇지 않으면 null을 반환합니다.
     */
    private ResponseEntity<ApiResponse> validateUserEntity(UserEntity userEntity) {
        if(userEntity == null) {
            return ApiResponse.notExistUser();
        }
        return null;
    }

    private void checkEmailIsDuplicated(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicatedException(DUPLICATE_EMAIL);
        }
    }

}
