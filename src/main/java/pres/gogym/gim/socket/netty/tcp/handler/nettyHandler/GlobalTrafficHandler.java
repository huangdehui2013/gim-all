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

package pres.gogym.gim.socket.netty.tcp.handler.nettyHandler;

import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import io.netty.util.concurrent.EventExecutor;

public class GlobalTrafficHandler extends GlobalTrafficShapingHandler {

	public GlobalTrafficHandler(EventExecutor executor, int checkInterval) {
		super(executor, checkInterval);
	}

}
