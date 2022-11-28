package com.allinfo.model.service;

import com.allinfo.exception.BaseException;
import com.allinfo.exception.ErrorMessage;
import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;
import com.allinfo.model.mapper.UserMapper;
import com.allinfo.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
            throw new BaseException(ErrorMessage.EXIST_ID);
        }

        userDTO.setPw(passwordEncoder.encode(userDTO.getPassword()));
        userMapper.signup(userDTO);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String salt = userDTO.getUid().toString() + calendar.getTime();

        salt = (BCrypt.hashpw(salt, BCrypt.gensalt()));
        userMapper.setSalt(userDTO.getUid(), salt);

        return userMapper.findUserById(userDTO.getUsername()).get();
    }

    @Override
    public Map<String, Object> login(LoginDTO loginDto) {
        UserDTO userDto = userMapper.findUserById(loginDto.getId())
                .orElseThrow(() -> new RuntimeException("잘못된 아이디입니다"));

        if (!passwordEncoder.matches(loginDto.getPw(), userDto.getPassword())) {
            throw new BaseException(ErrorMessage.NOT_PASSWORD);
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
                    throw new BaseException(ErrorMessage.ACCESS_TOKEN_EXPIRE);
            } else {
                throw new BaseException(ErrorMessage.REFRESH_TOKEN_NOT_MATCH);
            }
        } else {
            throw new BaseException(ErrorMessage.NOT_USER_INFO);
        }
    }
}
