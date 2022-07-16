package com.bfh;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author benfeihu
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockServiceInterface {
    @RequestMapping("/simpleStock")
    String simpleStock();
}
