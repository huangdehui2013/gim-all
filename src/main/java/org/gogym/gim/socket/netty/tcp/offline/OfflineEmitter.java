/*
 * 文件名：OfflineEmitter.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.offline;

import org.gogym.gim.packet.MessageClass.Message;

public abstract class OfflineEmitter {

	/**
	 * 
	 * Description: 离线消息
	 * 
	 * @param msg
	 * @see
	 */
	public static void putOfflineMsg(OfflineConfig offlineConfig,Message msg) {

		if (offlineConfig != null && offlineConfig.isOffline()) {

			if (offlineConfig.getHandleType() == OfflineConfig.QUEUE) {
				try {
					offlineConfig.getOfflineMsgQueue().put(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (offlineConfig.getHandleType() == OfflineConfig.HANDLER) {
				offlineConfig.getOfflineMsgHandler().offlineMsg(msg);
			}

		}

	}

}
