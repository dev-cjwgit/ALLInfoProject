package com.allinfo.model.service;

import com.allinfo.model.domain.Pagination;
import com.allinfo.model.domain.board.BoardDTO;
import com.allinfo.model.domain.board.BoardKindDTO;
import com.allinfo.model.domain.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BoardService {
    List<BoardKindDTO> getBoard() throws Exception;

    Boolean createBoard(BoardDTO board, UserDTO auth) throws Exception;

    List<BoardDTO> getBoardList(Long boardKindUid, Pagination pagination);
}
