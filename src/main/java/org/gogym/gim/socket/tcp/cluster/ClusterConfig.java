/*
 * 文件名：ClusterConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.cluster;


import org.gogym.gim.socket.tcp.intf.ClusterMsgHandlerIntf;
import org.gogym.gim.socket.tcp.redis.IRedisProxy;
import org.gogym.gim.socket.tcp.redis.RedisConfig;
import org.gogym.gim.socket.tcp.redis.RedisProxyImp;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ClusterConfig {

	public static int REDIS = 1;
	public static int HANDLER = 2;

	private String serverIdentify;

	private boolean isCluster = false;

	private int handleType = REDIS;

	private ClusterMsgHandlerIntf clusterMsgHandler;

	private IRedisProxy iRedisProxy;

	public ClusterConfig() {
	}

	public ClusterConfig(String serverIdentify, RedisConfig redisConfig) {

		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
		jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
		jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
		jedisPoolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
		jedisPoolConfig.setTestWhileIdle(redisConfig.getTestWhileIdle());

		JedisPool jedisPool = new JedisPool(jedisPoolConfig,
				redisConfig.getHost(), redisConfig.getPort(),
				redisConfig.getConnectionTimeout(), redisConfig.getPassword());

		iRedisProxy = new RedisProxyImp(jedisPool);

		this.serverIdentify = serverIdentify;
	}

	public ClusterConfig redisConfig(RedisConfig redisConfig) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
		jedisPoolConfig.setMaxIdle(redisConfig.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());
		jedisPoolConfig.setTestOnBorrow(redisConfig.getTestOnBorrow());
		jedisPoolConfig.setTestOnReturn(redisConfig.getTestOnReturn());
		jedisPoolConfig.setTestWhileIdle(redisConfig.getTestWhileIdle());

		JedisPool jedisPool = new JedisPool(jedisPoolConfig,
				redisConfig.getHost(), redisConfig.getPort(),
				redisConfig.getConnectionTimeout(), redisConfig.getPassword());

		iRedisProxy = new RedisProxyImp(jedisPool);
		return this;
	}

	public ClusterConfig serverIdentify(String serverIdentify) {
		this.serverIdentify = serverIdentify;
		return this;
	}

	public ClusterConfig isCluster(boolean isCluster) {
		this.isCluster = isCluster;
		return this;
	}

	public ClusterConfig handleType(int handleType) {
		this.handleType = handleType;
		return this;
	}

	public ClusterConfig clusterMsgHandler(
			ClusterMsgHandlerIntf clusterMsgHandler) {
		this.clusterMsgHandler = clusterMsgHandler;
		return this;
	}


	public boolean isCluster() {
		return isCluster;
	}

	public int getHandleType() {

		return handleType;
	}

	public ClusterMsgHandlerIntf getClusterMsgHandler() {

		return clusterMsgHandler;
	}

	public IRedisProxy getiRedisProxy() {

		return iRedisProxy;
	}

	public String getServerIdentify() {

		return serverIdentify;
	}

}
