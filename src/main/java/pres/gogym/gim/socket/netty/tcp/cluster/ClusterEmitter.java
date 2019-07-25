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

package pres.gogym.gim.socket.netty.tcp.cluster;

import java.util.function.Predicate;

import com.google.protobuf.util.JsonFormat;

import pres.gogym.gim.common.Const;
import pres.gogym.gim.common.Type;
import pres.gogym.gim.packet.AckReqClass.AckReq;
import pres.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.packet.SingleChatReqClass.SingleChatReq;
import pres.gogym.gim.socket.netty.tcp.message.MessagEmitter;
import pres.gogym.gim.socket.netty.tcp.message.MessageDelayPacket;
import pres.gogym.gim.socket.netty.tcp.message.MessageGenerate;
import pres.gogym.gim.socket.netty.tcp.message.PacketConfig;
import pres.gogym.gim.socket.netty.tcp.offline.OfflineEmitter;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

public class ClusterEmitter {

	/**
	 * 
	 * Description: send msg to user
	 * 
	 * @param userId
	 * @param msg
	 * @see
	 */
	public static void sendToClient(GimConfig gimConfig, String msgJson)
			throws Exception {

		Message.Builder builder = Message.newBuilder();
		JsonFormat
				.parser()
				.usingTypeRegistry(
						PacketConfig.shareInstance().getTypeRegistry())
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
			
			return;
		}

		// 如果是普通消息
		if (type == Type.SINGLE_MSG_REQ) {

			SingleChatReq singleChatReq = builder.getBody().unpack(
					SingleChatReq.class);
			String userId = singleChatReq.getReceiverId();
			MessagEmitter.sendToUser(gimConfig, userId, builder.build());

		} else if (type == Type.GROUP_MSG_REQ) {
			GroupChatReq groupChatReq = builder.getBody().unpack(
					GroupChatReq.class);
			String groupId = groupChatReq.getGroupId();
			MessagEmitter.sendToGroup(gimConfig, groupId, builder.build());
		}

		// 返回ack给集群服务器
		ClusterConfig clusterConfig = gimConfig.getClusterConfig();
		Message ack = MessageGenerate.createAck(builder.getId());
		// 把消息放到队列里
		MessageDelayPacket ackMdp = new MessageDelayPacket(
				clusterConfig.getServerIdentify(), "", ack, Const.msg_delay);
		// 发送到Redis
		clusterConfig.getiRedisProxy().lpush(
				"gim_" + builder.getServerIdentify(), ackMdp.msgToJson());

	}

	/**
	 * 
	 * Description: send msg to user
	 * 
	 * @param userId
	 * @param msg
	 * @see
	 */
	public static void sendToUser(GimConfig gimConfig, String userId,
			Message msg) throws Exception {

		ClusterConfig clusterConfig = gimConfig.getClusterConfig();

		if (clusterConfig != null && clusterConfig.isCluster()) {

			if (clusterConfig.getHandleType() == ClusterConfig.HANDLER) {
				clusterConfig.getClusterMsgHandler().clusterMsg(msg);
			} else if (clusterConfig.getHandleType() == ClusterConfig.REDIS) {
				// 集群下查找路由信息
				String serverIdentify = ClusterRoute.getUserRoute(
						gimConfig.getClusterConfig(), userId);
				if (null == serverIdentify) {
					// 没有找到路由信息，则离线
					OfflineEmitter.putOfflineMsg(gimConfig.getOfflineConfig(),
							msg);
				} else {

					// 把消息放到队列里
					MessageDelayPacket mdp = new MessageDelayPacket(
							clusterConfig.getServerIdentify(), userId, msg, Const.msg_delay);
					gimConfig.getGimContext().delayMsgQueue.add(mdp);

					// 把消息转成json字符串
					// String msgJson = FastJsonUtils.toJSONNoFeatures(mdp);
					// 发送到Redis
					Long size = clusterConfig.getiRedisProxy().lpush(
							"gim_" + serverIdentify, mdp.msgToJson());

					if (null == size) {
						// 一般不会到这里，到这里说明发送到MQ失败
						OfflineEmitter.putOfflineMsg(
								gimConfig.getOfflineConfig(), msg);
					}
				}
			}

		}
	}
}
