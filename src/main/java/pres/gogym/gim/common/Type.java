package pres.gogym.gim.common;

/**
 * 消息类型定义
 * 
 * @author tanyaowu 2017年3月26日 下午8:18:13
 */
public interface Type {

	/**
	 * 连接消息请求
	 */
	int CONNET_REQ = 1;
	/**
	 * 连接消息响应
	 */
	int CONNET_RESP = 2;

	/**
	 * 进入群组消息请求
	 */
	int JOIN_GROUP_REQ = 3;
	/**
	 * 进入群组消息响应
	 */
	int JOIN_GROUP_RESP = 4;

	/**
	 * 单聊消息请求
	 */
	int SINGLE_MSG_REQ = 5;
	/**
	 * 单聊消息响应
	 */
	int SINGLE_MSG_RESP = 6;

	/**
	 * 群聊消息请求
	 */
	int GROUP_MSG_REQ = 7;
	/**
	 * 群聊消息响应
	 */
	int GROUP_MSG_RESP = 8;
	
	/**
	 * 添加好友请求
	 */
	int ADD_FRIEND_REQ =9;
	
	/**
	 * 添加好友响应
	 */
	int ADD_FRIEND_RESP=10;
	

	// ---------------------------------------------------------------

	/**
	 * 心跳
	 */
	int HEART_BEAT_REQ = 99;

	/**
	 * ACK
	 */
	int ACK_REQ = 100;

}
