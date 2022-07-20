package com.bfh.controller;

import com.bfh.exception.AException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author benfeihu
 */
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(AException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String dealAException() {
        return "捕获到 A 异常";
    }

}
