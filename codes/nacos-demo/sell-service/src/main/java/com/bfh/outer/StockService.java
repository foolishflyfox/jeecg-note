package com.bfh.outer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author benfeihu
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockService {

    @RequestMapping("/simpleStock")
    String simpleStock();
}
