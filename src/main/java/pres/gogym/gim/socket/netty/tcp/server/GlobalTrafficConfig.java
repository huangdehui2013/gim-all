/*
 * 文件名：GlobalTrafficConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.server;

import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import pres.gogym.gim.socket.netty.tcp.handler.nettyHandler.ChannelTrafficHandler;
import pres.gogym.gim.socket.netty.tcp.handler.nettyHandler.GlobalTrafficHandler;

public class GlobalTrafficConfig {

	private boolean isMonitor = false;

	private int checkInterval = 1000;

	private EventExecutorGroup EXECUTOR_GROUOP = new DefaultEventExecutorGroup(
			2);

	private GlobalTrafficHandler globalTrafficHandler;

	private ChannelTrafficHandler channelTrafficHandler;

	public GlobalTrafficConfig(boolean isMonitor) {
		this.isMonitor = isMonitor;
	}

	public GlobalTrafficConfig isMonitor(boolean isMonitor) {
		this.isMonitor = isMonitor;
		return this;
	}

	public GlobalTrafficConfig checkInterval(int checkInterval) {
		this.checkInterval = checkInterval;
		return this;
	}

	public boolean isMonitor() {
		return isMonitor;
	}

	public int getCheckInterval() {

		return checkInterval;
	}

	public EventExecutorGroup getEXECUTOR_GROUOP() {
		return EXECUTOR_GROUOP;
	}

	public GlobalTrafficHandler getGlobalTrafficHandler() {

		if (this.globalTrafficHandler == null) {
			this.globalTrafficHandler = new GlobalTrafficHandler(this
					.getEXECUTOR_GROUOP().next(), getCheckInterval());
		}

		return globalTrafficHandler;
	}

	public ChannelTrafficHandler getChannelTrafficHandler() {

		if (this.channelTrafficHandler == null) {
			this.channelTrafficHandler = new ChannelTrafficHandler(this
					.getEXECUTOR_GROUOP().next(), getCheckInterval());
		}

		return channelTrafficHandler;
	}

}
