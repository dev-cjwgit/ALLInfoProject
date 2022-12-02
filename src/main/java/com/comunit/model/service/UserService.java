package com.comunit.model.service;

import com.comunit.model.domain.user.MypageDTO;
import com.comunit.model.domain.user.UserDTO;
import com.comunit.model.domain.param.LoginDTO;

import java.util.Map;

public interface UserService {
    UserDTO signup(UserDTO userDto) throws Exception;

    Map<String, Object> login(LoginDTO loginDto) throws Exception;


    String refreshToken(Long uid, String token) throws Exception;

    void sendSignupEmail(UserDTO user) throws Exception;

    void resendCheckMail(LoginDTO loginDTO) throws Exception;

    void checkEmail(String token) throws Exception;

    void findMyPW(UserDTO user) throws Exception;

    UserDTO getMypage(Long user_uid);

    Boolean setMypage(MypageDTO user, UserDTO auth);
}
