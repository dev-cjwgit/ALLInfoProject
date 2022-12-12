package com.comunit.controller;

import com.comunit.annotation.ValidationGroups;
import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
import com.comunit.model.domain.board.comment.BoardCommentDTO;
import com.comunit.model.domain.user.UserDTO;
import com.comunit.model.service.BoardCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Api(value = "BoardComentController")
@RequestMapping("/board/comment")
@RequiredArgsConstructor
public class BoardCommentController {
    private final BoardCommentService boardCommentService;
    // CRUD

    @PostMapping
    @ApiOperation(value = "댓글 생성", notes = "게시판에 게시글을 생성합니다.")
    public ResponseEntity<?> createComment(@RequestBody @Validated(ValidationGroups.comment_create.class) BoardCommentDTO commentDTO,
                                           final Authentication authentication) throws Exception {
        UserDTO auth = (UserDTO) authentication.getPrincipal();

        if (boardCommentService.createComment(commentDTO, auth)) {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "게시글 등록에 성공하였습니다.");
            }}, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", false);
                put("msg", "게시글 등록에 실패하였습니다.");
            }}, HttpStatus.OK);
        }
    }

    @GetMapping("/{board_uid}")
    @ApiOperation(value = "댓글 목록 불러오기", notes = "특정 게시글의 댓글 목록을 불러옵니다.")
    public ResponseEntity<?> selectAll(@PathVariable("board_uid") Long boardUid,
                                       @ModelAttribute Pagination pagination,
                                       final Authentication authentication) throws Exception {
        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardCommentService.getCommentList(boardUid, pagination));
        }}, HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation(value = "댓글 수정하기", notes = "댓글을 수정합니다.")
    public ResponseEntity<?> updateComment(@RequestBody @Validated(ValidationGroups.comment_update.class) BoardCommentDTO commentDTO,
                                           final Authentication authentication) throws Exception {
        UserDTO auth = (UserDTO) authentication.getPrincipal();

        if (boardCommentService.updateComment(commentDTO, auth)) {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "수정에 성공하였습니다.");
            }}, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", false);
                put("msg", "수정에 실패하였습니다.");
            }}, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{comment_uid}")
    @ApiOperation(value = "댓글 삭제하기", notes = "댓글을 삭제합니다.")
    public ResponseEntity<?> deleteComment(@PathVariable("comment_uid") Long commentUid,
                                           final Authentication authentication) throws Exception {
        UserDTO auth = (UserDTO) authentication.getPrincipal();

        if (boardCommentService.deleteComment(commentUid, auth)) {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "삭제에 성공하였습니다.");
            }}, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", false);
                put("msg", "삭제에 실패하였습니다.");
            }}, HttpStatus.OK);
        }
    }

    @GetMapping("/page/{board_uid}/{page_ragne}")
    @ApiOperation(value = "게시판 페이지 정보", notes = "게시판의 페이지 정보를 불러옵니다.")
    public ResponseEntity<?> getPageInfo(@PathVariable("board_uid") Long boardUid,
                                         @PathVariable("page_ragne") Long range,
                                         final Authentication authentication) throws Exception {
        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardCommentService.getCommentListPageInfo(boardUid, range));
        }}, HttpStatus.OK);
    }
}
