/*
 * 文件名：ClusterEmitter.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.cluster;

import java.util.function.Predicate;

import org.gogym.gim.common.Const;
import org.gogym.gim.common.Type;
import org.gogym.gim.packet.AckReqClass.AckReq;
import org.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.packet.SingleChatReqClass.SingleChatReq;
import org.gogym.gim.socket.netty.tcp.message.MessagEmitter;
import org.gogym.gim.socket.netty.tcp.message.MessageDelayPacket;
import org.gogym.gim.socket.netty.tcp.message.MessageGenerate;
import org.gogym.gim.socket.netty.tcp.server.GimConfig;
import org.gogym.gim.utils.IDGenerator;

import com.google.protobuf.util.JsonFormat;

class ClusterEmitter {

	static GimConfig gimConfig = GimConfig.shareInstance();

	/**
	 * 
	 * Description: send msg to user
	 * 
	 * @param userId
	 * @param msg
	 * @see
	 */
	public static void sendToClient(String msgJson) throws Exception {

		Message.Builder builder = Message.newBuilder();
		JsonFormat
				.parser()
				.usingTypeRegistry(
						gimConfig.getPacketConfig().getTypeRegistry())
				.merge(msgJson, builder);
		int type = builder.getReqType();

		if (type == Type.ACK_REQ) {

			AckReq ackReq = builder.getBody().unpack(AckReq.class);
			gimConfig.getGimContext().delayMsgQueue
					.removeIf(new Predicate<MessageDelayPacket>() {
						@Override
						public boolean test(MessageDelayPacket t) {
							// 如果存在，从队列中移除消息
							return t.getMessage().getId()
									.equals(ackReq.getAck());
						}
					});
		} else {

			// 返回ack给集群服务器
			ClusterConfig clusterConfig = gimConfig.getClusterConfig();
			Message ack = MessageGenerate.createAck(builder.getId());
			// 把消息放到队列里
			MessageDelayPacket ackMdp = new MessageDelayPacket(
					clusterConfig.getServerIdentify(), "", ack, Const.msg_delay);

			System.out.println("服务器：" + builder.getServerIdentify());
			// 发送到Redis
			clusterConfig.getiRedisProxy().lpush(
					"gim_" + builder.getServerIdentify(),
					ackMdp.msgToJson(gimConfig.getPacketConfig()
							.getTypeRegistry()));
			ackMdp = null;
			// 如果是普通消息
			if (type == Type.SINGLE_MSG_REQ) {

				SingleChatReq singleChatReq = builder.getBody().unpack(
						SingleChatReq.class);
				String userId = singleChatReq.getReceiverId();
				MessagEmitter.sendToUser(userId,
						builder.setId(IDGenerator.getUUID()).build());

			} else if (type == Type.GROUP_MSG_REQ) {
				GroupChatReq groupChatReq = builder.getBody().unpack(
						GroupChatReq.class);
				String groupId = groupChatReq.getGroupId();

				// TODO 注意，这里因为已经是集群处理，只需发送本机存在的连接即可
				MessagEmitter.sendToGroupOnlyServer(groupId,
						builder.setId(IDGenerator.getUUID()).build());
			}

		}

	}

}
