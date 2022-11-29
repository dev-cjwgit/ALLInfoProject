package com.comunit.model.domain.board.comment;

import com.comunit.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class BoardCommentDTO {
    private Long uid;
    private Long board_uid;

    @ApiModelProperty(hidden = true)
    private Long user_uid;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @NotNull(groups = {ValidationGroups.comment_create.class, ValidationGroups.comment_update.class}, message = "내용은 공백일 수 없습니다.")
    private String body;

    @ApiModelProperty(hidden = true)
    private Timestamp sdate;

    @ApiModelProperty(hidden = true)
    private Timestamp udate;
}
