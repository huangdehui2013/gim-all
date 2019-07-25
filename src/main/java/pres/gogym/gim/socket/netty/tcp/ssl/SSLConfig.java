/*
 * 文件名：SSLConfig.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年7月13日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.ssl;

public class SSLConfig {

	// 是否开启ssl
	private boolean isSSL = false;
	// 是否开启双向验证
	private boolean needClientAuth = false;
	// 密匙库地址
	private String pkPath;
	// 签名证书地址
	private String caPath;
	// 证书密码
	private String passwd;

	public SSLConfig isSSL(boolean isSSL) {
		this.isSSL = isSSL;
		return this;
	}

	public SSLConfig needClientAuth(boolean needClientAuth) {
		this.needClientAuth = needClientAuth;
		return this;
	}

	public SSLConfig pkPath(String pkPath) {
		this.pkPath = pkPath;
		return this;
	}

	public SSLConfig caPath(String caPath) {
		this.caPath = caPath;
		return this;
	}

	public SSLConfig passwd(String passwd) {
		this.passwd = passwd;
		return this;
	}

	public boolean isSSL() {

		return isSSL;
	}

	public boolean isNeedClientAuth() {

		return needClientAuth;
	}

	public String getPkPath() {

		return pkPath;
	}

	public String getCaPath() {

		return caPath;
	}

	public String getPasswd() {

		return passwd;
	}

}
