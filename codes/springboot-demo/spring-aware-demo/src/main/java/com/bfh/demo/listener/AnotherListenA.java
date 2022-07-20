package com.bfh.demo.listener;

import com.bfh.demo.event.EventA;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author benfeihu
 */
@Component
public class AnotherListenA implements ApplicationListener<EventA> {
    @Override
    public void onApplicationEvent(EventA eventA) {
        System.out.printf("AnotherListenA get EventA from %s: %s\n",
                eventA.getSource().getClass().getName(), eventA.getMessage());
    }
}
