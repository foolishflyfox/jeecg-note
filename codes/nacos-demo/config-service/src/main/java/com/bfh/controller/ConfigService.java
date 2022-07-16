package com.bfh.controller;

import com.bfh.StockServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigService {
    @Autowired
    private StockServiceInterface stockServiceInterface;
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/test")
    public String test() {
        return "config-service [ " + stockServiceInterface.simpleStock() + " ]";
    }

    @GetMapping("/userinfo")
    public String userinfo() {
        String username = applicationContext.getEnvironment().getProperty("user.name");
        String age = applicationContext.getEnvironment().getProperty("user.age");
        String address = applicationContext.getEnvironment().getProperty("user.address");
        return String.format("name = %s, age = %s, address = %s\n", username, age, address);
    }

    @Value("${user.name}")  // 必须在类上表名 @RefreshScope
    private String name;

    @GetMapping("/name")
    public String name() {
        return name;
    }
}
