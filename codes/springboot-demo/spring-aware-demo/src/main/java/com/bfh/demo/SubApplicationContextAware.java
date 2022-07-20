package com.bfh.demo;

import com.bfh.demo.listener.ListenerA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContextAware 可以获取容器环境
 * @author benfeihu
 */
@Component
public class SubApplicationContextAware
        implements ApplicationContextAware
{
    private static ApplicationContext applicationContext;

    @Autowired
    ListenerA listenerA1;

    @Autowired
    ListenerA listenerA2;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SubApplicationContextAware.applicationContext = applicationContext;
        System.out.println("listenerA1 == listenerA2 ? " + (listenerA1 == listenerA2));
    }

    static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
