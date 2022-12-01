package com.comunit.model.mapper;

import com.comunit.model.domain.user.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<UserDTO> findUserByEmail(String email) throws Exception;

    Optional<UserDTO> findUserByUserId(String id) throws Exception;

    Optional<UserDTO> findUserByUid(Long uid);

    Optional<UserDTO> findUserById(String id) throws Exception;

    void signup(UserDTO userDTO) throws Exception;

    void setRefreshToken(UserDTO userDTO) throws Exception;

    void setSalt(@Param(value = "uid") Long uid, @Param(value = "salt") String salt) throws Exception;

    String getSalt(Long uid);

    void checkEmail(Long uid) throws Exception;

    void setPassword(@Param(value = "email") String email, @Param(value = "pw") String pw);

    Optional<UserDTO> findUserByNickname(String nickname);
}
