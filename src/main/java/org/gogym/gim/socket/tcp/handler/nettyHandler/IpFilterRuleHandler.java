/*
 * 文件名：IpFilterRuleHandler.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.tcp.handler.nettyHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.CopyOnWriteArrayList;

import org.gogym.getty.handler.ipfilter.IpFilterRule;
import org.gogym.getty.handler.ipfilter.IpFilterRuleType;
import org.gogym.gim.entity.IpRange;
import org.gogym.gim.socket.tcp.server.GimConfig;
import org.gogym.gim.utils.NetWorkUtil;

/**
 * 
 * Ip过滤器
 * 
 * @author gogym
 * @version 2019年7月13日
 * @see IpFilterRuleHandler
 * @since
 */
public class IpFilterRuleHandler implements IpFilterRule {

	GimConfig gimConfig;

	public IpFilterRuleHandler(GimConfig gimConfig) {
		this.gimConfig = gimConfig;
	}

	@Override
	public boolean matches(InetSocketAddress remoteAddress) {

		// ip转成long类型
		String ip = remoteAddress.getHostString();
		long ipLong = NetWorkUtil.ipToLong(ip);

		CopyOnWriteArrayList<IpRange> blackIps = gimConfig.getBlackIps();
		for (IpRange ipRange : blackIps) {

			long ipStart = NetWorkUtil.ipToLong(ipRange.getIpStart());
			long ipEnd = NetWorkUtil.ipToLong(ipRange.getIpEnd());
			// 比较ip区间
			if (ipLong >= ipStart && ipLong <= ipEnd) {
				return true;
			}
		}

		return false;

	}

	@Override
	public IpFilterRuleType ruleType() {
		// 返回拒绝则表示拒绝连接，返回接受则表示可以连接
		return IpFilterRuleType.REJECT;
	}

}
