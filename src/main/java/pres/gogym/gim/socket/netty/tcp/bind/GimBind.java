/*
 * 文件名：ChatBind.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.bind;

import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

import pres.gogym.gim.common.Const;
import pres.gogym.gim.socket.netty.tcp.cluster.ClusterRoute;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;
import pres.gogym.gim.socket.netty.tcp.server.NettyChannelAttribute;

public class GimBind {

	static GimConfig gimConfig = GimConfig.shareInstance();

	/**
	 * 
	 * Description: 用户与连接绑定
	 * 
	 * @param userId
	 * @param channelId
	 * @see
	 */
	public static void bindUser(String userId, Channel channel) {
		gimConfig.getGimContext().userChannelMap.put(userId, channel.id());
		AttributeKey<NettyChannelAttribute> netty_channel_key = AttributeKey
				.valueOf(Const.netty_attributeKey);

		Attribute<NettyChannelAttribute> attr = channel.attr(netty_channel_key);
		NettyChannelAttribute nettyChannelAttribute = new NettyChannelAttribute(
				userId);
		attr.set(nettyChannelAttribute);

		// 集群模式下，需要向redis中注册用户服务器地址
		ClusterRoute.setUserRoute(userId);

	}

	/**
	 * 
	 * Description: 把用户绑定到群
	 * 
	 * @param gourpId
	 * @param userId
	 * @see
	 */
	public static void bindGroup(String groupId, String userId) {

		ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
				.getGimContext().groupUserMap;

		if (gimConfig.getClusterConfig().isCluster()) {

			try {
				ClusterRoute.setGroupRoute(groupId, userId);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			// 添加到群组列表中
			if (groupUserMap.get(groupId) == null) {
				CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
				list.add(userId);
				groupUserMap.put(groupId, list);
			} else {
				groupUserMap.get(groupId).add(userId);
			}
		}

	}

	public static void bindGroup(String groupId, List<String> users) {

		if (gimConfig.getClusterConfig().isCluster()) {

			try {
				ClusterRoute.setGroupRoute(groupId, users);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;
			// 添加到群组列表中
			if (groupUserMap.get(groupId) == null) {
				CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(
						users);
				groupUserMap.put(groupId, list);
			} else {
				groupUserMap.get(groupId).addAll(users);
			}
		}

	}

	/**
	 * 
	 * Description: 解绑用户
	 * 
	 * @param userId
	 * @param channel
	 * @see
	 */
	public static void unbindUser(String userId) {
		gimConfig.getGimContext().userChannelMap.remove(userId);
		ClusterRoute.delUserRoute(userId);
	}

	public static void unbindUser(Channel channel) {

		AttributeKey<NettyChannelAttribute> netty_channel_key = AttributeKey
				.valueOf(Const.netty_attributeKey);
		Attribute<NettyChannelAttribute> attr = channel.attr(netty_channel_key);

		NettyChannelAttribute nettyChannelAttribute = attr.get();

		if (nettyChannelAttribute != null) {

			String channelUserId = nettyChannelAttribute.getUserId();
			if (channelUserId != null) {
				gimConfig.getGimContext().userChannelMap.remove(channelUserId);
				ClusterRoute.delUserRoute(channelUserId);
			}
		}
		gimConfig.getGimContext().channels.remove(channel);

		if (channel.isActive()) {
			channel.close();
		}
	}

	public static void unbindGroup(String groupId, String userId) {

		if (gimConfig.getClusterConfig().isCluster()) {

			try {
				ClusterRoute.delGroupRoute(groupId, userId);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;

			CopyOnWriteArrayList<String> list = groupUserMap.get(groupId);
			// 添加到群组列表中
			if (list != null) {
				list.remove(userId);
			}
		}

	}

	public static void unbindGroup(String groupId, List<String> userIds) {

		if (gimConfig.getClusterConfig().isCluster()) {

			try {
				ClusterRoute.delGroupRoute(groupId, userIds);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;

			CopyOnWriteArrayList<String> list = groupUserMap.get(groupId);
			// 添加到群组列表中
			if (list != null) {
				list.removeAll(userIds);
			}
		}

	}

	/**
	 * 
	 * Description: 清理群组
	 * 
	 * @param groupId
	 * @see
	 */
	public static void cleanGroup(String groupId) {

		if (gimConfig.getClusterConfig().isCluster()) {

			try {
				ClusterRoute.clearGroupRoute(groupId);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = gimConfig
					.getGimContext().groupUserMap;
			// 添加到群组列表中
			if (groupUserMap.get(groupId) != null) {
				groupUserMap.remove(groupId);
			}
		}

	}

}
