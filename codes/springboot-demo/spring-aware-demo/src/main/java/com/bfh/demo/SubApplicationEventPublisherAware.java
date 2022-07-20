package com.bfh.demo;

import com.bfh.demo.event.EventA;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作为消息的发布者发布消息，用于消息间的解耦
 * setApplicationEventPublisher 用于获取发布对象实例
 * @author benfeihu
 */
@RestController
public class SubApplicationEventPublisherAware implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    @GetMapping("/eventA")
    public String eventA() {
        publisher.publishEvent(new EventA(this, "hello"));
        return "ok";
    }
}
