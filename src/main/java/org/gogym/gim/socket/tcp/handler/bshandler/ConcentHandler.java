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
import org.gogym.gim.common.Const;
import org.gogym.gim.packet.ConnectReqClass.ConnectReq;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.bind.GimBind;
import org.gogym.gim.socket.tcp.cluster.ClusterRoute;
import org.gogym.gim.socket.tcp.handler.AbsChatHandler;
import org.gogym.gim.socket.tcp.message.MessageGenerate;

/**
 * 
 * 连接请求处理器 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see ConcentHandler
 * @since
 */
public class ConcentHandler extends AbsChatHandler<ConnectReq> {

	@Override
	public Class<ConnectReq> bodyClass() {
		return ConnectReq.class;
	}

	@Override
	public void handler(Message message, ConnectReq bsBody,
			ChannelHandlerContext ctx) {

		Message reMsg;

		// 发送者的ID
		String senderId = bsBody.getSenderId();
		try {
			// 绑定用户关系
			GimBind.bindUser(senderId, ctx.channel());
			// 绑定路由
			ClusterRoute.setUserRoute(senderId);

			reMsg = MessageGenerate.crateConnectResp(senderId, Const.success,
					"连接成功");

		} catch (Exception e) {
			e.printStackTrace();
			reMsg = MessageGenerate.crateConnectResp(senderId, Const.faild,
					"连接失败");
		}
		// 写响应消息到客户端
		ctx.channel().writeAndFlush(reMsg);
	}

}
