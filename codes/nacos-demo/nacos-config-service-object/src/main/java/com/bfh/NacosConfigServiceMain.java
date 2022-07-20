package com.bfh;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

/**
 * @author benfeihu
 */
@SpringBootApplication
public class NacosConfigServiceMain implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigServiceMain.class, args);
    }

    private ConfigService configService;

    @Override
    public void run(String... args) throws Exception {
        // 通过 ConfigService 获取 nacos 的配置信息
        configService = createConfigService("localhost:8848",
                "dev", "nacos", "nacos");
        String result = getConfig("config-service", "DEFAULT_GROUP", 1000);
        System.out.println("config content:\n" + result + "\n");

    }

    private String getConfig(String dataId, String group, long timeoutMs) throws NacosException {
        String result = "";
        if (null != configService) {
            result = configService.getConfig(dataId, group, timeoutMs);
        }
        return result;
    }

    private ConfigService createConfigService(String serverAddr, String namespace,
                                              String username, String password) throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", serverAddr);
        properties.setProperty("namespace", namespace);
        properties.setProperty("username", username);
        properties.setProperty("password", password);
        return NacosFactory.createConfigService(properties);
    }
}
