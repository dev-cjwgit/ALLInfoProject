package com.allinfo.model.service;

import com.allinfo.exception.BaseException;
import com.allinfo.exception.ErrorMessage;
import com.allinfo.model.mapper.AxiosMapper;
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
            case "email": {
                return axiosMapper.signupRules(keyword, word) > 0;
            }
        }
        throw new BaseException(ErrorMessage.UNDEFINED_EXCEPTION);
    }
}
