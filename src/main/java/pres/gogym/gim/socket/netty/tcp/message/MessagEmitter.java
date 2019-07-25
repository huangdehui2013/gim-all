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

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pres.gogym.gim.common.Const;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.socket.netty.tcp.cluster.ClusterEmitter;
import pres.gogym.gim.socket.netty.tcp.cluster.ClusterRoute;
import pres.gogym.gim.socket.netty.tcp.offline.OfflineEmitter;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

public class MessagEmitter {

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
			ClusterEmitter.sendToUser(gimConfig,userId, msg);
		} else {
			// 离线
			OfflineEmitter.putOfflineMsg(gimConfig.getOfflineConfig(), msg);
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
	public static void sendToGroup(GimConfig gimConfig, String groupId,
			Message msg) throws Exception {

		if (gimConfig.getClusterConfig().isCluster()) {

			Set<String> set = ClusterRoute.getGroupRoute(gimConfig.getClusterConfig(),groupId);
			for (String string : set) {
				sendToUser(gimConfig, string, msg);
			}

		} else {
			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap =gimConfig.getGimContext().groupUserMap;
			CopyOnWriteArrayList<String> list = groupUserMap.get(groupId);

			if (groupUserMap.get(groupId) != null) {
				for (String string : list) {
					sendToUser(gimConfig, string, msg);
				}
			}

		}

	}
}
