/*
 * 文件名：GlobalTrafficMonitor.java
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月26日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.server;

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

		return globalTrafficConfig.getNettyTrafficShapingHandler()
				.trafficCounter().getRealWriteThroughput();

	}

}
