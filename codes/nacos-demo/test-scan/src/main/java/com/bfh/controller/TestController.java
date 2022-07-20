package com.bfh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    String hello() {
        return "hello.....";
    }
}
