package com.bfh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author benfeihu
 */
@FeignClient("test-service")
@Component
public interface BeanA {
    @GetMapping("/test")
    String test();
}
