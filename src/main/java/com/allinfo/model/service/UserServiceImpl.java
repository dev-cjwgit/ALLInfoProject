package com.allinfo.model.service;

import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;
import com.allinfo.model.mapper.UserMapper;
import com.allinfo.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO join(UserDTO userDTO) {
        if (userMapper.findUserById(userDTO.getId()).isPresent()) {
            throw new RuntimeException("이미 가입된 유저입니다");
        }

        userDTO.setPw(passwordEncoder.encode(userDTO.getPassword()));
        userMapper.join(userDTO);

        return userMapper.findUserById(userDTO.getUsername()).get();
    }

    @Override
    public String login(LoginDTO loginDto) {
        UserDTO userDto = userMapper.findUserById(loginDto.getId())
                .orElseThrow(() -> new RuntimeException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getPw(), userDto.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다");
        }

        return jwtTokenProvider.createToken(userDto.getUid(), Collections.singletonList(userDto.getRole()));
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        return null;
    }
}
