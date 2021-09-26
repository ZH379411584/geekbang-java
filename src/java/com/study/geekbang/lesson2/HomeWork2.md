
## 垃圾回收算法

### 标记清除

### 标记整理

### 复制



## 不同GC总结
### Serial
用于新生代，单线程，使用复制算法，执行垃圾收集时Stop the world。
### Serial Old
用于老年代，单线程，使用标记整理算法，执行垃圾收集时Stop the world。

### ParNew
Serial多线程版本
### Parallel Scavenge 
用于新生代，使用复制算法，目标是达到一个可控制的吞吐量。

### Parallel Old

Parallel Scavenge 老年代版本

### CMS
用于老年代，使用标记清除算法，目的是希望停顿时间最短。

运行过程
1. 初始标记
2. 并发标记
3. 重新标记
4. 并发清除

初始标记和重新标记 这两个步骤仍然会Stop The World。

### G1

用于新生代和老年代，将Java堆划分为多个大小相等的独立区域(Region)。


### 不同垃圾收集器配置
垃圾收集器配置 | 新生代垃圾收集器 | 新生代垃圾回收算法 | 老年代垃圾收集器  | 老年代垃圾回收算法
---|---|---|---|---
UseSerialGC | Serial | 复制算法 | Serial Old | 标记整理
UseParNewGC | ParNew | 复制算法 | Serial Old | 标记整理
UseParallelGC | Parallel Scavenge | 复制算法 | Serial Old（改进后的jdk版本默认开启ParallelOld） | 标记整理
UseParallelOldGC | Parallel Scavenge | 复制算法 | ParallelOld | 标记整理
UseConcMarkSweepGC | ParNew | 复制算法 | CMS | 标记清除
UserG1GC | G1 | 复制算法 | G1 | 复制算法




