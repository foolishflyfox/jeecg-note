package com.bfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author benfeihu
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.bfh")
public class ConfigServiceMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ConfigServiceMain.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ConfigServiceMain.class, args);
        String username = ctx.getEnvironment().getProperty("user.name");
        String address = ctx.getEnvironment().getProperty("user.address");
//        String age = ctx.getEnvironment().getProperty("user.age");
//        String address = ctx.getEnvironment().getProperty("user.address");
//        System.out.println("username = " + username + ", age = " + age
//                + ", address = " + address);

    }
}
