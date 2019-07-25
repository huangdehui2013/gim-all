/*
 * 文件名：ClusterMsgHandlerIntf.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.intf;

import pres.gogym.gim.packet.MessageClass.Message;

/**
 * 
 * 〈集群消息处理〉 〈功能详细描述〉
 * 
 * @author gogym
 * @version 2019年7月12日
 * @see ClusterMsgHandlerIntf
 * @since
 */
public abstract class ClusterMsgHandlerIntf {

	public abstract void clusterMsg(Message msg);

}
