/*
 * 文件名：PacketConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.message;

import java.util.ArrayList;
import java.util.List;

import org.gogym.gim.packet.AckReqClass.AckReq;
import org.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import org.gogym.gim.packet.SingleChatReqClass.SingleChatReq;

import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.TypeRegistry;

public class PacketConfig {

	private List<Descriptor> list = new ArrayList<Descriptor>();

	// private static PacketConfig packetConfig = new PacketConfig();
	//
	// public static PacketConfig shareInstance() {
	// return packetConfig;
	// }

	public PacketConfig() {
		this.list.add(AckReq.getDescriptor());
		this.list.add(SingleChatReq.getDescriptor());
		this.list.add(GroupChatReq.getDescriptor());
	}

	public PacketConfig addType(Descriptor messageType) {

		if (messageType == null) {
			throw new IllegalStateException(
					"A TypeRegistry.Builer can only be used once.");
		}

		this.list.add(messageType);
		return this;
	}

	public PacketConfig addTypes(List<Descriptor> messageTypes) {

		if (messageTypes == null) {
			throw new IllegalStateException(
					"A TypeRegistry.Builer can only be used once.");
		}
		this.list.addAll(messageTypes);
		return this;
	}

	public TypeRegistry getTypeRegistry() {

		JsonFormat.TypeRegistry.Builder builer = JsonFormat.TypeRegistry
				.newBuilder().add(list);
		return builer.build();
	}

}
