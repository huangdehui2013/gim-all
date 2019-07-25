/*
 * 文件名：NettyChannelAttribute.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.server;

/**
 * 
 * 〈netty通道属性类〉
 * 〈功能详细描述〉
 * @author gogym
 * @version 2019年7月12日
 * @see NettyChannelAttribute
 * @since
 */
public class NettyChannelAttribute {

	private String userId;

	public NettyChannelAttribute(String userId) {
		this.userId = userId;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

}
