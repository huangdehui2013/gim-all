/*
 * 文件名：DelayMsgQueueListener.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.listener.queue;

import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.socket.netty.tcp.message.MessagEmitter;
import pres.gogym.gim.socket.netty.tcp.message.MessageDelayPacket;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

public class DelayMsgQueueListener {

	public static void takeMessage(GimConfig gimConfig) {

		try {
			// 从代发队列中拿出消息
			MessageDelayPacket element = gimConfig.getGimContext().delayMsgQueue
					.take();
			Message msg = element.getMessage();
			MessagEmitter.sendToUser(element.getUserId(), msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
