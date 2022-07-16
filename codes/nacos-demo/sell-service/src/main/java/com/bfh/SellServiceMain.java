package com.bfh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author benfeihu
 */
@SpringBootApplication
@EnableFeignClients
public class SellServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(SellServiceMain.class, args);
    }

    @Bean
    @LoadBalanced  // 必须加上 LoadBalance 指定负载均衡器
    RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
