package com.bitcamp.board_back.feature.user.dto;

import com.bitcamp.board_back.common.enums.ApiStatus;
import com.bitcamp.board_back.exception.NotFoundException;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountUserDetails implements UserDetails {

    @Getter
    private final UserEntity user;
    private String email;

    public AccountUserDetails(UserEntity user) {
        if(user == null) {
            throw new NotFoundException(ApiStatus.NOT_FOUND_ACCOUNT);
        }
        this.user = user;
        this.email = user.getEmail();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
