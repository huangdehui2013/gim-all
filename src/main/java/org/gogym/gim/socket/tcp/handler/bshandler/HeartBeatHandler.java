/*
 * 文件名：HeartBeatHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.handler.bshandler;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.util.Attribute;
import org.gogym.getty.util.AttributeKey;
import org.gogym.gim.common.Const;
import org.gogym.gim.packet.HeartBeatReqClass.HeartBeatReq;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.tcp.cluster.ClusterRoute;
import org.gogym.gim.socket.tcp.handler.AbsChatHandler;
import org.gogym.gim.socket.tcp.server.NettyChannelAttribute;

public class HeartBeatHandler extends AbsChatHandler<HeartBeatReq> {

	@Override
	public Class<HeartBeatReq> bodyClass() {
		return HeartBeatReq.class;

	}

	@Override
	public void handler(Message message, HeartBeatReq bsBody,
			ChannelHandlerContext ctx) {
		// 如果集群，那么心跳时刷新集群路由映射。确保路由信息是最新的。
		AttributeKey<NettyChannelAttribute> netty_channel_key = AttributeKey
				.valueOf(Const.netty_attributeKey);
		Attribute<NettyChannelAttribute> attr = ctx.channel().attr(
				netty_channel_key);
		NettyChannelAttribute nettyChannelAttribute = attr.get();

		if (nettyChannelAttribute != null) {
			String channelUserId = nettyChannelAttribute.getUserId();
			if (channelUserId != null) {
				// 绑定路由
				ClusterRoute.setUserRoute(channelUserId);
			}
		}

	}

}
