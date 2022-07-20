package com.bfh.demo.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * @author benfeihu
 */
public class EventA extends ApplicationEvent {
    public EventA(Object source) {
        super(source);
    }

    @Getter
    @Setter
    private String message;

    public EventA(Object source, String message) {
        super(source);
        this.message = message;
    }
}
