/*
 * 文件名：MessageGenerate.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.message;

import java.util.Date;
import java.util.List;

import org.gogym.gim.common.Const;
import org.gogym.gim.common.Type;
import org.gogym.gim.packet.AckReqClass.AckReq;
import org.gogym.gim.packet.ConnectRespClass.ConnectResp;
import org.gogym.gim.packet.GroupChatReqClass.GroupChatReq;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.packet.SingleChatReqClass.SingleChatReq;
import org.gogym.gim.utils.IDGenerator;

import com.google.protobuf.Any;

/**
 * 
 * 消息构造类 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月12日
 * @see MessageGenerate
 * @since
 */
public class MessageGenerate {

	/**
	 * 
	 * Description: 创建单聊消息
	 * 
	 * @param sendlerId
	 * @param receiverId
	 * @param msgType
	 * @param body
	 * @return
	 * @see
	 */
	public static Message createSingleChatReq(String sendlerId,
			String receiverId, int msgType, String body) {

		// 创建一个ack消息
		Message.Builder builder = Message.newBuilder();
		builder.setIdentify(Const.identify);
		builder.setVersion(Const.version);
		builder.setReqType(Type.SINGLE_MSG_REQ);
		builder.setMsgTime(new Date().getTime());
		builder.setId(IDGenerator.getUUID());

		SingleChatReq.Builder singleChatReq = SingleChatReq.newBuilder();
		singleChatReq.setSenderId(sendlerId);
		singleChatReq.setReceiverId(receiverId);
		singleChatReq.setMsgType(msgType);
		singleChatReq.setBody(body);

		builder.setBody(Any.pack(singleChatReq.build()));
		return builder.build();

	}

	/**
	 * 
	 * Description: 创建一个团消息
	 * 
	 * @param sendlerId
	 * @param groupId
	 * @param msgType
	 * @param body
	 * @param atUserId
	 * @return
	 * @see
	 */
	public static Message createGroupChatReq(String sendlerId, String groupId,
			int msgType, String body, List<String> atUserId) {

		// 创建一个ack消息
		Message.Builder builder = Message.newBuilder();
		builder.setIdentify(Const.identify);
		builder.setVersion(Const.version);
		builder.setReqType(Type.GROUP_MSG_REQ);
		builder.setMsgTime(new Date().getTime());
		builder.setId(IDGenerator.getUUID());

		GroupChatReq.Builder groupChatReq = GroupChatReq.newBuilder();
		groupChatReq.setSenderId(sendlerId);
		groupChatReq.setGroupId(groupId);
		groupChatReq.setMsgType(msgType);
		groupChatReq.setBody(body);

		if (atUserId != null) {

			StringBuffer stringBuffer = new StringBuffer();

			for (String string : atUserId) {
				stringBuffer.append(string).append(",");
			}
			stringBuffer.deleteCharAt(stringBuffer.length() - 1);
			groupChatReq.setAtUserId(stringBuffer.toString());
		}

		builder.setBody(Any.pack(groupChatReq.build()));
		return builder.build();

	}

	/**
	 * 
	 * Description: 创建ack消息
	 * 
	 * @param ack
	 * @return
	 * @see
	 */
	public static Message createAck(String ack) {

		// 创建一个ack消息
		Message.Builder builder = Message.newBuilder();
		builder.setIdentify(Const.identify);
		builder.setVersion(Const.version);
		builder.setReqType(Type.ACK_REQ);
		builder.setMsgTime(new Date().getTime());
		builder.setId(IDGenerator.getUUID());

		// 创建一个ack
		AckReq.Builder ackBuilder = AckReq.newBuilder();
		ackBuilder.setAck(ack);
		// 把ack消息放到消息body里
		builder.setBody(Any.pack(ackBuilder.build()));
		return builder.build();
	}

	/**
	 * 
	 * Description: 创建用户绑定响应信息
	 * 
	 * @param senderId
	 * @param result
	 * @param msg
	 * @return
	 * @see
	 */
	public static Message crateConnectResp(String senderId, Integer result,
			String msg) {

		Message.Builder builder = Message.newBuilder();
		builder.setIdentify(Const.identify);
		builder.setVersion(Const.version);
		builder.setReqType(Type.CONNET_RESP);
		builder.setMsgTime(new Date().getTime());
		builder.setId(IDGenerator.getUUID());

		ConnectResp.Builder respBuilder = ConnectResp.newBuilder();
		respBuilder.setSenderId(senderId);
		respBuilder.setResult(result);
		respBuilder.setMsg(msg);

		builder.setBody(Any.pack(respBuilder.build()));
		return builder.build();

	}
}
