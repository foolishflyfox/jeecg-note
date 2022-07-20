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
public class SpringConfigDemoMain implements CommandLineRunner {

    @Value("${my.value:abc}") // 使用 application.yaml 中的 my.value 值，未配置则使用 "abc"
    private String myValue;

    @Value("${my.value2:#{null}}") // 未配置则赋值为 null
    private String myValue2;

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigDemoMain.class, args);
    }

    @Autowired
    ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("myValue = " + myValue);
        System.out.println("myValue2 = " + myValue2);
        System.out.println(ctx.getEnvironment().getProperty("my.value"));
    }
}
