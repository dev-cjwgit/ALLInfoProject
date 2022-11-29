package com.allinfo.model.service;


import com.allinfo.model.domain.user.UserDTO;
import com.allinfo.model.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userMapper.findUserByUid(Long.valueOf(userId))
                .map(this::addAuthorities)
                .orElseThrow(() -> new RuntimeException(userId + "> 찾을 수 없습니다."));
    }

    private UserDTO addAuthorities(UserDTO userDto) {
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }
}
