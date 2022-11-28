package com.allinfo.model.mapper;

import com.allinfo.model.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findUserByUserEmail(String email) throws Exception;

    Optional<UserDTO> findUserByUserId(String id) throws Exception;

    Optional<UserDTO> findUserByUid(Long uid);

    Optional<UserDTO> findUserById(String id) throws Exception;

    void signup(UserDTO userDTO) throws Exception;

    void setRefreshToken(UserDTO userDTO) throws Exception;

    void setSalt(@Param(value = "uid") Long uid, @Param(value = "salt") String salt) throws Exception;

    String getSalt(Long uid);

    void checkEmail(Long uid)  throws Exception;
}
