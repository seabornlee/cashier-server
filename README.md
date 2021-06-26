# springboot-api

# 模块

- application 应用，包括Spring服务、JPA实现等
- domain 业务领域，纯业务逻辑部分

# 测试介绍

- application 使用 cucumber测试
- domain 使用junit5

## gradle方式

* 运行所有模块测试

```shell
./gradlew test
```

* 运行指定模块测试

```shell
./gradlew test -Pmodule=application
```

### 编译项目

```shell
./gradlew clean build
```

* 编译指定模块

```shell
./gradlew clean build -Pmodule=application
```

* 本地启动mysql和数据库查看工具

```shell
cd env/local
docker-compose up
```

### 本地运行

## maven 方式

根目录下打包

```shell
mvn clean package
```

运行application项目

```shell
 mvn spring-boot:run 
 ```
