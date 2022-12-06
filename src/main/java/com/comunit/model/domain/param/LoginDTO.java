package com.comunit.model.domain.param;

import com.comunit.annotation.ValidationGroups;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginDTO {
    @NotNull
    @Size(min = 6, max = 20, groups = {ValidationGroups.login.class}, message = "아이디는 6글자이상 20글자 이하입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", groups = {ValidationGroups.login.class}, message = "아이디는 특수문자와 초성, 한글은 사용불가능합니다")
    private String id;

    @NotNull(groups = {ValidationGroups.login.class}, message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, max = 30, groups = {ValidationGroups.login.class}, message = "비밀번호는은 8글자 이상 30글자 이하입니다.")
    private String pw;
}
