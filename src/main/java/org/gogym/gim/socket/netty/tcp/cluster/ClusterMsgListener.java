/*
 * 文件名：RedisMsgListener.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月22日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.cluster;

import java.util.List;

import org.gogym.gim.socket.netty.tcp.server.GimConfig;

public class ClusterMsgListener extends Thread {

	private String key;
	private GimConfig gimConfig;

	public ClusterMsgListener(GimConfig gimConfig) {
		this.key = gimConfig.getClusterConfig().getServerIdentify();
		this.gimConfig = gimConfig;
	}

	private void processMessage() throws Exception {

		List<String> msgJson = gimConfig.getClusterConfig().getiRedisProxy().brpop(0,
				"gim_" + key);
		if (msgJson != null && msgJson.size() != 0) {

			// 由于该指令可以监听多个Key,所以返回的是一个列表
			// 列表由2项组成，1) 列表名，2)数据
			String keyName = msgJson.get(0);
			// 如果返回的是MESSAGE_KEY的消息
			if (keyName.equals("gim_" + key)) {
				String message = msgJson.get(1);
				handle(message);
			}

		}
	}

	public void handle(String json) {
		try {
			ClusterEmitter.sendToClient(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				processMessage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
