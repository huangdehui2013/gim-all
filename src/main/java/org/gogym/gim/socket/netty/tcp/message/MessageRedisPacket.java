/*
 * 文件名：MessageRedisPacket.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.message;

import org.gogym.gim.packet.MessageClass.Message;

public class MessageRedisPacket {

	private String serverIdentify;
	private Message message;

	public MessageRedisPacket(String serverIdentify, Message message) {
		this.serverIdentify = serverIdentify;
		this.message = message;
	}

	public String getServerIdentify() {

		return serverIdentify;
	}

	public Message getMessage() {

		return message;
	}

}
