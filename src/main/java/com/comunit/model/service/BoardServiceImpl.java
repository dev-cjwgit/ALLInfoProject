package com.comunit.model.service;


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
    public List<BoardDTO> getBoardList(Long boardKindUid, Pagination pagination) {
        return boardMapper.getBoardList(boardKindUid, pagination);
    }

    @Override
    public BoardDTO getBoardDetail(Long boardUid) throws Exception {
        return boardMapper.getBoardDetail(boardUid);
    }

    @Override
    public Integer getBoardListPageInfo(Long boardKindUid, Long range) {
        return (int) Math.ceil(boardMapper.getBoardListPageInfo(boardKindUid) * 1.0 / range);
    }
}
