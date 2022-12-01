package com.comunit.model.service;

import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.comment.BoardCommentDTO;
import com.comunit.model.domain.user.UserDTO;

import java.util.List;

public interface BoardCommentService {
    Boolean createComment(BoardCommentDTO commentDTO, UserDTO auth) throws Exception;

    List<BoardCommentDTO> getCommentList(Long boardUid, Pagination pagination) throws Exception;

    BoardCommentDTO getComment(Long comment_uid);

    Boolean updateComment(BoardCommentDTO commentDTO, UserDTO auth) throws Exception;

    Boolean deleteComment(Long commentUid, UserDTO auth);

    Integer getCommentListPageInfo(Long boardKindUid, Long range) throws Exception;
}
