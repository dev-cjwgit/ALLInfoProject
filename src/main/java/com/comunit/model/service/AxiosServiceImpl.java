package com.comunit.model.service;

import com.comunit.exception.BaseException;
import com.comunit.exception.ErrorMessage;
import com.comunit.model.mapper.AxiosMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AxiosServiceImpl implements AxiosService {
    private final AxiosMapper axiosMapper;

    @Override
    public Boolean signupRules(String keyword, String word) throws Exception {
        switch (keyword) {
            case "id":
            case "email":
            case "nickname":
            {
                return axiosMapper.signupRules(keyword, word) > 0;
            }
        }
        throw new BaseException(ErrorMessage.UNDEFINED_EXCEPTION);
    }
}
