/*
 * 文件名：GimListenerIntf.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.intf;

import pres.gogym.gim.packet.MessageClass.Message;

public interface GimListenerIntf {

	public void userBind(String userId, String address);

	public void userUnBind(String userId);

	public void messageRead(String address, Message message);

	public void messageWrite(boolean isSuccess, Message message);
	
	public void channelAdd(String address);
	
	public void channelClose(String address);

}
