/*
 * 文件名：BaseHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.handler;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.gim.common.Type;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.listener.ChatMsgListener;
import org.gogym.gim.socket.tcp.message.MessageGenerate;
import org.gogym.gim.socket.tcp.server.GimConfig;

/**
 * 
 * 〈业务处理器基类〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see BaseHandler
 * @since
 */
public class BaseHandler implements ChatMsgListener {

	@Override
	public void read(GimConfig gimConfig, Message message,
			ChannelHandlerContext ctx) throws Exception {

		// 这里判断是否自动返回ack给客户端
		if (gimConfig.isAutoAck() && message.getReqType() != Type.ACK_REQ
				&& message.getReqType() != Type.HEART_BEAT_REQ) {
			Message ack = MessageGenerate.createAck(message.getId());
			// 把ack消息写给客户端
			ctx.channel().writeAndFlush(ack);
		}

		Integer type = message.getReqType();
		AbsChatHandler<?> absChatHandler = gimConfig.getHandlerMap().get(type);

		if (absChatHandler == null) {
			throw new Exception("找不到处理类");
		}

		if (gimConfig.getGimListener() != null) {
			gimConfig.getGimListener().messageRead(
					ctx.channel().remoteAddress().toString(), message);
		}

		absChatHandler.handler(message, ctx);
	}
}
