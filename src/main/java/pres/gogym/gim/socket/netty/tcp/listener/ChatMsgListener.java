/*
 * 文件名：ChatMsgListener.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.listener;

import io.netty.channel.ChannelHandlerContext;
import pres.gogym.gim.packet.MessageClass.Message;
import pres.gogym.gim.socket.netty.tcp.server.GimConfig;

/**
 * 
 * 〈消息读取监听〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年6月11日
 * @see ChatMsgListener
 * @since
 */
public interface ChatMsgListener {

	/**
	 * 
	 * Description: 实现该方法，获取消息
	 * 
	 * @param message
	 * @see
	 */
	public void read(GimConfig gimConfig,Message message, ChannelHandlerContext ctx);

}
