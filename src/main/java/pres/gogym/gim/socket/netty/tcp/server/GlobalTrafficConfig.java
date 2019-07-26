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
import pres.gogym.gim.socket.netty.tcp.handler.nettyHandler.NettyTrafficShapingHandler;

public class GlobalTrafficConfig {

	private boolean isMonitor = false;

	private int checkInterval = 10000;

	private EventExecutorGroup EXECUTOR_GROUOP = new DefaultEventExecutorGroup(
			2);

	private NettyTrafficShapingHandler nettyTrafficShapingHandler;

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

	public NettyTrafficShapingHandler getNettyTrafficShapingHandler() {

		if (this.nettyTrafficShapingHandler == null) {
			this.nettyTrafficShapingHandler = new NettyTrafficShapingHandler(
					this.getEXECUTOR_GROUOP().next(), getCheckInterval());
		}

		return nettyTrafficShapingHandler;
	}
}
