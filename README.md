# geekbang-java
极客时间Java训练营

## Lesson1 JVM

2.（必做）自定义一个 Classloader，加载一个 Hello.xlass 文件，执行 hello 方法，此文件内容是一个 Hello.class 文件所有字节（x=255-x）处理后的文件。文件群里提供。
 
[答案](https://github.com/ZH379411584/geekbang-java/blob/main/src/java/com/study/geekbang/lesson1/CustomerClassLoader.java)


3.（必做）画一张图，展示 Xmx、Xms、Xmn、Meta、DirectMemory、Xss 这些内存参数的关系。

[答案](https://github.com/ZH379411584/geekbang-java/blob/main/src/java/com/study/geekbang/lesson1/jvm.png)


## Lesson2 JVM && NIO
  
1. （选做）使用 GCLogAnalysis.java 自己演练一遍 串行/并行/CMS/G1 的案例。

2. （选做）使用压测工具（wrk 或 sb），演练 gateway-server-0.0.1-SNAPSHOT.jar 示例。

3. （选做）如果自己本地有可以运行的项目，可以按照 2 的方式进行演练。

4. （必做）根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结，提交到 GitHub。  
[答案](./src/java/com/study/geekbang/lesson2/HomeWork2.md)

5. （选做）运行课上的例子，以及 Netty 的例子，分析相关现象。

6. （必做）写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub
[OkHttpDemo](./src/java/com/study/geekbang/lesson2/OkHttpDemo.java)


## Lesson3 Netty 
1. （必做）整合你上次作业的 httpclient/okhttp；
2. （选做）使用 netty 实现后端 http 访问（代替上一步骤）
3. （必做）实现过滤器。
4. （选做）实现路由。
5. （选做）跑一跑课上的各个例子，加深对多线程的理解
6. （选做）完善网关的例子，试着调整其中的线程池参数

[作业地址](./homework3)


## Lesson 4
1.（选做）把示例代码，运行一遍，思考课上相关的问题。也可以做一些比较。


2.（必做）思考有多少种方式，在 main 函数启动一个新线程，运行一个方法，拿到这
个方法的返回值后，退出主线程? 写出你的方法，越多越好，提交到 GitHub。
一个简单的代码参考:  https://github.com/kimmking/JavaCourseCodes/tree/main/03concurrency/0301 /src/main/java/java0/conc0303/Homework03.java

[答案](./homework4/src/main/java/com/study/concurrent/HomeWork4_2.java)

3.（选做）列举常用的并发操作API和工具类，简单分析其使用场景和优缺点。
#### synchronize
Java 语言级别的实现，简单，无法实现中断。
#### Lock
API 实现，有公平非公平锁，可以尝试加锁，可以中断。
#### CAS
 无锁实现，不会死锁
 
 使用场景：在压力一般，争夺资源不激烈的时候。


#### Semaphore
线程并发执行的线程的数量
#### CountDownLatch
协调多个线程在同一时刻执行。
#### CyclicBarrier
协调多个任务在一个`任务执行完成后执行。

4.（选做）请思考: 什么是并发? 什么是高并发? 实现高并发高可用系统需要考虑哪些因素，对于这些你是怎么理解的?
#### 什么是并发？
指在某个时间段内，多任务交替的执行任务。
#### 什么是高并发？
只在某个时刻内，更多的任务在执行，充分利用CPU多核。
#### 需要考虑哪些因素？
1. 开启新任务的开销。
2. 各个任务的协作。
3. 共享资源的控制。

5.（选做）请思考: 还有哪些跟并发类似/有关的场景和问题，有哪些可以借鉴的解决办法。

并发太大，导致系统扛不住。

#### MQ 
使用MQ存储，根据系统的能力来执行任务。
#### 熔断，降级
达到设置的最大并发量后，直接拒绝。


6.（必做）把多线程和并发相关知识梳理一遍，画一个脑图，截图上传到 GitHub 上。 可选工具:xmind，百度脑图，wps，MindManage，或其他。


[答案](./homework4/src/main/java/com/study/concurrent/java并发.png)


## Lesson 5
1.（选做）使 Java 里的动态代理，实现一个简单的 AOP。
[答案](./homework5_spring/src/main/java/com/study/spring/aop/SimpleAop.java)

2.（必做）写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 GitHub。
### Xml
1. 屬性注入
2. 构造器注入
### 注解
1. @Resource
2. @Autowired
3. @Bean
### java
1. 实现ApplicationContextAware

3.（选做）实现一个 Spring XML 自定义配置，配置一组 Bean，例如：Student/Klass/School。
4.（选做，会添加到高手附加题）
4.1 （挑战）讲网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式；
4.2 （挑战）基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现；
4.3 （中级挑战）基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息；
4.4 （中级挑战）尝试使用 ByteBuddy 实现一个简单的基于类的 AOP；
4.5 （超级挑战）尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP。
5.（选做）总结一下，单例的各种写法，比较它们的优劣。

6.（选做）maven/spring 的 profile 机制，都有什么用法？

7.（选做）总结 Hibernate 与 MyBatis 的各方面异同点。

8.（必做）给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

[boot](./homework5_spring/src/main/java/com/study/spring/boot)
[StudentAppTest](./homework5_spring/src/test/java/com/study/spring/boot/StudentAppTest.java)

9.（选做）学习 MyBatis-generator 的用法和原理，学会自定义 TypeHandler 处理复杂类型。

10.（必做）研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
1）使用 JDBC 原生接口，实现数据库的增删改查操作。

2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
[JdbcTest](./homework5_spring/src/test/java/com/study/spring/jdbc/JdbcTest.java)
3）配置 Hikari 连接池，改进上述操作。提交代码到 GitHub。

[HikariTest](./homework5_spring/src/test/java/com/study/spring/jdbc/HikariTest.java)