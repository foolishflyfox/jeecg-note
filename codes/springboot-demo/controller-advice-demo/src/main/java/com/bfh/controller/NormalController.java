package com.bfh.controller;

import com.bfh.exception.AException;
import com.bfh.exception.BException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author benfeihu
 */
@RestController
public class NormalController {
    @GetMapping("/test")
    public String test(int id) {
        if (id == 1) {
            throw new AException();
        } else if (id == 2) {
            throw new BException();
        }
        return "正常返回";
    }
}
