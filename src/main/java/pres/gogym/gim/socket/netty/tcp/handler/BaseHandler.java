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

package pres.gogym.gim.socket.netty.tcp.handler;

import io.netty.channel.ChannelHandlerContext;
import pres.gogym.gim.common.Type;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.socket.netty.tcp.listener.ChatMsgListener;
import pres.gogym.gim.socket.netty.tcp.message.MessageGenerate;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

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
			ChannelHandlerContext ctx) {

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
			System.out.println("找不到处理类");
			return;
		}
		absChatHandler.handler(message, ctx);
	}
}