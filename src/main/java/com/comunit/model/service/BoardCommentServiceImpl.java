package com.comunit.model.service;

import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.comment.BoardCommentDTO;
import com.comunit.model.domain.user.UserDTO;
import com.comunit.model.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {
    private final BoardCommentMapper boardCommentMapper;

    @Override
    @Transactional
    public Boolean createComment(BoardCommentDTO commentDTO, UserDTO auth) throws Exception {
        commentDTO.setUser_uid(auth.getUid());
        boardCommentMapper.createComment(commentDTO);
        return true;
    }

    @Override
    public List<BoardCommentDTO> getCommentList(Long boardUid, Pagination pagination) throws Exception {
        return null;
    }

    @Override
    public Integer getBoardListPageInfo(Long board_uid, Long range) throws Exception {
        return (int) Math.ceil(boardCommentMapper.getCommentListPageInfo(board_uid) * 1.0 / range);
    }

    @Override
    public BoardCommentDTO getComment(Long comment_uid) {
        return boardCommentMapper.getComment(comment_uid);
    }

    @Override
    @Transactional
    public Boolean updateComment(BoardCommentDTO commentDTO, UserDTO auth) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public Boolean deleteComment(Long commentUid, UserDTO auth) {
        return null;
    }
}
