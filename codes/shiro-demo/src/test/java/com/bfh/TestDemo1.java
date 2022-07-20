package com.bfh;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author benfeihu
 */
public class TestDemo1 {
    @Test
    public void testHelloWorld() {
        // 获取 SecurityManager 工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "classpath:shiro.ini");
        // 得到 SecurityManager 实例，并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("fo", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("身份认证失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.login(token);
    }
//    @Test
//    public void testMyRealm1() {
//        // 获取 SecurityManager 工厂
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
//                "classpath:shiro-realm.ini");
//        // 得到 SecurityManager 实例，并绑定给 SecurityUtils
//        SecurityManager securityManager = factory.getInstance();
//        SecurityUtils.setSecurityManager(securityManager);
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken("fo", "123");
//        try {
//            subject.login(token);
//        } catch (AuthenticationException e) {
//            System.out.println("身份认证失败");
//        }
//        Assert.assertEquals(true, subject.isAuthenticated());
//        subject.login(token);
//    }
}
