package com.bfh.annotation.lazzy;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author benfeihu
 */
@Component
@Lazy
public class LazyComponent {
    public LazyComponent() {
        System.out.println("LazyComponent is constructing...");
    }
}
