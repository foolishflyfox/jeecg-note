package com.bfh.controller;

import com.bfh.outer.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/sell")
public class SellServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/simpleSell")
    public String simpleSell() {
        String stockResult = restTemplate.getForObject(
                "http://stock-service/stock/simpleStock",  // 指定服务地址
                String.class);  // 指定返回类型
        return "商品售卖--无参数, 调用 stock-service 结果: " + stockResult;
    }

    @Autowired
    private StockService stockService;

    @GetMapping("/feignSell")
    public String feignSell() {
        String stockResult = stockService.simpleStock();
        return "feignSell call stock-service result: " + stockResult;
    }
}
