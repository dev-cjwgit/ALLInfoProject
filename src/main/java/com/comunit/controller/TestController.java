package com.comunit.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Test Controller")
public class TestController {

    @ApiOperation(value = "테스트", notes = "테스트 컨트롤러입니다.")
    @GetMapping("/test")
    public ResponseEntity<?> test(
            final Authentication authentication) {

        return new ResponseEntity<Object>("dd", HttpStatus.OK);
    }
}
