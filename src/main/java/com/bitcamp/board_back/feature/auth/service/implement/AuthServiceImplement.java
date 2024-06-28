package com.bitcamp.board_back.feature.auth.service.implement;

import com.bitcamp.board_back.common.ApiResponse;
import com.bitcamp.board_back.common.CertificationNumber;
import com.bitcamp.board_back.exception.AuthException;
import com.bitcamp.board_back.exception.NotFoundException;
import com.bitcamp.board_back.feature.auth.dto.request.CheckCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.EmailCertificationRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.IdCheckRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignInRequestDto;
import com.bitcamp.board_back.feature.auth.dto.request.SignUpRequestDto;
import com.bitcamp.board_back.feature.auth.dto.response.CheckCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.EmailCertificationResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.IdCheckResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignInResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignOutResponseDto;
import com.bitcamp.board_back.feature.auth.dto.response.SignUpResponseDto;
import com.bitcamp.board_back.feature.auth.entity.CertificationEntity;
import com.bitcamp.board_back.feature.auth.repository.CertificationRepository;
import com.bitcamp.board_back.feature.auth.service.AuthService;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.repository.UserRepository;
import com.bitcamp.board_back.jwt.JwtProvider;
import com.bitcamp.board_back.jwt.dto.JwtReissueResponseDto;
import com.bitcamp.board_back.jwt.dto.JwtRequestDto;
import com.bitcamp.board_back.jwt.dto.JwtResponseDto;
import com.bitcamp.board_back.provider.EmailProvider;
import com.bitcamp.board_back.service.RedisService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.bitcamp.board_back.common.enums.ApiStatus.EXPIRED_REFRESH_TOKEN;
import static com.bitcamp.board_back.common.enums.ApiStatus.NOT_EXISTED_USER;
import static com.bitcamp.board_back.constant.AuthConstant.*;
import static org.springframework.beans.propertyeditors.CustomBooleanEditor.VALUE_TRUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository;
    private final JwtProvider jwtProvider;
    private final RedisService redisService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EmailProvider emailProvider;

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail)
                return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname)
                return SignUpResponseDto.duplicateNickname();

            String telNumber = dto.getTelNumber();
            boolean existedTelNumber = userRepository.existsByTelNumber(telNumber);
            if (existedTelNumber)
                return SignUpResponseDto.duplicateTelNumber();

            String password = dto.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);

            UserEntity userEntity = dto.toEntity(encodedPassword);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return SignUpResponseDto.success();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        try {
            String email = dto.getEmail();
            String requestedPassword = dto.getPassword();
            UserEntity user = userRepository.findByEmail(email);

            if (user == null)
                throw new NotFoundException(NOT_EXISTED_USER);

            boolean checkPasswordIsCorrect = passwordEncoder.matches(requestedPassword, user.getPassword());
            if (!checkPasswordIsCorrect)
                return SignInResponseDto.signInFail();

            final JwtResponseDto jwtResponseDto = jwtProvider.createJwtToken(email);
            redisService.setDataExpire(email, jwtResponseDto.getRefreshToken(),
                    REFRESH_TOKEN_EXPIRE_TIME);

            return SignInResponseDto.success(user, jwtResponseDto);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }
    }

    @Override
    public ResponseEntity<ApiResponse> signOut(JwtRequestDto dto) {
        try {
            jwtProvider.validateToken(dto.getAccessToken());

            Claims claims = jwtProvider.getInfoFromToken(dto.getAccessToken());
            String email = claims.getSubject();
            invalidateToken(email, dto.getAccessToken());

        } catch (Exception exception) {
            exception.printStackTrace();
            ApiResponse.databaseError();
        }
        return SignOutResponseDto.success();
    }

    @Override
    public ResponseEntity<JwtReissueResponseDto> reissue(final JwtRequestDto requestDto) {
        validateRefreshToken(requestDto);
        validateRefreshTokenOwnership(requestDto);

        String email = jwtProvider.getInfoFromToken(requestDto.getAccessToken()).getSubject();
        JwtResponseDto responseDto = jwtProvider.createJwtToken(email);

        return JwtReissueResponseDto.success(requestDto, responseDto);
    }

    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto) {

        try {

            String userEmail = dto.getEmail();
            boolean isExistEmail = userRepository.existsByEmail(userEmail);
            if (isExistEmail)
                return ApiResponse.duplicateEmail();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return IdCheckResponseDto.success();
    }

    @Override
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto) {

        try {

            String email = dto.getEmail();

            boolean isExistEmail = userRepository.existsByEmail(email);
            if (isExistEmail)
                return EmailCertificationResponseDto.duplicateEmail();

            String certificationNumber = CertificationNumber.getCertificationNumber();

            boolean isSuccessed = emailProvider.sendCertificationMail(email, certificationNumber);
            if (!isSuccessed)
                return EmailCertificationResponseDto.mailSendFail();

            CertificationEntity certificationEntity = new CertificationEntity(email, certificationNumber);
            certificationRepository.save(certificationEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return EmailCertificationResponseDto.success();

    }

    // ################### private methods ################### //

    /**
     * 토큰의 유효성을 검증하는 메서드
     */
    private void validateRefreshToken(JwtRequestDto dto) {
        jwtProvider.validateToken(dto.getRefreshToken());
    }

    /**
     * 요청자와 토큰의 소유자 정보가 일치하는지 확인하는 메서드
     *
     */
    private void validateRefreshTokenOwnership(JwtRequestDto dto) {
        String email = jwtProvider.getInfoFromToken(dto.getAccessToken()).getSubject();
        String validRefreshToken = redisService.getData(email);
        if (!dto.getRefreshToken().equals(validRefreshToken)) {
            throw new AuthException(EXPIRED_REFRESH_TOKEN);
        }
    }

    /**
     * 무효화된 토큰을 처리하는 메서드.
     * <p>
     * 1. 사용자 이메일과 연관된 데이터(토큰)를 Redis에서 삭제합니다.
     * <p>
     * 2. 무효화된 액세스 토큰을 블랙리스트에 추가하고, 지정된 시간 동안 유지합니다.
     * <p>
     * <p>
     * - 토큰 삭제: 사용자가 로그아웃하거나 토큰이 무효화될 때, 즉시 해당 토큰을 삭제하여 더 이상 유효하지 않게 합니다.
     * - 블랙리스트 추가: 이미 발행된 토큰이 유효기간 내에 재사용되는 것을 방지합니다. 이를 통해 네트워크 지연이나 기타 이유로 클라이언트에
     * 전달된 토큰이 다시 사용되지 않도록 보장합니다.
     * <p>
     * 
     * @param email       사용자의 이메일 주소.
     * @param accessToken 무효화할 액세스 토큰.
     */
    private void invalidateToken(String email, String accessToken) {
        redisService.deleteData(email);
        redisService.setDataExpire(
                BLACK_LIST_KEY_PREFIX + accessToken, VALUE_TRUE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    @Override
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto) {
        
        try {

            String email = dto.getEmail();
            String certificationNumber = dto.getCertificationNumber();
        
            CertificationEntity certificationEntity = certificationRepository.findByEmail(email);
            if (certificationEntity == null) return CheckCertificationResponseDto.CertificationFail();

            boolean isMatched = certificationEntity.getCertificationNumber().equals(certificationNumber);
            if (!isMatched) return CheckCertificationResponseDto.CertificationFail();
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ApiResponse.databaseError();
        }

        return CheckCertificationResponseDto.success();
    }

    
}