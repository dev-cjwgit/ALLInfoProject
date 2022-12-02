package com.comunit.model.domain.user;

import com.comunit.annotation.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class MypageDTO {
    @NotNull
    @ApiModelProperty(hidden = true)
    protected Long uid;

    @NotNull(groups = {ValidationGroups.mypage.class}, message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, max = 30, groups = {ValidationGroups.mypage.class}, message = "비밀번호는은 8글자 이상 30글자 이하입니다.")
    protected String pw;

    @NotNull(groups = {ValidationGroups.mypage.class}, message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, max = 30, groups = {ValidationGroups.mypage.class}, message = "비밀번호는은 8글자 이상 30글자 이하입니다.")
    protected String npw;

    @NotNull(groups = {ValidationGroups.signup.class}, message = "별명은 공백일 수 없습니다.")
    @Size(min = 2, max = 30, groups = {ValidationGroups.mypage.class}, message = "별명은 2글자이상 20글자 이하입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$", groups = {ValidationGroups.mypage.class}, message = "별명은 특수문자와 초성은 사용불가능합니다")
    protected String nickname;
}
