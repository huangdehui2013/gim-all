/*
 * 文件名：ChatContext.java
 * 版权：Copyright by www.poly.com
 * 描述：
 * 修改人：gogym
 * 修改时间：2019年6月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package pres.gogym.gim.socket.netty.tcp.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;

import pres.gogym.gim.socket.netty.tcp.message.MessageDelayPacket;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GimContext {

//	private static final ThreadLocal<GimContext> threadLocal = new ThreadLocal<GimContext>();// 全局静态变量
//
//	private GimContext() {
//	}
//
//	public static GimContext shareInstance() {
//
//		if (threadLocal.get() == null) {
//			synchronized (GimContext.class) {
//				if (threadLocal.get() == null) {
//					GimContext gimContext = new GimContext();
//					threadLocal.set(gimContext);
//				}
//			}
//		}
//		return threadLocal.get();
//
//	}

	// 实例一个连接容器用户保存TCP连接
	public ChannelGroup channels = new DefaultChannelGroup(
			GlobalEventExecutor.INSTANCE);

	// userId与ChannelId的映射关系，这里注意要使用线程安全的ConcurrentMap
	public ConcurrentMap<String, ChannelId> userChannelMap = new ConcurrentHashMap<String, ChannelId>();

	// 群组成员映射
	public ConcurrentMap<String, CopyOnWriteArrayList<String>> groupUserMap = new ConcurrentHashMap<String, CopyOnWriteArrayList<String>>();

	// 保存已经写到客户端，但还未得到客户端ack确认的消息的key
	// public CopyOnWriteArrayList<String> ackList = new
	// CopyOnWriteArrayList<String>();

	// 保存已经写到客户端，但未收到ack的msg,通过延迟队列重发
	public DelayQueue<MessageDelayPacket> delayMsgQueue = new DelayQueue<MessageDelayPacket>();

	// 创建一个阻塞队列，用于缓冲需要转发的消息
	// public static BlockingQueue<Message> chatMsgQueue = new
	// LinkedBlockingQueue<Message>();

	// 创建一个阻塞队列，用于缓冲还未处理的群聊消息
	// public static BlockingQueue<Message> chatGroupMsgQueue = new
	// LinkedBlockingQueue<Message>();

}
