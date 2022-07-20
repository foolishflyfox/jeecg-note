package com.bfh;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
@RequestMapping("b")
public class BeanBImpl {
    @GetMapping("/test")
    public String test() {
        return "b - test";
    }
}
