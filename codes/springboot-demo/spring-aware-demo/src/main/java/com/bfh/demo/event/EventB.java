package com.bfh.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author benfeihu
 */
public class EventB extends ApplicationEvent {
    public EventB(Object source) {
        super(source);
    }
}
