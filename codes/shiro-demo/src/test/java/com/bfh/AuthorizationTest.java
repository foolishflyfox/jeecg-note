package com.bfh;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author benfeihu
 */
public class AuthorizationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void before() {
        simpleAccountRealm.addAccount("foo", "000", "admin", "user");
    }

    @Test
    public void testRole() {
        SecurityManager securityManager = new DefaultSecurityManager(simpleAccountRealm);

        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken("foo", "000");
        subject.login(authenticationToken);
        Assert.assertTrue(subject.isAuthenticated());
//        subject.checkRole("admin");
        Assert.assertTrue(subject.hasRole("admin"));
        System.out.println(Arrays.toString(subject.hasRoles(Arrays.asList("admin", "xx", "user"))));

    }
}
