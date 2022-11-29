package com.comunit.model.service;

import com.comunit.exception.BaseException;
import com.comunit.exception.ErrorMessage;
import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
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
        return boardCommentMapper.getCommentList(boardUid, pagination);
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
    public Boolean updateComment(BoardCommentDTO comment, UserDTO auth) throws Exception {
        BoardCommentDTO sComment = getComment(comment.getUid());

        if (sComment == null)
            throw new BaseException(ErrorMessage.NOT_EXIST_CONTENT);

        if (!sComment.getUser_uid().equals(auth.getUid())) {
            throw new BaseException(ErrorMessage.NOT_PERMISSION_EXCEPTION);
        }
        boardCommentMapper.updateComment(comment);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteComment(Long commentUid, UserDTO auth) {
        BoardCommentDTO sComment = getComment(commentUid);

        if (sComment == null)
            throw new BaseException(ErrorMessage.NOT_EXIST_CONTENT);

        if (!sComment.getUser_uid().equals(auth.getUid())) {
            throw new BaseException(ErrorMessage.NOT_PERMISSION_EXCEPTION);
        }

        boardCommentMapper.deleteComment(commentUid);
        return true;
    }
}
