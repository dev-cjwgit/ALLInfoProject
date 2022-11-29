package com.comunit.model.domain.board.comment;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardCommentDTO {
    @ApiModelProperty(hidden = true)
    private Long uid;
    private Long board_uid;

    @ApiModelProperty(hidden = true)
    private Long user_uid;

    @ApiModelProperty(hidden = true)
    private String nickname;

    private String body;

    @ApiModelProperty(hidden = true)
    private Timestamp sdate;

    @ApiModelProperty(hidden = true)
    private Timestamp udate;
}
