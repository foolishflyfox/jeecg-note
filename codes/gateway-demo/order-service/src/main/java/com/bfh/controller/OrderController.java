package com.bfh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/test")
    public String test() {
        return "order service test";
    }
}
