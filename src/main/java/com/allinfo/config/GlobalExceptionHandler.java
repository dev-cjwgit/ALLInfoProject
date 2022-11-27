package com.allinfo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handler(Throwable t) {

        logger.error(t.getMessage());

        return new ResponseEntity<Object>(new HashMap<String, String>() {{
            put("msg", "알 수 없는 예외입니다.");
        }}, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}

