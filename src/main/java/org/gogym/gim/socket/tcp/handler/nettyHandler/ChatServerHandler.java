package org.gogym.gim.socket.tcp.handler.nettyHandler;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.channel.SimpleChannelInboundHandler;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.bind.GimBind;
import org.gogym.gim.socket.tcp.handler.BaseHandler;
import org.gogym.gim.socket.tcp.listener.ChatMsgListener;
import org.gogym.gim.socket.tcp.server.GimConfig;

/**
 * netty处理类
 * 
 * @author kokJuis
 * @version 1.0
 * @date 2016-9-30
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<Message> {

	// 把消息传给监听
	ChatMsgListener chatMsgListener = new BaseHandler();
	GimConfig gimConfig;

	public ChatServerHandler(GimConfig gimConfig) {
		this.gimConfig = gimConfig;
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		gimConfig.getGimContext().channels.add(incoming);

		if (gimConfig.getGimListener() != null) {
			gimConfig.getGimListener().channelAdd(
					incoming.remoteAddress().toString());
		}
		System.out.println("[Client] - " + incoming.remoteAddress() + " 连接过来");
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		GimBind.unbindUser(incoming);

		if (gimConfig.getGimListener() != null) {
			gimConfig.getGimListener().channelClose(
					incoming.remoteAddress().toString());
		}
		System.out.println("[Client] - " + incoming.remoteAddress() + " 离开");
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg)
			throws Exception {
		// 消息会在这个方法接收到，msg就是经过解码器解码后得到的消息，框架自动帮你做好了粘包拆包和解码的工作
		chatMsgListener.read(gimConfig, msg, ctx);

	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Channel incoming = ctx.channel();
		// 当出现异常就关闭连接
		System.out.println("Client:" + incoming.remoteAddress() + "异常,已被服务器关闭");
		cause.printStackTrace();
	}

}
