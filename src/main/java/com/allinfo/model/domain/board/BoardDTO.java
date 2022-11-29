package com.allinfo.model.domain.board;

import com.allinfo.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.bytebuddy.implementation.bind.annotation.Empty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
public class BoardDTO {
    @ApiModelProperty(hidden = true)
    private Long uid;

    @NotNull(groups = {ValidationGroups.board.class}, message = "게시판 번호는 공백일 수 없습니다.")
    private Long board_kind_uid;

    @ApiModelProperty(hidden = true)
    private Long user_uid;

    @ApiModelProperty(hidden = true)
    private String nickname;

    @NotNull(groups = {ValidationGroups.board.class}, message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotNull(groups = {ValidationGroups.board.class}, message = "내용은 공백일 수 없습니다.")
    private String body;

    @ApiModelProperty(hidden = true)
    private Timestamp sdate;

    @ApiModelProperty(hidden = true)
    private Timestamp udate;
}
