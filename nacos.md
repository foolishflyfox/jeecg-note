# nacos 的使用

nacos 是一个服务发现与配置中心，要使用 nacos，必须引入 spring-boot、spring-cloud-alibaba。
```xml
    <properties>
        <java.version>1.8</java.version>
        <spring.cloud.alibaba.version>2.2.6.RELEASE</spring.cloud.alibaba.version>
        <spring.boot.version>2.3.2.RELEASE</spring.boot.version>
        <spring.cloud.version>Hoxton.SR9</spring.cloud.version>
    </properties>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring.cloud.alibaba.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-parent</artifactId>
                <version>${spring.boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!-- OpenFeign 在该依赖中，因此也引入 -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring.cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

## nacos 的自动发现功能

### 启动 nacos 服务

从官网下载对应版本的 nacos，参考 [spring-cloud-alibaba 版本说明](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)。例如我们选择了 2.2.6.RELEASE，那么 nacos 版本就需要选择 1.4.2。

下载对应的版本后，压缩包中有如下的文件：
```shell
$ tar tf nacos-server-1.4.2.tar.gz
nacos/LICENSE
nacos/NOTICE
nacos/target/nacos-server.jar
nacos/conf/
nacos/conf/1.4.0-ipv6_support-update.sql
nacos/conf/schema.sql
nacos/conf/nacos-mysql.sql
nacos/conf/application.properties.example
nacos/conf/nacos-logback.xml
nacos/conf/cluster.conf.example
nacos/conf/application.properties
nacos/bin/startup.sh
nacos/bin/startup.cmd
nacos/bin/shutdown.sh
nacos/bin/shutdown.cmd
```
其中 *.cmd* 后缀的文件为 Windows 下使用的脚本，*.sh* 后缀的文件为 Linux 下使用的脚本。修改对应操作系统下的 startup 脚本，将 cluster(集群)模式变为 standalone(单机)模式。

直接执行 `nacos/bin/startup.sh` 即可启动 nacos 服务。通过 http://localhost:8848/nacos 可以访问 nacos 的管理界面，默认的用户名与密码为 nacos/nacos 。

### 编写 restful 风格的 web 服务

因为微服务之间是以 restful 进行通信的，因此必须引入 spring-boot-web 模块。这里我们创建两个服务：sell-service 和 stock-service。

#### sell-service

SellServiceController.java 的内容为：
```java
@RestController
@RequestMapping("/sell")
public class SellServiceController {
    @RequestMapping("/simpleSell")
    public String simpleSell() {
        return "商品售卖--无参数";
    }
}
```
SellServiceMain.java 的内容为：
```java
@SpringBootApplication
public class SellServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(SellServiceMain.class, args);
    }
}
```
在 resources 中创建 *application.yaml* 文件，配置为：
```yaml
spring:
  application:
    name: sell-service
server:
  port: 8000
```
在启动 spring-boot 服务后，我们就可以通过 *curl localhost:8000/sell/simpleSell* 访问服务了。

#### stock-service

stock-service 的代码与 sell-service 相似。

StockServiceController.java :
```java
@RestController
@RequestMapping("/stock")
public class StockServiceController {
    @RequestMapping("/simpleStock")
    public String simpleStock() {
        return "无参数调用: simpleStock";
    }
}
```

StockServiceMain.java :
```java
@SpringBootApplication
public class StockServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(StockServiceMain.class, args);
    }
}
```

application.yaml:
```yaml
spring:
  application:
    name: stock-service
server:
  port: 8001
```

### 将服务提供方注册到 nacos 中

我们将 stock-service 注册到 nacos，stock-service 作为服务提供方，sell-service 作为服务消费方。

- 引入 nacos 依赖
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

- 配置 application.xml 配置 nacos 注册地址
```yaml
spring:
  application:
    name: stock-service
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848
```
这样，就将 stock-service 注册到 nacos 上了。

### 通过 RestTemplate 消费服务

- 服务消费方也需要引入 nacos 的包。
```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

- 在 application.xml 中指定 nacos 地址，即可完成服务发现功能
```yaml
spring:
  application:
    name: sell-service
  cloud:
    nacos:
      server-addr: 127.0.0.1:8848
```

- 通过 RestTemplate 访问服务，RestTemplate 是一个 rest api 的客户端。因为 RestTemplate 在 SpringBoot web 模块中，因此不需要再引入其他的模块。在 Spring 的配置类中创建 RestTemplate 实例。
```java
@Bean
@LoadBalanced  // 必须加上 LoadBalance 指定负载均衡器
RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
    return restTemplateBuilder.build();
}
```

- 通过 *restTemplate* 访问 stock-service：
```java
@RestController
@RequestMapping("/sell")
public class SellServiceController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/simpleSell")
    public String simpleSell() {
        String stockResult = restTemplate.getForObject(
                "http://stock-service/stock/simpleStock",  // 指定服务地址
                String.class);  // 指定返回类型
        return "商品售卖--无参数, 调用 stock-service 结果: " + stockResult;
    }
}
```
执行 `curl localhost:8000/sell/simpleSell` 结果为：
```
商品售卖--无参数, 调用 stock-service 结果: 无参数调用: simpleStock
```

### 通过 Feign 消费服务

1. 引入 OpenFeign 依赖包:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2. 为 SpringBoot 应用添加注解，启用 Feign 客户端功能：
```java
@SpringBootApplication
@EnableFeignClients
public class SellServiceMain {
    ... ...
}
```

3. 定义与服务方定义相同的接口
```java
@FeignClient(name = "stock-service", path = "/stock")
public interface StockService {

    @RequestMapping("/simpleStock")
    String simpleStock();
}
```

4. 自动装载 StockService 并调用服务：
```java
    @Autowired
    private StockService stockService;

    @GetMapping("/feignSell")
    public String feignSell() {
        String stockResult = stockService.simpleStock();
        return "feignSell call stock-service result: " + stockResult;
    }
```

5. 测试，执行 ` curl localhost:8000/sell/feignSell`，输出为：
```
feignSell call stock-service result: 无参数调用: simpleStock%
```

## nacos 的配置中心功能


