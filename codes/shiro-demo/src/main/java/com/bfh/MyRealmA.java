package com.bfh;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author benfeihu
 * 认证：确定用户身份(用户名-密码)
 * 鉴权：控制用户对系统资源的访问进行控制。后台接口访问，前台页面元素，敏感数据。
 * RBAC：Role Based Access Control
 */
public class MyRealm1 extends AuthorizingRealm {

    /*
     * 模拟数据库数据
     */
    Map<String, String> userMap = new HashMap<>(16);

    public MyRealm1() {
        userMap.put("foo", "000");
        super.setName("MyRealm1");
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> permissions = new HashSet<>();
        permissions.add("user:delete");
        permissions.add("user:add");
        return permissions;
    }

    private Set<String> getRolesByUserName(String userName) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        roles.add("user");
        return roles;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String userName = (String) principalCollection.getPrimaryPrincipal();

        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);

        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        simpleAuthenticationInfo.setRoles(roles);
        simpleAuthenticationInfo.setStringPermissions(permissions);
        return simpleAuthenticationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName = (String) authenticationToken.getPrincipal();
        String password = getPasswordByUserName(userName);
        if (null == password) {
            return null;
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, password, this.getName());
        return authenticationInfo;
    }

    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }
}
