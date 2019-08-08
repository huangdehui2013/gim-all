/*
 * 文件名：GlobalTrafficMonitor.java
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.server;

import java.util.Collection;

import org.gogym.getty.handler.traffic.TrafficCounter;

public class GlobalTrafficMonitor {

	static GimConfig gimConfig = GimConfig.shareInstance();

	/**
	 * 
	 * Description: 获取读（吞吐量）
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 */
	public static long currentReadThroughput() throws Exception {

		GlobalTrafficConfig globalTrafficConfig = gimConfig
				.getGlobalTrafficConfig();

		if (globalTrafficConfig == null) {
			throw new Exception("globalTrafficConfig not set");
		}

		return globalTrafficConfig.getGlobalTrafficHandler().trafficCounter()
				.cumulativeReadBytes();

	}

	/**
	 * 
	 * Description: 获取写（吞吐量）
	 * 
	 * @return
	 * @throws Exception
	 * @see
	 */
	public static long currentWriteThroughput() throws Exception {

		GlobalTrafficConfig globalTrafficConfig = gimConfig
				.getGlobalTrafficConfig();

		if (globalTrafficConfig == null) {
			throw new Exception("globalTrafficConfig not set");
		}

		return globalTrafficConfig.getGlobalTrafficHandler().trafficCounter()
				.cumulativeWrittenBytes();

	}

	public static Collection<TrafficCounter> channelTrafficCounters()
			throws Exception {

		GlobalTrafficConfig globalTrafficConfig = gimConfig
				.getGlobalTrafficConfig();

		if (globalTrafficConfig == null) {
			throw new Exception("globalTrafficConfig not set");
		}

		return globalTrafficConfig.getChannelTrafficHandler()
				.channelTrafficCounters();
	}

	public static int getChannelsSize() throws Exception {

		if (gimConfig.getGimContext() == null) {
			throw new Exception("getGimContext not set");
		}

		return gimConfig.getGimContext().channels.size();

	}
}
