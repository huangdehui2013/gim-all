/*
 * 文件名：ConcentHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.handler.bshandler;

import io.netty.channel.ChannelHandlerContext;

import java.util.function.Predicate;

import pres.gogym.gim.packet.AckReqClass.AckReq;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.socket.netty.tcp.handler.AbsChatHandler;
import pres.gogym.gim.socket.netty.tcp.message.MessageDelayPacket;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

/**
 * 
 * ack处理器 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see AckHandler
 * @since
 */
public class AckHandler extends AbsChatHandler<AckReq> {

	@Override
	public Class<AckReq> bodyClass() {
		return AckReq.class;
	}

	@Override
	public void handler(Message message, AckReq bsBody,
			ChannelHandlerContext ctx) {

		System.out.println("处理ACK");
		String ack = bsBody.getAck();
		// remove ack from list
		// GimContext.shareInstance().ackList.remove(ack);

		GimConfig.shareInstance().getGimContext().delayMsgQueue
				.removeIf(new Predicate<MessageDelayPacket>() {
					@Override
					public boolean test(MessageDelayPacket t) {
						// 如果存在，从队列中移除消息
						return t.getMessage().getId().equals(ack);
					}
				});

	}
}
