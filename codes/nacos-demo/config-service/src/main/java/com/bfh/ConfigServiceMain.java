package com.bfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author benfeihu
 */
@SpringBootApplication
@EnableFeignClients
public class ConfigServiceMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConfigServiceMain.class, args);
        String username = ctx.getEnvironment().getProperty("user.name");
        String age = ctx.getEnvironment().getProperty("user.age");
        String address = ctx.getEnvironment().getProperty("user.address");
        System.out.println("username = " + username + ", age = " + age
                + ", address = " + address);
    }
}
