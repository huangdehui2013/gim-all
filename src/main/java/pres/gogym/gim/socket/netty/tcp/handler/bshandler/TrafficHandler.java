/*
 * 文件名：TrafficHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月24日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.handler.bshandler;

import io.netty.handler.traffic.TrafficCounter;

import java.util.Collection;

import pres.gogym.gim.socket.netty.tcp.intf.GlobalTrafficIntf;

public class TrafficHandler implements GlobalTrafficIntf {

	@Override
	public void currentThroughput(long readThroughput, long writeThroughput) {
		System.out.println("实时流量:"+readThroughput+"|"+writeThroughput);
	}

	@Override
	public void totalBytes(long totalRead, long totalWrite) {
		System.out.println("总流量:"+totalRead+"|"+totalWrite);
	}

	@Override
	public void allChannelTraffic(Collection<TrafficCounter> collection) {
		
	}

}
