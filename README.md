# gim
gim是本人业余断断续续时间捣鼓的一个即时通讯框架，把它命名为gim

### 一、什么是gim？
gim是基于netty封装的及时通讯框架

### 二、写gim的目的是什么？
希望开发者更专注于业务，而不需要在比较复杂的及时通讯框架上花太多的时间。还有一个算是情怀吧！

### 三、gim能做什么？
简单的代码，就能轻松实现高可用的im应用，开发项目，移植到现有的项目中去，都是非常的方便。

### 四、gim目前拥有的能力：
1）基于netty强大稳定的性能。
 
2）基于谷歌出品的protobuf数据协议，高效的传输效率。

3)不需要关心连接问题，gim已经实现断线重连、心跳保活机制。

4）不需要关心安全问题，gim已经实现ssl单/双向认证，只需几行代码便能实现SSL。

5）不需要关心消息了可靠性问题，gim已经实现消息应答，失败重发，离线处理等机制。

6）不需要花太多时间实现聊天功能，gim已经做好了单聊，群聊功能，几行代码搞定聊天。

7）对某个ip不爽？没关系，gim已经实现ip拉黑功能，随时拉黑，解封。

8）系统集群太复杂？放心，gim已经实现集群功能，只需几行代码，就能轻松实现高可用的im系统。

9）流控，流量统计太麻烦，不懂？gim已经做了过载流量限制，ip流量统计，吞吐量统计等。

10）消息拆包/粘包太复杂？放心，gim已经实现健壮的拆包粘包机制。

11）不知如何与客户端集成？没关系，gim会为你提供ios/android客户端sdk（编码中），让开发客户端的小伙伴轻松接入。

12）相对优雅的设计模式，随时自定义，方便的热拔插设计，拓展非常方便。

13）性能不够？netty单机百万连接不是吹的，gim基于netty，性能非常不错。

14）易用的接口，消息发送，绑定，监听，都已实现了简易的接口，一看就会用。

### 五、简单的使用：
1）使用gim前，先添加以下依赖：


```
<!--netty依赖 -->
<dependency>
<groupId>io.netty</groupId>
<artifactId>netty-all</artifactId>
<version>4.1.36.Final</version>
</dependency>
```
```
<!-- 如果需要使用openSSL,需添加下面两个依赖，使用JDK提供的ssl则不需要 -->
<dependency>
<groupId>io.netty</groupId>
<artifactId>netty-tcnative</artifactId>
<version>2.0.25.Final</version>
<scope>runtime</scope>
</dependency>
    
<dependency>
<groupId>io.netty</groupId>
<artifactId>netty-tcnative-boringssl-static</artifactId>
<version>2.0.25.Final</version>
<scope>runtime</scope>
</dependency>
```

