package pres.gogym.gim.common;

public class Const {
	// 协议标识
	public final static String identify = "gim";
	// 协议版本
	public final static String version = "1.0";

	// 重发消息间隔时间（毫秒）
	public static final Long msg_delay = 20 * 1000L;

	// 创建的群组放到redis的key
	public final static String REDIS_CHAT_GROUP = "REDIS_CHAT_GROUP_";

	public static final String CHARSET = "utf-8";

	public static final String netty_attributeKey = "netty.channel";

	public static final Integer success = 10000;

	public static final Integer faild = 10001;

}
