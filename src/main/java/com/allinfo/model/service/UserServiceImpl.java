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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO signup(UserDTO userDTO) {
        if (userMapper.findUserById(userDTO.getId()).isPresent()) {
            throw new RuntimeException("이미 가입된 유저입니다");
        }

        userDTO.setPw(passwordEncoder.encode(userDTO.getPassword()));
        userMapper.join(userDTO);

        return userMapper.findUserById(userDTO.getUsername()).get();
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDto) {
        UserDTO userDto = userMapper.findUserById(loginDto.getId())
                .orElseThrow(() -> new RuntimeException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getPw(), userDto.getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다");
        }
        String accessToken = jwtTokenProvider.createToken(userDto.getUid(), Collections.singletonList(userDto.getRole()));
        String refreshToken = jwtTokenProvider.createRefresh(userDto.getUid(), Collections.singletonList(userDto.getRole()));
        userDto.setRefresh_token(refreshToken);
        userMapper.setRefreshToken(userDto);
        return new HashMap<String, Object>() {{
            put("access-token", accessToken);
            put("refresh-token", refreshToken);
            put("uid", userDto.getUid());
        }};
    }

    @Override
    public UserDTO findByUserId(Long userId) {
        return null;
    }

    @Override
    public String refreshToken(Long uid, String token) {
        Optional<UserDTO> object = userMapper.findUserByUid(uid);
        if (object.isPresent()) {
            UserDTO userDTO = object.get();
            if (token.equals(userDTO.getRefresh_token())) {
                if (jwtTokenProvider.validateToken(token))
                    return jwtTokenProvider.createToken(userDTO.getUid(), Collections.singletonList(userDTO.getRole()));
                else
                    throw new RuntimeException("리프레시 토큰 정보가 만료되었습니다.");
            } else {
                throw new RuntimeException("리프레시 토큰 정보가 일치하지 않습니다.");
            }
        } else {
            throw new RuntimeException("유저 정보가 존재하지 않습니다.");
        }
    }
}
