package com.allinfo.model.service;

import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;

import java.util.Map;

public interface UserService {
    UserDTO join(UserDTO userDto);

    Map<String, Object>  login(LoginDTO loginDto);

    UserDTO findByUserId(Long userId);

    public String refreshToken(Long uid, String token);
}
