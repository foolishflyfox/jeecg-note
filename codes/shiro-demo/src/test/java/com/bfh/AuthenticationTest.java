package com.bfh;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author benfeihu
 */
public class AuthenticationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser() {
        simpleAccountRealm.addAccount("foo", "123456");
    }

    @Test
    public void testAuthentication() {
        // 1. 构建 SecurityManager 环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        // 2. 主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        Subject subject2 = SecurityUtils.getSubject();
        System.out.println("subject == subject2 ? " + (subject == subject2));

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("foo", "12345");
        System.out.println("pos1 is authenticated: " + subject.isAuthenticated());
        subject.login(usernamePasswordToken);
        System.out.println("pos2 is authenticated: " + subject.isAuthenticated());
        subject.logout();
        System.out.println("pos3 is authenticated: " + subject.isAuthenticated());
    }

    @Test
    public void testMyRealm() {
        MyRealmA myRealmA = new MyRealmA();
        SecurityManager securityManager = new DefaultSecurityManager(myRealmA);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken("foo", "000"));

        System.out.println("isAuthenticated: " + subject.isAuthenticated());
        subject.checkRoles("admin", "user");
        subject.checkPermission("user:add");
    }
}
