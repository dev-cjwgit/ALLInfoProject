package com.allinfo.controller;

import com.allinfo.annotation.ValidationGroups;
import com.allinfo.model.domain.UserDTO;
import com.allinfo.model.domain.param.LoginDTO;
import com.allinfo.model.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api("User Controller")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "req_data : [id, pw, email, name, nickname]")
    @PostMapping("/join")
    public ResponseEntity<?> signup(@RequestBody @Validated(ValidationGroups.signup.class) UserDTO userDTO) {

        UserDTO savedUser = userService.signup(userDTO);

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", true);
            put("msg", "회원가입을 성공하였습니다.");
        }}, HttpStatus.OK);
    }

    @ApiOperation(value = "로그인", notes = "req_data : [id, pw]")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated(ValidationGroups.login.class) LoginDTO user) {
        try {
            Map<String, Object> token = userService.login(user);
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "로그인을 성공하였습니다.");
                put("access-token", token.get("access-token"));
                put("refresh-token", token.get("refresh-token"));
                put("uid", token.get("uid"));
            }}, HttpStatus.OK);

        } catch (RuntimeException exception) {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", false);
                put("msg", "로그인을 실패하였습니다.");
            }}, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.")
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Long uid, HttpServletRequest request)
            throws Exception {
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        String result = userService.refreshToken(uid, token);
        if (result != null && !result.equals("")) {
            // 발급 성공
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "토큰이 발급되었습니다.");
                put("access-token", result);
            }}, status);
        } else {
            // 발급 실패
            throw new RuntimeException("리프레시 토큰 발급에 실패하였습니다.");
        }
    }
}
