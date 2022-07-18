# Spring Cloud Gateway

Spring Cloud Gateway 是由 WebFlux + Netty + Reactor 实现的非阻塞响应式的 API 网关。

Spring Cloud Gateway 旨在为微服务框架提供一种简单且有效的管理方式，并给予 Filter 的方式提供网关的基本功能，例如安全认证、监控、限流等。

Spring Cloud Gateway 功能特性：
- 基于 Spring Fraework 5，Project Reactor 和 Spring Boot 2.0 进行构建；
- 动态路由：能够匹配任何请求属性
- 支持路径重写
- 集成 Spring Cloud 服务发现功能(Nacos, Eruka)
- 可集成流控降级功能(Sentinel, Hystrix)
- 可以对路由指定易于编写的Predicate(断言)和Filter(过滤器)

核心概念：
- 路由(route)：路由是网关中最基本的部分，路由信息包括一个 ID，一个目的 URI，一组断言工厂，一组 Filter 组成。如果断言为真，则说明请求和配置的路由匹配。
- 断言(predicates): Java8 中的断言函数，SpringCloud Gateway 中的断言函数类型是 Spring5.0 框架中的 ServerWebExchange，断言函数允许开发者去定义匹配 Http request 中的任何信息，比如请求图和参数等。
- 过滤器(filter): SpringCloud Gateway 中的 Filter 分为 Gateway Filter 和 Global Filter。Filter 可以对请求和相应进行处理。

## 
