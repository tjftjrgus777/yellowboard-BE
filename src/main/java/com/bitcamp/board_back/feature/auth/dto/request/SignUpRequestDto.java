package com.bitcamp.board_back.feature.auth.dto.request;

import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.enums.UserRole;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {

    @NotBlank @Email(message = "유효한 이메일 주소를 입력하세요.")
    private String email;

    @NotBlank @Size(min=8, max=20)
    private String password;

    @NotBlank
    private String nickname;

    @NotBlank @Pattern(regexp ="^[0-9]{11,13}$")
    private String telNumber;

    @NotBlank
    private String address;

    private String addressDetail;

    @NotNull @AssertTrue
    private Boolean agreedPersonal;

    public UserEntity toEntity(final String encodedPassword) {
        return UserEntity.builder()
                .email(this.email)
                .password(encodedPassword)
                .nickname(this.nickname)
                .telNumber(this.telNumber)
                .address(this.address)
                .addressDetail(this.addressDetail)
                .agreedPersonal(this.agreedPersonal)
                .userRole(UserRole.ROLE_USER)
                .build();
    }
}
