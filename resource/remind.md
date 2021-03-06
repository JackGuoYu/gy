# Spring cloud分布式框架使用说明书

hello,我叫jack ！该架构的搭建也不难，目前该项目工程没有实现任何业务功能，只是将微服务的一些常见功能开通使用，方便大家搭建项目，减少时间成本。下面，我先整理出该项目的集成的功能。


## Spring boot
每一个服务使用的是**spring boot**,版本是**2.2.6.RELEASE**，集成spring boot让配置功能更加便捷，启动服务更加方便。

## 微服务框架
我们使用的是**spring cloud** 全家桶，里面集成了**注册中心、配置中心、远程调用、路由网关、消息总线、熔断器、负载均衡器**，版本是**Hoxton.SR3**

## 业务服务
### 功能扩展
* 数据库 **mysql**
* 缓存 **集群redis,3主3从配置**
* 消息中间件 **kafka** 版本：2.2.6.RELEASE 、 **rabbitmq**
* orm框架 **mybatis-plus** 
* 分布式事务 **fescar** 

## 所需环境
你需要安装并启动一些功能服务，该项目才能运行起来，否则即使运行起来，该项目还是有部分功能残缺。
 
 * **maven仓库** （必须安装）
 * **mysql5.7 或5.7版本以上** （必须启动）
 * **redis集群6.0.6**（必须启动，注意端口配置）
 * **kafka2.6.0**		（必须启动,zk也需要启动）
 * **rabbitmq3.8.8** (可不启动，用于消息总线)
 * **fescar-server.sh** (可不启动，用于分布式事务)

 如果你是mac用户，那么你可以直接使用**brew install 服务名称** 进行环境安装；如果不是，请网上自行查阅下载安装
 
 
## 项目启动步骤
1. 启动注册中心（gy-eureka）
2. 启动配置中心（gy-config）
3. 启动网关 （gy-gateway）
4. 启动业务服务 （gy-user、gy-pay）

## 使用
该项目已经集成了业务常用框架，能够满足大部分用户的需求。

### 配置中心
对于注册中心，由于配置简单明了，就不做介绍。配置中心有几个要点要说明一下。

* 该项目已经实现了消息总线，也就是说，如果环境都启动了，那配置中心可不必每次都重新启动刷新配置。
    举个例子：假设有一个服务的配置更改了，然后提交到git上，而这个配置关联到其他业务服务，那么可不必每个服务都重新启动一次，可调用如下地址：
   ```http://localhost:9002/actuator/bus-refresh``` 
   
   9092是配置中心的端口，该地址会触发消息总线进行业务内存的配置刷新。
   
* 配置的格式，有细看会发现分了几个文件bootstrap.yml、application.yml、application-dev.yml、application-prod.yml等。 根据spring框架的配置优先加载顺序是：bootstrap.yml -> application.yml,而如果application.yml文件里有配置：**spring.profiles.active: dev**，则会定位到application-dev.yml文件。 之所以这样分，是因为涉及到部署环境，开发环境有开发环境的配置，产线环境有产线环境的配置。

* 配置中心里的**具体配置**都是放在git代码仓库进行管理的，只有这样才能保证私密数据的安全。（虽然可以根据git登陆获取，但是最好做一层隔离）

### 网关
使用网关可使得用户访问有个总开关去管理服务，提供了一些功能可以进行**数据监控、鉴权、异常处理、路由规则匹配、限流、负载均衡**等等。

网关服务主要通过配置实现路由控制、限流、负载均衡，具体的配置规则，可查阅资料进行详细配置。
如果还要实现数据监控、异常处理需要开发者自己编写实现类。

### 公共服务
每个业务服务可依赖公共服务**gy-common**,公共服务旨在将一些公共的组件、功能、工具、方法等抽取出来服务于每个需要用到的业务服务。该项目公共服务提供了功能如下：

* 集成kafka消息中间件，可使用消息队列工具
* 提供远程调用服务接口，及熔断后的备案模式

### gy-fescar
如果两个服务间需要同时执行写入操作，则依赖该服务实现分布式事务。

### 业务服务
业务服务集成了mybatis-plus插件、redis工具。mybatis-plus实现了分页插件，提高开发效率。mybatis-plus的具体使用，请自行上网查阅。



 
 
 


