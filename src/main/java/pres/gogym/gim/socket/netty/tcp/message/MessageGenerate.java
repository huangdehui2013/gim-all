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

package pres.gogym.gim.socket.netty.tcp.message;

import java.util.Date;

import pres.gogym.gim.common.Const;
import pres.gogym.gim.common.Type;
import pres.gogym.gim.packet.AckReqClass.AckReq;
import pres.gogym.gim.packet.ConnectRespClass.ConnectResp;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.utils.IDGenerator;

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
