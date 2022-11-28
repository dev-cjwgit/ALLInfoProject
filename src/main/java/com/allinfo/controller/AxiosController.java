package com.allinfo.controller;

import com.allinfo.model.service.AxiosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(value = "Axios Controller")
@RequiredArgsConstructor
@RequestMapping("/axios")
@RestController
public class AxiosController {

    private final AxiosService axiosService;

    @ApiOperation(value = "회원가입 룰", notes = "회원가입 시 필요한 데이터가 있는지 없는지 확인하는 컨트롤러입니다.")
    @GetMapping("/signup/rules/{keyword}/{word}")
    private ResponseEntity<?> signupRules(@PathVariable("keyword") String keyword, @PathVariable("word") String word) throws Exception {

        return new ResponseEntity<Object>(new HashMap<String, Object>() {{
            put("result", axiosService.signupRules(keyword, word));
        }}, HttpStatus.OK);
    }
}
