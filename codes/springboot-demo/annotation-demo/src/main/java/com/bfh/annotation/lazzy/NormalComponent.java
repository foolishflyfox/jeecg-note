package com.bfh.annotation.lazzy;

import org.springframework.stereotype.Component;

/**
 * @author benfeihu
 */
@Component
public class NormalComponent {
    public NormalComponent() {
        System.out.println("NormalComponent is contructing...");
    }
}
