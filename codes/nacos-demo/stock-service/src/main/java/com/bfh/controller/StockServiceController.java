package com.bfh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/stock")
public class StockServiceController {
    @RequestMapping("/simpleStock")
    public String simpleStock() {
        return "无参数调用: simpleStock";
    }
}
