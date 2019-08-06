/*
 * 文件名：ConnectManageHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.handler.nettyHandler;

import org.gogym.getty.channel.ChannelDuplexHandler;
import org.gogym.getty.channel.ChannelHandlerContext;

/**
 * 
 * 〈监听水位线变化，关闭自动读〉
 * 
 * @author gogym
 * @version 2019年7月12日
 * @see ConnectManageHandler
 * @since
 */
public class ConnectManageHandler extends ChannelDuplexHandler {

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx)
			throws Exception {

		if (ctx.channel().isWritable()) {
			ctx.channel().config().setAutoRead(true);
		}
		super.channelWritabilityChanged(ctx);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

		if (!ctx.channel().isWritable()) {
			ctx.channel().config().setAutoRead(false);
		}
		super.channelReadComplete(ctx);
	}
}
