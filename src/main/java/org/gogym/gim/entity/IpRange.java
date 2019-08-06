/*
 * 文件名：IpRange.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.entity;

public class IpRange {

	// 开始ip
	private String ipStart;

	// 结束ip
	private String ipEnd;

	public IpRange(String ipStart, String ipEnd) {

		this.ipStart = ipStart;
		this.ipEnd = ipEnd;
	}

	public String getIpStart() {

		return ipStart;
	}

	public String getIpEnd() {

		return ipEnd;
	}

}
