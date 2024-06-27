package com.bitcamp.board_back.feature.user.service.implement;

import com.bitcamp.board_back.exception.NotFoundException;
import com.bitcamp.board_back.feature.user.dto.AccountUserDetails;
import com.bitcamp.board_back.feature.user.entity.UserEntity;
import com.bitcamp.board_back.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bitcamp.board_back.common.enums.ApiStatus.NOT_EXISTED_USER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountUserDetailServiceImplement implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new NotFoundException(NOT_EXISTED_USER);
        }
        return new AccountUserDetails(user);
    }

}
