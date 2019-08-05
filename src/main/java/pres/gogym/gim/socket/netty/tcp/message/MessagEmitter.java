/*
 * 文件名：MessagEmitter.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.message;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;

import org.gogym.getty.channel.Channel;
import org.gogym.getty.channel.ChannelId;

import com.google.protobuf.util.JsonFormat;

import pres.gogym.gim.common.Const;
import pres.gogym.gim.common.Type;
import pres.gogym.gim.packet.AckReqClass.AckReq;
import pres.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.packet.SingleChatReqClass.SingleChatReq;
import pres.gogym.gim.socket.netty.tcp.cluster.ClusterConfig;
import pres.gogym.gim.socket.netty.tcp.cluster.ClusterRoute;
import pres.gogym.gim.socket.netty.tcp.offline.OfflineEmitter;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;
import pres.gogym.gim.utils.IDGenerator;

public class MessagEmitter {

	static GimConfig gimConfig = GimConfig.shareInstance();

	/**
	 * 
	 * Description: send msg to user
	 * 
	 * @param userId
	 * @param msg
	 * @see
	 */
	public static void sendToUser(String userId, Message msg) throws Exception {

		ChannelId channelId = gimConfig.getGimContext().userChannelMap
				.get(userId);

		if (channelId != null) {
			Channel channel = gimConfig.getGimContext().channels
					.find(channelId);
			if (channel == null) {
				throw new Exception("[channel is null error]");
			}

			// save ack to list
			// GimContext.shareInstance().ackList.add(msg.getId());
			MessageDelayPacket mdp = new MessageDelayPacket(userId, msg,
					Const.msg_delay);
			gimConfig.getGimContext().delayMsgQueue.put(mdp);

			channel.writeAndFlush(msg);
		} else if (gimConfig.getClusterConfig().isCluster()) {
			// 集群发送
			sendToUserCluster(userId, msg);
		} else {
			// 离线
			OfflineEmitter.putOfflineMsg(gimConfig.getOfflineConfig(), msg);
		}

		if (gimConfig.getGimListener() != null) {
			gimConfig.getGimListener().messageWrite(true, msg);
		}

	}

	/**
	 * 
	 * Description: send msg to group
	 * 
	 * @param groupId
	 * @param msg
	 * @throws Exception
	 * @see
	 */
	public static void sendToGroup(String groupId, Message msg)
			throws Exception {

		if (gimConfig.getClusterConfig().isCluster()) {

			Set<String> set = ClusterRoute.getGroupRoute(groupId);
			for (String string : set) {
				sendToUser(string, msg.toBuilder().setId(IDGenerator.getUUID())
						.build());
			}

		} else {
			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;
			CopyOnWriteArrayList<String> list = groupUserMap.get(groupId);

			if (groupUserMap.get(groupId) != null) {
				for (String string : list) {
					sendToUser(string,
							msg.toBuilder().setId(IDGenerator.getUUID())
									.build());
				}
			}
		}

		if (gimConfig.getGimListener() != null) {
			gimConfig.getGimListener().messageWrite(true, msg);
		}
	}

	/**
	 * 
	 * Description:
	 * 
	 * @param gimConfig
	 * @param groupId
	 * @param msg
	 * @see
	 */
	public static void sendToGroupOnlyServer(String groupId, Message msg)
			throws Exception {

		if (gimConfig.getClusterConfig().isCluster()) {

			Set<String> set = ClusterRoute.getGroupRoute(groupId);
			for (String string : set) {

				ChannelId channelId = gimConfig.getGimContext().userChannelMap
						.get(string);
				if (channelId != null) {
					Channel channel = gimConfig.getGimContext().channels
							.find(channelId);
					if (channel == null) {
						throw new Exception("[channel is null error]");
					}

					MessageDelayPacket mdp = new MessageDelayPacket(string,
							msg, Const.msg_delay);
					gimConfig.getGimContext().delayMsgQueue.put(mdp);
					channel.writeAndFlush(msg);
				}
			}

		} else {
			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;
			CopyOnWriteArrayList<String> list = groupUserMap.get(groupId);

			if (groupUserMap.get(groupId) != null) {
				for (String string : list) {
					ChannelId channelId = gimConfig.getGimContext().userChannelMap
							.get(string);
					if (channelId != null) {
						Channel channel = gimConfig.getGimContext().channels
								.find(channelId);
						if (channel == null) {
							throw new Exception("[channel is null error]");
						}

						MessageDelayPacket mdp = new MessageDelayPacket(string,
								msg, Const.msg_delay);
						gimConfig.getGimContext().delayMsgQueue.put(mdp);
						channel.writeAndFlush(msg);
					}
				}
			}
		}

	}

	// -----------------------------------------------------------

	/**
	 * 
	 * Description: send msg to user
	 * 
	 * @param userId
	 * @param msg
	 * @see
	 */
	private static void sendToUserCluster(String userId, Message msg)
			throws Exception {

		ClusterConfig clusterConfig = gimConfig.getClusterConfig();

		if (clusterConfig != null && clusterConfig.isCluster()) {

			if (clusterConfig.getHandleType() == ClusterConfig.HANDLER) {
				clusterConfig.getClusterMsgHandler().clusterMsg(msg);
			} else if (clusterConfig.getHandleType() == ClusterConfig.REDIS) {
				// 集群下查找路由信息
				String serverIdentify = ClusterRoute.getUserRoute(userId);
				if (null == serverIdentify) {
					// 没有找到路由信息，则离线
					OfflineEmitter.putOfflineMsg(gimConfig.getOfflineConfig(),
							msg);
				} else {

					// 把消息放到队列里
					MessageDelayPacket mdp = new MessageDelayPacket(
							clusterConfig.getServerIdentify(), userId, msg,
							Const.msg_delay);
					gimConfig.getGimContext().delayMsgQueue.add(mdp);

					// 发送到Redis
					Long size = clusterConfig.getiRedisProxy().lpush(
							"gim_" + serverIdentify,
							mdp.msgToJson(gimConfig.getPacketConfig()
									.getTypeRegistry()));

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
