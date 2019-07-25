/*
 * 文件名：GlobalTrafficIntf.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.intf;

import io.netty.handler.traffic.TrafficCounter;

import java.util.Collection;

public interface GlobalTrafficIntf {

	public void currentThroughput(long readThroughput, long writeThroughput);

	public void totalBytes(long totalRead, long totalWrite);

	public void allChannelTraffic(Collection<TrafficCounter> collection);

}
