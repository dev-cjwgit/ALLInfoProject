package com.comunit.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AxiosMapper {
    int signupRules(@Param("keyword") String keyword, @Param("word") String word);
}
