package com.bfh.demo.listener;

import com.bfh.demo.event.EventA;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 消息监听者是按照时间类型进行监听的
 * @author benfeihu
 */
@Component
@Scope("prototype")
//@Scope("singleton")
public class ListenerA implements ApplicationListener<EventA> {
    /**
     * 即使有两个实例，监听函数也就执行一次
     * @param sendMessageEvent
     */
    @Override
    public void onApplicationEvent(EventA sendMessageEvent) {
        System.out.printf("ListenA get EventA from %s: %s\n",
                sendMessageEvent.getSource().getClass().getName(), sendMessageEvent.getMessage());
    }
}
