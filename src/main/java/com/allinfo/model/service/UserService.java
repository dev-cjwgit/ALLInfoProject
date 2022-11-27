package com.allinfo.model.service;

import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;

public interface UserService {
    UserDTO join(UserDTO userDto);

    String login(LoginDTO loginDto);

    UserDTO findByUserId(Long userId);
}
