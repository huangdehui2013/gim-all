/*
 * 文件名：SslCheckServerHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.ssl;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.ByteBuffer;

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
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		System.out.println("服务端增加");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) {
		System.out.println("移除:" + ctx.channel().remoteAddress());
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
