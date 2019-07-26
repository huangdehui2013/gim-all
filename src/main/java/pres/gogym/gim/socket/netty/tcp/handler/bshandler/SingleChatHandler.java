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
import pres.gogym.gim.packet.AckReqClass.AckReq;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.packet.SingleChatReqClass.SingleChatReq;
import pres.gogym.gim.socket.netty.tcp.handler.AbsChatHandler;
import pres.gogym.gim.socket.netty.tcp.message.MessagEmitter;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;
import pres.gogym.gim.socket.netty.tcp.server.GimContext;

/**
 * 
 * ack处理器 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see SingleChatHandler
 * @since
 */
public class SingleChatHandler extends AbsChatHandler<SingleChatReq> {

	@Override
	public Class<SingleChatReq> bodyClass() {
		return SingleChatReq.class;
	}

	@Override
	public void handler(Message message, SingleChatReq bsBody,
			ChannelHandlerContext ctx) {

		// 接收者的ID
		String receiverId = bsBody.getReceiverId();

		try {
			MessagEmitter.sendToUser(receiverId, message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
