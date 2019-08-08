/*
 * 文件名：ChatServerStart.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp;

import org.gogym.getty.bootstrap.ServerBootstrap;
import org.gogym.getty.channel.ChannelFuture;
import org.gogym.getty.channel.ChannelOption;
import org.gogym.getty.channel.EventLoopGroup;
import org.gogym.getty.channel.WriteBufferWaterMark;
import org.gogym.getty.channel.nio.NioEventLoopGroup;
import org.gogym.getty.channel.socket.nio.NioServerSocketChannel;
import org.gogym.getty.handler.logging.LogLevel;
import org.gogym.getty.handler.logging.LoggingHandler;
import org.gogym.gim.socket.tcp.cluster.ClusterConfig;
import org.gogym.gim.socket.tcp.cluster.ClusterMsgListener;
import org.gogym.gim.socket.tcp.handler.nettyHandler.ChannelTrafficHandler;
import org.gogym.gim.socket.tcp.listener.queue.DelayMsgQueueListener;
import org.gogym.gim.socket.tcp.offline.OfflineConfig;
import org.gogym.gim.socket.tcp.server.GimConfig;
import org.gogym.gim.socket.tcp.server.GimContext;
import org.gogym.gim.socket.tcp.server.GimServerInitializer;
import org.gogym.gim.socket.tcp.server.GlobalTrafficConfig;

/**
 * 
 * 〈gim启动器〉
 * 
 * @author gogym
 * @version 2019年7月12日
 * @see GimServerStarter
 * @since
 */
public class GimServerStarter {

	private GimConfig gimConfig;

	public GimServerStarter(GimConfig gimConfig) {
		this.gimConfig = gimConfig;
	}

	/**
	 * 
	 * Description: start
	 * 
	 * @throws Exception
	 * @see
	 */
	public void start() throws Exception {

		// 检查配置
		checkConfig();
		// 创建context
		gimConfig.setGimContext(new GimContext());

		// 启动
		startQueueListener();
		startGim();
	}

	private void checkConfig() throws Exception {

		// 启动前，做系统自查，检查集群，离线等配置等
		if (gimConfig == null) {
			throw new Exception("[GimConfig can't be not null]");
		}

		// 检查端口号
		if (gimConfig.getPort() == null) {
			throw new Exception("[the port can't be not null]");
		}

		// 检查离线配置
		if (gimConfig.getOfflineConfig() != null
				&& gimConfig.getOfflineConfig().isOffline()) {

			if (gimConfig.getOfflineConfig().getHandleType() == OfflineConfig.HANDLER) {

				if (gimConfig.getOfflineConfig().getOfflineMsgHandler() == null) {
					throw new Exception(
							"[if offine is open,You need to configure OfflineMsgHandler]");
				}
			} else if (gimConfig.getOfflineConfig().getHandleType() == OfflineConfig.QUEUE) {
				if (gimConfig.getOfflineConfig().getOfflineMsgQueue() == null) {
					throw new Exception(
							"[if offine is open,You need to configure OfflineMsgQueue]");
				}
			}
		}

		// 检查集群配置
		if (gimConfig.getClusterConfig() != null
				&& gimConfig.getClusterConfig().isCluster()) {

			if (gimConfig.getClusterConfig().getServerIdentify() == null) {
				throw new Exception("[the ServerIdentify can't be not null]");
			}

			if (gimConfig.getClusterConfig().getHandleType() == ClusterConfig.REDIS
					&& gimConfig.getClusterConfig().getiRedisProxy() == null) {
				throw new Exception(
						"[if 'HandleType'==ClusterConfig.REDIS, the RedisConfig can't be not null]");
			}

			if (gimConfig.getClusterConfig().getHandleType() == ClusterConfig.HANDLER
					&& gimConfig.getClusterConfig().getClusterMsgHandler() == null) {
				throw new Exception(
						"[if 'HandleType'==ClusterConfig.HANDLER, the clusterMsgHandler can't be not null]");
			}

			// 启动redis消费者监听消息
			ClusterMsgListener clusterMsgListener = new ClusterMsgListener(
					gimConfig);
			clusterMsgListener.start();

		} else {
			// 如果没有配置，则创建一个默认的集群配置,默认不集群
			ClusterConfig clusterConfig = new ClusterConfig();
			gimConfig.clusterConfig(clusterConfig);
		}

		// 检查ssl配置
		if (gimConfig.getSslConfig() != null
				&& gimConfig.getSslConfig().isSSL()) {

			if (gimConfig.getSslConfig().getPkPath() == null
					|| gimConfig.getSslConfig().getPasswd() == null) {
				throw new Exception(
						"[if ssl is true, the pkPath and passwd can't be not null]");
			}

			if (gimConfig.getSslConfig().isNeedClientAuth()
					&& gimConfig.getSslConfig().getCaPath() == null) {
				throw new Exception(
						"[if needClientAuth==true, the caPath  can't be not null]");
			}
		}

		if (gimConfig.getGlobalTrafficConfig() == null) {

			GlobalTrafficConfig globalTrafficConfig = new GlobalTrafficConfig(
					false);
			gimConfig.globalTrafficConfig(globalTrafficConfig);
		}
		

	}

	/**
	 * 
	 * Description: 处理延迟队列监听
	 * 
	 * @see
	 */
	private void startQueueListener() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					DelayMsgQueueListener.takeMessage(gimConfig);
				}
			}
		}.start();
	}

	/**
	 * 
	 * Description: 启动系统服务
	 * 
	 * @see
	 */
	private void startGim() {

		// 启动新的线程托管服务
		new Thread() {
			@Override
			public void run() {

				EventLoopGroup bossGroup = new NioEventLoopGroup();// boss线程池
				EventLoopGroup workerGroup = new NioEventLoopGroup(
						gimConfig.getWorkerThreads());// worker线程池
				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup)
							.channel(NioServerSocketChannel.class)
							//.handler(new LoggingHandler(LogLevel.INFO))
							// 使用TCP
							.childHandler(new GimServerInitializer(gimConfig))
							// 初始化配置的处理器
							.option(ChannelOption.SO_BACKLOG, 128)
							// BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
							.childOption(ChannelOption.SO_KEEPALIVE, true)
							// 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
							.childOption(
									ChannelOption.WRITE_BUFFER_WATER_MARK,
									new WriteBufferWaterMark(
											gimConfig
													.getWRITE_BUFFER_WATER_LOW_MARK(),
											gimConfig
													.getWRITE_BUFFER_HIGH_WATER_MARK()));// 配置水位线

					// 绑定端口，开始接收进来的连接
					ChannelFuture f = b.bind(gimConfig.getPort()).sync();
					System.out.println("[gim已启动，等待连接]");

					// 等待服务器 socket 关闭 ,这不会发生，可以优雅地关闭服务器。
					f.channel().closeFuture().sync();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					workerGroup.shutdownGracefully();
					bossGroup.shutdownGracefully();
				}
			}
		}.start();
	}
}
