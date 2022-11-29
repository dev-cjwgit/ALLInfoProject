package com.allinfo.model.mapper;

import com.allinfo.model.domain.Pagination;
import com.allinfo.model.domain.board.BoardDTO;
import com.allinfo.model.domain.board.BoardKindDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardKindDTO> getBoard();

    void createBoard(BoardDTO board);

    List<BoardDTO> getBoardList(@Param("board_kind_uid") Long boardKindUid, @Param("page") Pagination pagination);
}
