package com.bfh.annotation.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * @author benfeihu
 */
@SpringBootApplication
public class PrimaryDemoMain implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PrimaryDemoMain.class, args);
    }

    @Bean
    @Primary
    public BeanA beanA1() {
        return new BeanA("beanA1");
    }
    @Bean
    public BeanA beanA2() {
        return new BeanA("beanA2");
    }

    @Autowired
    BeanA beanA;

    @Autowired
    @Qualifier("beanA2")
    BeanA bean;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(beanA);
        System.out.println(bean);
    }
}
