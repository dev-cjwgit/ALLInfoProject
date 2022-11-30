package com.comunit.model.service;

import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
import com.comunit.model.domain.board.BoardKindDTO;
import com.comunit.model.domain.user.UserDTO;

import java.util.List;

public interface BoardService {
    List<BoardKindDTO> getBoard() throws Exception;

    Boolean createBoard(BoardDTO board, UserDTO auth) throws Exception;

    List<BoardDTO> getBoardList(Long boardKindUid, Pagination pagination) throws Exception;

    BoardDTO getBoardDetail(Long boardUid) throws Exception;
    BoardDTO getBoardDetail(Long boardUid, UserDTO auth) throws Exception;

    Integer getBoardListPageInfo(Long boardKindUid, Long pagination) throws Exception;

    Boolean updateBoard(BoardDTO board, UserDTO auth) throws Exception;

    Boolean deleteBoard(Long boardUid, UserDTO auth) throws Exception;
}
