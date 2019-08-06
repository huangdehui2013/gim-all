/*
 * 文件名：RedisConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package org.gogym.gim.socket.netty.tcp.redis;

public class RedisConfig {

	private String host;

	private Integer port;

	private String password;

	private Integer connectionTimeout = 5000;

	private Integer maxTotal = 20;

	private Integer maxIdle = 20;

	private Integer minIdle = 5;

	private Long maxWaitMillis = 10000L;

	private Boolean testOnBorrow = true;

	private Boolean testOnReturn = true;

	private Boolean testWhileIdle = false;

	public String getHost() {

		return host;
	}

	public void setHost(String host) {

		this.host = host;
	}

	public Integer getPort() {

		return port;
	}

	public void setPort(Integer port) {

		this.port = port;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public Integer getConnectionTimeout() {

		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {

		this.connectionTimeout = connectionTimeout;
	}

	public Integer getMaxTotal() {

		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {

		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {

		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {

		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {

		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {

		this.minIdle = minIdle;
	}

	public Long getMaxWaitMillis() {

		return maxWaitMillis;
	}

	public void setMaxWaitMillis(Long maxWaitMillis) {

		this.maxWaitMillis = maxWaitMillis;
	}

	public Boolean getTestOnBorrow() {

		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {

		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {

		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {

		this.testOnReturn = testOnReturn;
	}

	public Boolean getTestWhileIdle() {

		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {

		this.testWhileIdle = testWhileIdle;
	}

}
