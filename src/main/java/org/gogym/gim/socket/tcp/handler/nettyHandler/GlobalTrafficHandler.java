/*
 * 文件名：NettyTrafficShapingHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.handler.nettyHandler;

import org.gogym.getty.handler.traffic.GlobalTrafficShapingHandler;
import org.gogym.getty.util.concurrent.EventExecutor;

public class GlobalTrafficHandler extends GlobalTrafficShapingHandler {

	public GlobalTrafficHandler(EventExecutor executor, int checkInterval) {
		super(executor, checkInterval);
	}

}
