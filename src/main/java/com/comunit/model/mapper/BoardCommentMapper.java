package com.comunit.model.mapper;

import com.comunit.model.domain.board.comment.BoardCommentDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardCommentMapper {
    BoardCommentDTO getComment(Long comment_uid);

    Integer getCommentListPageInfo(Long board_uid);

    void createComment(BoardCommentDTO commentDTO);
}
