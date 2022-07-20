package com.bfh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author benfeihu
 */
@SpringBootApplication
public class NacosObjectMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NacosObjectMain.class, args);
    }

    @Autowired
    private ApplicationContext ctx;

    @Value("${user.name}")
    public void setUserName(String un) {
        System.out.println("un = " + un);
    }

    @Value("${user.sex}")
    public void setUserSex(String sex) {
        System.out.println("sex = " + sex);
    }

    @Override
    public void run(String... args) throws Exception {
        String userName = ctx.getEnvironment().getProperty("user.name");
        String address = ctx.getEnvironment().getProperty("user.address");
        System.out.println("userName = " + userName);
    }
}
