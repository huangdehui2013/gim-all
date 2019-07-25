/*
 * 文件名：ClusterRoute.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.cluster;

import java.util.List;
import java.util.Set;

import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

public class ClusterRoute {

	/**
	 * 
	 * Description: 设置用户与服务器的路由映射
	 * 
	 * @param userId
	 * @see
	 */
	public static void setUserRoute(ClusterConfig clusterConfig, String userId) {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			// 把用户连接对应所在服务器标志写到redis,便于分布式路由
			clusterConfig.getiRedisProxy().setex("channel_client_" + userId,
					30 * 60, clusterConfig.getServerIdentify());
		}
	}

	/**
	 * 
	 * Description: 获取用户与服务器的路由映射
	 * 
	 * @param userId
	 * @return
	 * @see
	 */
	public static String getUserRoute(ClusterConfig clusterConfig, String userId) {

		return clusterConfig.getiRedisProxy().get("channel_client_" + userId);
	}

	/**
	 * 
	 * Description: 删除用户路由
	 * 
	 * @param userId
	 * @see
	 */
	public static void delUserRoute(ClusterConfig clusterConfig, String userId) {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			// 把用户连接对应所在服务器标志写到redis,便于分布式路由
			clusterConfig.getiRedisProxy().del("channel_client_" + userId);
		}

	}

	/**
	 * 
	 * Description: 用户绑定群组
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 * @see
	 */
	public static void setGroupRoute(ClusterConfig clusterConfig,String groupId, String userId)
			throws Exception {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Long result = clusterConfig.getiRedisProxy().sadd(
					"channel_group_" + groupId, userId);
			if (null == result) {
				throw new Exception("setGroupRoute error");
			}
		}

	}

	/**
	 * 
	 * Description: 用户绑定群组
	 * 
	 * @param groupId
	 * @param userId
	 * @return
	 * @see
	 */
	public static void setGroupRoute(ClusterConfig clusterConfig,String groupId, List<String> userIds)
			throws Exception {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Long result = clusterConfig.getiRedisProxy().sadd(
					"channel_group_" + groupId,
					userIds.toArray(new String[userIds.size()]));
			if (null == result) {
				throw new Exception("setGroupRoute error");
			}
		}
	}

	/**
	 * 
	 * Description: 获取群组路由
	 * 
	 * @param groupId
	 * @return
	 * @see
	 */
	public static Set<String> getGroupRoute(ClusterConfig clusterConfig,String groupId) {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Set<String> result = clusterConfig.getiRedisProxy().smembers(
					"channel_group_" + groupId);
			return result;
		}
		return null;
	}

	/**
	 * 
	 * Description: 删除群路由
	 * 
	 * @param groupId
	 * @param userId
	 * @throws Exception
	 * @see
	 */
	public static void delGroupRoute(ClusterConfig clusterConfig,String groupId, String userId)
			throws Exception {


		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Long result = clusterConfig.getiRedisProxy().srem(
					"channel_group_" + groupId, userId);
			if (null == result) {
				throw new Exception("setGroupRoute error");
			}
		}

	}

	public static void delGroupRoute(ClusterConfig clusterConfig,String groupId, List<String> userIds)
			throws Exception {

		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Long result = clusterConfig.getiRedisProxy().srem(
					"channel_group_" + groupId,
					userIds.toArray(new String[userIds.size()]));
			if (null == result) {
				throw new Exception("setGroupRoute error");
			}
		}
	}

	/**
	 * 
	 * Description: 清空集合
	 * 
	 * @param groupId
	 * @throws Exception
	 * @see
	 */
	public static void clearGroupRoute(ClusterConfig clusterConfig,String groupId) throws Exception {


		// 先判断是否开启集群
		if (clusterConfig != null && clusterConfig.isCluster()) {
			Long result = clusterConfig.getiRedisProxy().batchSrem(
					"channel_group_" + groupId);
			if (null == result) {
				throw new Exception("setGroupRoute error");
			}
		}
	}

}
