/*
 * 文件名：ChannelTrafficHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.handler.nettyHandler;

import java.util.concurrent.ScheduledExecutorService;

import org.gogym.getty.handler.traffic.GlobalChannelTrafficShapingHandler;

public class ChannelTrafficHandler extends GlobalChannelTrafficShapingHandler {

	public ChannelTrafficHandler(ScheduledExecutorService executor,
			long checkInterval) {
		super(executor, checkInterval);
	}

}
