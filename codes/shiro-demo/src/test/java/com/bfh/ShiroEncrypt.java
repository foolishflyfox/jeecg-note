package com.bfh;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @author benfeihu
 */
public class ShiroEncrypt {
    @Test
    public void encryptWithSalt() {
        String password = "123456";
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodePassword = new SimpleHash(algorithmName, password, salt, times).toString();
        System.out.printf("原始密码: %s\nsalt: %s\n运算次数: %d\n密文: %s\n",
                password, salt, times, encodePassword);
    }
}
