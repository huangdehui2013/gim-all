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

package org.gogym.gim.socket.tcp.handler.bshandler;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.handler.AbsChatHandler;
import org.gogym.gim.socket.tcp.message.MessagEmitter;

/**
 * 
 * ack处理器 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see GroupChatHandler
 * @since
 */
public class GroupChatHandler extends AbsChatHandler<GroupChatReq> {

	@Override
	public Class<GroupChatReq> bodyClass() {
		return GroupChatReq.class;
	}

	@Override
	public void handler(Message message, GroupChatReq bsBody,
			ChannelHandlerContext ctx) {
		// 接收者的ID
		String groupId = bsBody.getGroupId();
		try {
			MessagEmitter.sendToGroup(groupId, message);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
