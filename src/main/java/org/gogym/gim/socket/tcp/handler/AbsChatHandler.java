package org.gogym.gim.socket.tcp.handler;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.intf.ChatHandlerIntf;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * 
 * 〈消息分发基类〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see AbsChatHandler
 * @since
 */
public abstract class AbsChatHandler<T extends GeneratedMessageV3> implements
		ChatHandlerIntf {

	public AbsChatHandler() {
	}

	// 获取泛型类，从子类中返回，这个很重要
	public abstract Class<T> bodyClass();

	@Override
	public void handler(Message message, ChannelHandlerContext ctx) {

		T bsBody = null;
		try {
			// 根据类型转换消息
			bsBody = message.getBody().unpack(bodyClass());
		} catch (InvalidProtocolBufferException e) {
			e.printStackTrace();
		}
		handler(message, bsBody, ctx);
	}

	// 把消息分发给指定的业务处理器
	public abstract void handler(Message message, T bsBody,
			ChannelHandlerContext ctx);

}
