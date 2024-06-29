package com.bitcamp.board_back.feature.user.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.bitcamp.board_back.feature.user.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    @Email
    private String email;

    @Column(nullable = false, length = 300)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 20)
    @Pattern(regexp = "^[0-9]{11,13}$")
    private String telNumber;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String address;
    private String addressDetail;
    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @Column(nullable = false)
    private boolean agreedPersonal;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updateAt;

    @Builder
    public UserEntity(
            String email,
            String password,
            String nickname,
            String telNumber,
            String address,
            String addressDetail,
            String profileImage,
            UserRole userRole,
            boolean agreedPersonal

    ) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.telNumber = telNumber;
        this.address = address;
        this.addressDetail = addressDetail;
        this.profileImage = profileImage;
        this.userRole = userRole;
        this.agreedPersonal = agreedPersonal;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
