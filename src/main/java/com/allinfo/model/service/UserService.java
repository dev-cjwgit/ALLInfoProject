package com.allinfo.model.service;

import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;

import java.util.Map;

public interface UserService {
    UserDTO signup(UserDTO userDto) throws Exception;

    Map<String, Object> login(LoginDTO loginDto) throws Exception;

    UserDTO findByUserId(Long userId) throws Exception;

    String refreshToken(Long uid, String token) throws Exception;

    void sendSignupEmail(UserDTO user) throws Exception;

    void resendCheckMail(LoginDTO loginDTO) throws Exception;

    void checkEmail(String token) throws Exception;
}
