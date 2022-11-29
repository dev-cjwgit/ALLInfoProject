package com.comunit.model.mapper;

import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.comment.BoardCommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface BoardCommentMapper {
    BoardCommentDTO getComment(Long comment_uid);

    Integer getCommentListPageInfo(Long board_uid);

    void createComment(BoardCommentDTO commentDTO);

    List<BoardCommentDTO> getCommentList(@Param("board_uid") Long boardUid, @Param("page") Pagination pagination);

    void updateComment(BoardCommentDTO comment);

    void deleteComment(Long comment_uid);
}
