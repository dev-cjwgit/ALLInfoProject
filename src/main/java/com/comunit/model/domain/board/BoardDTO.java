package com.comunit.model.domain.board;

import com.comunit.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class BoardDTO {
    @NotNull(groups = {ValidationGroups.board_update.class}, message = "게시글 번호는 공백일 수 없습니다.")
    private Long uid;

    @NotNull(groups = {ValidationGroups.board_create.class}, message = "게시판 번호는 공백일 수 없습니다.")
    private Long board_kind_uid;

    @ApiModelProperty(hidden = true)
    private Long user_uid;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @ApiModelProperty(hidden = true)
    private Integer comment_count;

    @NotNull(groups = {ValidationGroups.board_create.class, ValidationGroups.board_update.class}, message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotNull(groups = {ValidationGroups.board_create.class, ValidationGroups.board_update.class}, message = "내용은 공백일 수 없습니다.")
    private String body;

    @ApiModelProperty(hidden = true)
    private Timestamp sdate;

    @ApiModelProperty(hidden = true)
    private Timestamp udate;
}
