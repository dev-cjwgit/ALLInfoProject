package com.comunit.model.service;

public interface AxiosService {
    Boolean signupRules(String keyword, String word) throws Exception;

    Integer getBoardCommentCount(Long board_uid);
}
