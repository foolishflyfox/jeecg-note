package com.bfh.annotation.lazzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @author benfeihu
 */
@SpringBootApplication
public class LazyMainTest {
    /*
       测试结果：
        NormalComponent is contructing...
        NormalBean 被创建
        容器被创建...
        LazyBean 被创建
        LazyComponent is constructing...
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(LazyMainTest.class, args);
        System.out.println("容器被创建...");
        ctx.getBean(LazyBean.class);
        ctx.getBean(LazyComponent.class);
    }
    @Bean
    NormalBean normalBean() {
        System.out.println("NormalBean 被创建");
        return new NormalBean();
    }
    @Bean
    @Lazy
    LazyBean lazyBean() {
        System.out.println("LazyBean 被创建");
        return new LazyBean();
    }

}
