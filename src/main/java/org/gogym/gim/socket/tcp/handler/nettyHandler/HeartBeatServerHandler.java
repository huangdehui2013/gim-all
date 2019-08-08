package org.gogym.gim.socket.tcp.handler.nettyHandler;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.getty.channel.ChannelInboundHandlerAdapter;
import org.gogym.getty.handler.timeout.IdleState;
import org.gogym.getty.handler.timeout.IdleStateEvent;

/**
 * 心跳检测
 * 
 * @ClassName HeartBeatServerHandler
 * @Description TODO
 * @author kokjuis 189155278@qq.com
 * @date 2016-9-26
 * @content
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

	private int loss_connect_time = 0;

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state() == IdleState.READER_IDLE) {
				loss_connect_time++;
				if (loss_connect_time > 2) {
					// 超过3次检测没有心跳就关闭这个连接
					System.out.println("[关闭这个不活跃的channel:" + ctx.channel().remoteAddress()
							+ "]");
					ctx.channel().close();
				}
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}

}
