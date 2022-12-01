package com.comunit.model.domain.board.comment;

import com.comunit.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotBlank(groups = {ValidationGroups.comment_create.class, ValidationGroups.comment_update.class}, message = "내용은 공백일 수 없습니다.")
    @Size(min = 2, max = 200, groups = {ValidationGroups.comment_create.class, ValidationGroups.comment_update.class}, message = "댓글은 2글자 이상 200자 이하여야합니다.")
    private String body;

    public String getBody() {
        return this.body.trim();
    }

    @ApiModelProperty(hidden = true)
    private Timestamp sdate;

    @ApiModelProperty(hidden = true)
    private Timestamp udate;
}
