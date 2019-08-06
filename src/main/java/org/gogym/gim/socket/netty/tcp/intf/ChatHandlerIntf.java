package org.gogym.gim.socket.netty.tcp.intf;

import org.gogym.getty.channel.ChannelHandlerContext;
import org.gogym.gim.packet.MessageClass.Message;

/**
 * 
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see ChatHandlerIntf
 * @since
 */
public interface ChatHandlerIntf {

	/**
	 * 
	 * Description: 业务处理接口
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 * @see
	 */
	public void handler(Message message,ChannelHandlerContext ctx);

}
