package com.allinfo.model.service;

import com.allinfo.model.domain.User;
import com.allinfo.model.domain.param.LoginDTO;
import com.allinfo.model.mapper.UserMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

public interface UserService {
    User join(User userDto);

    String login(LoginDTO loginDto);

    User findByUserId(Long userId);
}
