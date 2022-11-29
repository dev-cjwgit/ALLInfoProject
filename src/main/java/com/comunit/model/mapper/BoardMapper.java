package com.comunit.model.mapper;

import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
import com.comunit.model.domain.board.BoardKindDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardKindDTO> getBoard();

    void createBoard(BoardDTO board);

    List<BoardDTO> getBoardList(@Param("board_kind_uid") Long boardKindUid, @Param("page") Pagination pagination);
}
