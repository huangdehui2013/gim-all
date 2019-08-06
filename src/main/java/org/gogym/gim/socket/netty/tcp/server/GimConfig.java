/*
 * 文件名：GimConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.server;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.gogym.gim.entity.IpRange;
import org.gogym.gim.socket.netty.tcp.cluster.ClusterConfig;
import org.gogym.gim.socket.netty.tcp.handler.AbsChatHandler;
import org.gogym.gim.socket.netty.tcp.handler.nettyHandler.ChannelTrafficHandler;
import org.gogym.gim.socket.netty.tcp.intf.GimListenerIntf;
import org.gogym.gim.socket.netty.tcp.message.PacketConfig;
import org.gogym.gim.socket.netty.tcp.offline.OfflineConfig;
import org.gogym.gim.socket.netty.tcp.ssl.SSLConfig;

/**
 * 
 * 〈配置类〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年7月12日
 * @see GimConfig
 * @since
 */
public class GimConfig {

	// 业务处理器集合
	private Map<Integer, AbsChatHandler<?>> handlerMap = new HashMap<>();

	// 离线配置
	private OfflineConfig offlineConfig;

	// 集群配置
	private ClusterConfig clusterConfig;

	// ip黑名单
	private CopyOnWriteArrayList<IpRange> blackIps = new CopyOnWriteArrayList<IpRange>();

	// ssl配置
	private SSLConfig sslConfig;

	// 全局流量监控
	private GlobalTrafficConfig globalTrafficConfig;

	private PacketConfig packetConfig = new PacketConfig();

	private GimContext gimContext;

	private GimListenerIntf gimListener;

	// 端口
	private Integer port;

	// worker线程池大小
	private int workerThreads = 0;

	// 最低水位线
	private int WRITE_BUFFER_WATER_LOW_MARK = 64 * 1024;

	// 最高水位线
	private int WRITE_BUFFER_HIGH_WATER_MARK = 256 * 1024;

	// 是否自动发送ack
	private boolean autoAck = true;

	// 心跳检测时间，默认120秒
	private int readerIdleTime = 120;

	private static class LazyHolder {
		private static final GimConfig gimConfig = new GimConfig();
	}

	private GimConfig() {
	}

	public static GimConfig shareInstance() {
		return LazyHolder.gimConfig;
	}

	// 端口号
	public GimConfig port(int port) {
		this.port = port;
		return this;
	}

	// workerThreads线程池大小
	public GimConfig workerThreads(int nThreads) {
		this.workerThreads = nThreads;
		return this;
	}

	// 配置最低水位线
	public GimConfig writeBufferWaterLowMark(int mark) {
		this.WRITE_BUFFER_WATER_LOW_MARK = mark;
		return this;
	}

	public GimConfig writeBufferWaterHighMark(int mark) {
		this.WRITE_BUFFER_HIGH_WATER_MARK = mark;
		return this;
	}

	public GimConfig autoAck(boolean auto) {
		this.autoAck = auto;
		return this;
	}

	public GimConfig readerIdleTime(int readerIdleTime) {
		this.readerIdleTime = readerIdleTime;
		return this;
	}

	public GimConfig addBsHandler(Integer type, AbsChatHandler<?> handler) {
		this.handlerMap.put(type, handler);
		return this;
	}

	public GimConfig addPacket(com.google.protobuf.GeneratedMessageV3 packet) {
		packetConfig.addType(packet.getDescriptorForType());
		return this;
	}

	public GimConfig offlineConfig(OfflineConfig offlineConfig) {
		this.offlineConfig = offlineConfig;
		return this;
	}

	public GimConfig clusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = clusterConfig;
		return this;
	}

	public GimConfig blackIps(CopyOnWriteArrayList<IpRange> blackIps) {
		this.blackIps = blackIps;
		return this;
	}

	public GimConfig addBlackIp(IpRange ipRange) {
		this.blackIps.add(ipRange);
		return this;
	}

	public GimConfig sslConfig(SSLConfig sslConfig) {
		this.sslConfig = sslConfig;
		return this;
	}

	public GimConfig globalTrafficConfig(GlobalTrafficConfig globalTrafficConfig) {
		this.globalTrafficConfig = globalTrafficConfig;
		return this;
	}

	public GimConfig gimListener(GimListenerIntf gimListener) {
		this.gimListener = gimListener;
		return this;
	}

	// ------------------------------------------------------

	public int getWorkerThreads() {

		return workerThreads;
	}

	public int getWRITE_BUFFER_WATER_LOW_MARK() {

		return WRITE_BUFFER_WATER_LOW_MARK;
	}

	public int getWRITE_BUFFER_HIGH_WATER_MARK() {

		return WRITE_BUFFER_HIGH_WATER_MARK;
	}

	public boolean isAutoAck() {

		return autoAck;
	}

	public Map<Integer, AbsChatHandler<?>> getHandlerMap() {

		return handlerMap;
	}

	public OfflineConfig getOfflineConfig() {

		return offlineConfig;
	}

	public ClusterConfig getClusterConfig() {

		return clusterConfig;
	}

	public CopyOnWriteArrayList<IpRange> getBlackIps() {

		return blackIps;
	}

	public SSLConfig getSslConfig() {

		return sslConfig;
	}

	public Integer getPort() {

		return port;
	}

	public GlobalTrafficConfig getGlobalTrafficConfig() {

		return globalTrafficConfig;
	}

	public int getReaderIdleTime() {

		return readerIdleTime;
	}

	public GimContext getGimContext() {

		return gimContext;
	}

	public void setGimContext(GimContext gimContext) {

		this.gimContext = gimContext;
	}

	public PacketConfig getPacketConfig() {

		return packetConfig;
	}

	public GimListenerIntf getGimListener() {

		return gimListener;
	}

}
