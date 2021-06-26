# springboot-api

* 支持maven、gradle 编译 、打包

## 模块

- application 应用，包括Spring服务、JPA实现等
- domain 业务领域，纯业务逻辑部分

## 测试

- application 使用 cucumber测试
- domain 使用junit5

## 命令行操作

### 方式一：gradle

#### 1、运行测试

* 运行所有模块测试

```shell
./gradlew test
```

* 运行指定模块测试

```shell
./gradlew test -Pmodule=application
```

#### 2、编译项目

* 编译项目

```shell
./gradlew clean build
```

* 编译指定模块

```shell
./gradlew clean build -Pmodule=application
```

#### 3、本地运行项目

* 本地启动mysql和数据库查看工具

```shell
cd env/local
docker-compose up
```

* 运行项目

```shell
./gradlew 
 ```

### 方式二：maven

#### 1、运行测试

* 运行测试

```shell
mvn clean test
```

* 运行指定模块测试

```shell
mvn clean test -pl application
```

#### 2、编译项目

* 编译打包

```shell
mvn clean package
```

* 编译指定模块

```shell
mvn clean package -pl application
```

#### 3、本地运行项目

* 本地启动mysql和数据库查看工具

```shell
cd env/local
docker-compose up
```

* 运行项目

```shell
mvn spring-boot:run -pl application
 ```

## sonar

http://115.28.94.6:19000
