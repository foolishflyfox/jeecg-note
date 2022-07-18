package com.bfh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/stock")
public class StockController {
    @RequestMapping("/test")
    String test() {
        return "stock service test";
    }
}
