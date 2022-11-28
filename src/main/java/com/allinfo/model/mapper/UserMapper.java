package com.allinfo.model.mapper;

import com.allinfo.model.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findUserByUserEmail(String email);

    Optional<UserDTO> findUserByUserId(String id);

    Optional<UserDTO> findUserByUid(Long uid);

    Optional<UserDTO> findUserById(String id);

    void signup(UserDTO userDTO);

    void setRefreshToken(UserDTO userDTO);

    void setSalt(@Param(value = "uid") Long uid, @Param(value = "salt") String salt);

    String getSalt(Long uid);
}
