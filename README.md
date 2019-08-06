# gim
gim是本人业余断断续续时间捣鼓的一个即时通讯框架，把它命名为gim，第一个版本可能比较粗糙，后续会不断优化

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
<!--引入protobuf依赖 -->
<dependency>
<groupId>com.google.protobuf</groupId>
<artifactId>protobuf-java</artifactId>
<version>3.8.0</version>
</dependency>

<dependency>
<groupId>com.google.protobuf</groupId>
<artifactId>protobuf-java-util</artifactId>
<version>3.8.0</version>
</dependency>
```
```
<!--引入json依赖 -->
<dependency>
<groupId>com.alibaba</groupId>
<artifactId>fastjson</artifactId>
<version>1.2.36</version>
</dependency>
```
```
<!--如果需要集群，需引入redis依赖 -->
<dependency>
<groupId>org.apache.commons</groupId>
<artifactId>commons-pool2</artifactId>
<version>2.6.1</version>
</dependency>

<dependency>
<groupId>redis.clients</groupId>
<artifactId>jedis</artifactId>
<version>2.9.0</version>
</dependency>
```
2）简单的示例：

只需几行代码，就可以启动一个服务，客户端就可以连接了

```
//初始化gim配置
GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true)
				.addBsHandler(Type.CONNET_REQ, new ConcentHandler())
				.addBsHandler(Type.ACK_REQ, new AckHandler())
				.addBsHandler(Type.SINGLE_MSG_REQ, new SingleChatHandler())
				.addBsHandler(Type.HEART_BEAT_REQ, new HeartBeatHandler())
				.addBsHandler(Type.GROUP_MSG_REQ, new GroupChatHandler());
    //启动gim服务
    new GimServerStarter(config).start();
    
    //创建一条消息简单的单聊消息
    MessageGenerate.createSingleChatReq("123"/**发送者**/, "456"/**接收者**/, Type.SINGLE_MSG_REQ/**消息类型**/, "这是一条消息");
    // 发送一条消息
    MessagEmitter.sendToUser(userId, builder.build());
```

3）开启SSL

```
//证书地址，证书可以通过keystone生成，限于篇幅，这里不额外举例
String pkPath = ResourceUtils.getURL("classpath:ssl/serverStore.jks")
				.getPath();
//初始化SSL配置，needClientAuth可配置为单向或双向认证
		SSLConfig sslConfig = new SSLConfig().isSSL(true).pkPath(pkPath)
				.caPath(pkPath).passwd("123456").needClientAuth(false);
    //往GimConfig添加ssl配置即可
    GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).sslConfig(sslConfig);
```

4)离线消息配置：

```
//添加一个离线配置
	OfflineConfig offlineConfig = new OfflineConfig().isOffline(true)
				.handleType(OfflineConfig.HANDLER)
				.offlineMsgHandler(new OfflineMsgHandlerIntf() {

					@Override
					public void offlineMsg(
							pres.gogym.gim.packet.MessageClass.Message msg) {
      //按需处理离线消息即可，提供mq或者实现接口方方法
						System.out.println("处理离线消息");

					}
				});
    //添加离线配置
    GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).offlineConfig(offlineConfig);
```

5）开启集群

集群通过redis实现，当然也需要担心消息在Redis与消息服务之间传输的可靠性问题，因为这部分也做了消息确认处理。

```
//添加redis配置
RedisConfig redisConfig = new RedisConfig();
		redisConfig.setHost("");
		redisConfig.setPort(6379);
		redisConfig.setPassword("");
  //集群注意，服务器的标记不能重复，这里标记为s1
		ClusterConfig clusterConfig = new ClusterConfig("s1", redisConfig);
  //开启集群
		clusterConfig.isCluster(true);
  //添加集群配置到config
  GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).clusterConfig(clusterConfig);
```

6)ip黑名单：

拉黑ip非常简单，你可以启动前就配置好需要拉黑的ip,也可以在运行时拉黑

```
//先配置你需要拉黑的ip段
IpRange ipRange = new IpRange("172.31.1.87", "172.31.1.97");
//直接添加到config里就可以了
	GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).addBlackIp(ipRange);
  
  //也可以批量拉黑
  GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).blackIps(blackIps);
```

7）流量监控：

提供全局吞吐量，累计字节等的统计
```
//开启统计
GlobalTrafficConfig globalTrafficConfig = new GlobalTrafficConfig(true);
//添加到配置
GimConfig config = GimConfig.shareInstance();
		config.workerThreads(0).port(5678).autoAck(true).globalTrafficConfig(globalTrafficConfig);
  
  //通过GlobalTrafficMonitor这个类就能获取统计信息
  GlobalTrafficMonitor.currentReadThroughput();
```

8)拓展：
当前已提供基本的通讯功能，如果想要拓展，也是非常简单的
1、添加需要的消息类型，符合protobuf协议，如果使用protobuf，可查看本人博客：https://blog.csdn.net/KokJuis/article/details/54094348
2、添加对应的消息处理器：
  框架使用拔插式设计，对消息处理器的添加非常的容易，只需一行代码
  ```
  继承AbsChatHandler<T extends GeneratedMessageV3> 
  然后往配置里面添加对应的处理即可，如：
  .addBsHandler("你定义的消息类型，框架通过这个类型找到对应处理器", new ConcentHandler())
  ```
 
 9）发送消息
 ```
     
    //创建一条消息简单的单聊消息
 Message msg= MessageGenerate.createSingleChatReq("123"/**发送者**/, "456"/**接收者**/, Type.SINGLE_MSG_REQ/**消息类型**/, "这是一条消息");
    // 发送一条消息
 MessagEmitter.sendToUser(userId, msg);

//发送群消息

//需要@的用户ID
List<String> atUserId=new ArrayList<String>();
atUserId.add("user1");
//创建群消息
Message msg=MessageGenerate.createGroupChatReq("123"/**发送者**/, "g123"/**群id**/, Type.GROUP_MSG_REQ, "这是一条群消息", atUserId);
//发送消息
MessagEmitter.sendToGroup("g123", msg);
 ```
 #### 更多的功能，后续请查看文档（抽空编写中。。。）
 
 
 
 ## 持续更新中.....
 
 ##PS:
 
 IOS以及Android客户端的SDK会在后面开源发布
 
 #### 如果觉得还不错，就点个start支持一下吧。如果你有更好的建议或者发现什么问题，可以提交issues或者pull requests帮助改进吧！
 

 
 
