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





