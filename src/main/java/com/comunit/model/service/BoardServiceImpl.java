package com.comunit.model.service;


import com.comunit.exception.BaseException;
import com.comunit.exception.ErrorMessage;
import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
import com.comunit.model.domain.board.BoardKindDTO;
import com.comunit.model.domain.user.UserDTO;
import com.comunit.model.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;

    @Override
    public List<BoardKindDTO> getBoard() throws Exception {
        return boardMapper.getBoard();
    }

    @Override
    @Transactional
    public Boolean createBoard(BoardDTO board, UserDTO auth) throws Exception {
        board.setUser_uid(auth.getUid());
        boardMapper.createBoard(board);
        return true;
    }

    @Override
    public List<BoardDTO> getBoardList(Long boardKindUid, Pagination pagination) throws Exception {
        return boardMapper.getBoardList(boardKindUid, pagination);
    }

    @Override
    public BoardDTO getBoardDetail(Long boardUid) throws Exception {
        return boardMapper.getBoardDetail(boardUid);
    }

    @Override
    @Transactional
    public BoardDTO getBoardDetail(Long boardUid, UserDTO auth) throws Exception {
        if (boardMapper.isExistView(boardUid, auth.getUid()) == 1) {
            boardMapper.createView(boardUid, auth.getUid());
        }else{
            boardMapper.updateView(boardUid, auth.getUid());
        }
        return getBoardDetail(boardUid);
    }

    @Override
    public Integer getBoardListPageInfo(Long boardKindUid, Long range) throws Exception {
        return (int) Math.ceil(boardMapper.getBoardListPageInfo(boardKindUid) * 1.0 / range);
    }

    @Override
    @Transactional
    public Boolean updateBoard(BoardDTO board, UserDTO auth) throws Exception {
        BoardDTO sboard = getBoardDetail(board.getUid());

        if (sboard == null)
            throw new BaseException(ErrorMessage.NOT_EXIST_CONTENT);

        if (!sboard.getUser_uid().equals(auth.getUid())) {
            throw new BaseException(ErrorMessage.NOT_PERMISSION_EXCEPTION);
        }
        boardMapper.updateBoard(board);
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteBoard(Long boardUid, UserDTO auth) throws Exception {
        BoardDTO sboard = getBoardDetail(boardUid);

        if (sboard == null)
            throw new BaseException(ErrorMessage.NOT_EXIST_CONTENT);

        if (!sboard.getUser_uid().equals(auth.getUid())) {
            throw new BaseException(ErrorMessage.NOT_PERMISSION_EXCEPTION);
        }
        boardMapper.deleteBoard(boardUid);
        return true;
    }
}
