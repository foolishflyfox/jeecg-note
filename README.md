# jeecg-note

## 前端配置修改

*.env.development* 文件修改：
```
VITE_PROXY = [["/jeecgboot","http://localhost:9999"],["/upload","http://localhost:3300/upload"]]
VITE_GLOB_DOMAIN_URL=http://localhost:9999
```
注意，不能写成 http://localhost:9999/jeecgboot ，因为 gateway 的转发规则定义中也没有 jeecgboot 的前缀。

## jeecg-cloud-gateway

jeecg 在微服务下的配置是从 nacos 中获取的，因此在 gateway 启动之前，必须先启动 jeecg-cloud-nacos 服务。

jeecg-cloud-gateway 的路由配置根据 jeecg-boot-starter / jeecg-boot-starter-cloud 中的 bootstrap.yml 获取注册中心，data-id 等的相关信息。

其中的 `spring.cloud.nacos.config.prefix` 的值为 `@prefix.name@`，表示从 pom 文件中获取前缀，配置值为 jeecg 。而我们选中的 profiles 值为 dev，因此 gate 会去 nacos 上 namespce 为 public，group 默认，读取 jeecg-dev.yaml 的配置。



