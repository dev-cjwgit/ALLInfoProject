package com.allinfo.controller;

import com.allinfo.model.domain.param.LoginDTO;
import com.allinfo.model.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Api("User Controller")
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "로그인", notes = "로그인을 진행합니다.")
    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginDTO user) {
        try {
            String token = userService.login(user);

            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", true);
                put("msg", "로그인을 성공하였습니다.");
                put("Authorization", "Bearer " + token);
            }}, HttpStatus.OK);

        } catch (RuntimeException exception) {
            return new ResponseEntity<Object>(new HashMap<String, Object>() {{
                put("result", false);
                put("msg", "로그인을 실패하였습니다.");
            }}, HttpStatus.OK);
        }
    }
}
