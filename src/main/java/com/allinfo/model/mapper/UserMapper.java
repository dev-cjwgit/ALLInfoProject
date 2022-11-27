package com.allinfo.model.mapper;

import com.allinfo.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findUserByUserEmail(String email);

    Optional<User> findUserByUserId(String id);

    Optional<User> findUserByUid(Long uid);

    Optional<User> findUserById(String id);

    void join(User user);
}
