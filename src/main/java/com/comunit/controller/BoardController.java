package com.comunit.controller;

import com.comunit.annotation.ValidationGroups;
import com.comunit.model.domain.Pagination;
import com.comunit.model.domain.board.BoardDTO;
import com.comunit.model.domain.user.UserDTO;
import com.comunit.model.service.BoardService;
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
@Api(value = "Board Controller")
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    // CRUD

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "게시판에 게시글을 생성합니다.")
    public ResponseEntity<?> createBoard(@RequestBody @Validated(ValidationGroups.board.class) BoardDTO board,
                                         final Authentication authentication) throws Exception {
        UserDTO user = (UserDTO) authentication.getPrincipal();
        if (boardService.createBoard(board, user)) {
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

    @GetMapping
    @ApiOperation(value = "게시판 목록 불러오기", notes = "게시판 목록들을 불러옵니다.")
    public ResponseEntity<?> getBoard(
            final Authentication authentication) throws Exception {

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardService.getBoard());
        }}, HttpStatus.OK);
    }

    @GetMapping("/page/{board_kind_uid}/{page_ragne}")
    @ApiOperation(value = "게시판 페이지 정보", notes = "게시판의 페이지 정보를 불러옵니다.")
    public ResponseEntity<?> getPageInfo(@PathVariable("board_kind_uid") Long boardKindUid,
                                         @PathVariable("page_ragne") Long range,
                                         final Authentication authentication) throws Exception {

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardService.getBoardListPageInfo(boardKindUid, range));
        }}, HttpStatus.OK);
    }

    @GetMapping("/{board_kind_uid}")
    @ApiOperation(value = "모든 게시글 불러오기", notes = "특정 게시판의 모든 게시글을 불러옵니다.")
    public ResponseEntity<?> getAll(@PathVariable("board_kind_uid") Long boardKindUid,
                                    @ModelAttribute Pagination pagination,
                                    final Authentication authentication) throws Exception {

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardService.getBoardList(boardKindUid, pagination));
        }}, HttpStatus.OK);
    }

    @GetMapping("/detail/{board_uid}")
    @ApiOperation(value = "게시글 불러오기", notes = "특정 게시글을 불러옵니다.")
    public ResponseEntity<?> getOne(@PathVariable("board_uid") Long boardUid,
                                    final Authentication authentication) throws Exception {

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("data", boardService.getBoardDetail(boardUid));
        }}, HttpStatus.OK);
    }

    @PatchMapping
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
    public ResponseEntity<?> updateBoard(@RequestBody @Validated(ValidationGroups.board.class) BoardDTO board,
                                         final Authentication authentication) throws Exception {

        return null;
    }

    @DeleteMapping("/{board_kind_uid}/{board_uid}")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
    public ResponseEntity<?> deleteBoard(@PathVariable("board_kind_uid") Long boardKindUid,
                                         @PathVariable("board_uid") Long boardUid,
                                         final Authentication authentication) throws Exception {

        return null;
    }

}
