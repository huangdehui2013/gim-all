/*
 * 文件名：SslCheckServerHandler.java
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.ssl;

import java.nio.ByteBuffer;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.channel.SimpleChannelInboundHandler;
import org.gogym.getty.handler.ssl.SslHandler;
import org.gogym.getty.util.concurrent.Future;
import org.gogym.getty.util.concurrent.GenericFutureListener;

public class SslCheckServerHandler extends
		SimpleChannelInboundHandler<ByteBuffer> {

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
		// Once session is secured, send a greeting and register the channel to
		// the global channel
		// list so the channel received the messages from others.
		ctx.pipeline().get(SslHandler.class).handshakeFuture()
				.addListener(new GenericFutureListener<Future<Channel>>() {
					@Override
					public void operationComplete(Future<Channel> future)
							throws Exception {
						if (future.isSuccess()) {
							System.out.println("握手成功");
						} else {
							System.out.println("握手失败");
						}
					}
				});
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuffer msg)
			throws Exception {
	}

}
