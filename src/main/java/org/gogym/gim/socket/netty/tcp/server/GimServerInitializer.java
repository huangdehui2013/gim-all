package org.gogym.gim.socket.netty.tcp.server;

import java.util.concurrent.TimeUnit;

import org.gogym.getty.channel.ChannelInitializer;
import org.gogym.getty.channel.ChannelPipeline;
import org.gogym.getty.channel.socket.SocketChannel;
import org.gogym.getty.handler.codec.protobuf.ProtobufDecoder;
import org.gogym.getty.handler.codec.protobuf.ProtobufEncoder;
import org.gogym.getty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.gogym.getty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.gogym.getty.handler.ipfilter.RuleBasedIpFilter;
import org.gogym.getty.handler.ssl.SslHandler;
import org.gogym.getty.handler.timeout.IdleStateHandler;
import org.gogym.gim.packet.MessageClass.Message;
import org.gogym.gim.socket.netty.tcp.handler.nettyHandler.ChatServerHandler;
import org.gogym.gim.socket.netty.tcp.handler.nettyHandler.ConnectManageHandler;
import org.gogym.gim.socket.netty.tcp.handler.nettyHandler.HeartBeatServerHandler;
import org.gogym.gim.socket.netty.tcp.handler.nettyHandler.IpFilterRuleHandler;
import org.gogym.gim.socket.netty.tcp.ssl.SSLContextFactory;
import org.gogym.gim.socket.netty.tcp.ssl.SslCheckServerHandler;

/**
 * netty处理器配置
 * 
 * @author kokJuis
 * @version 1.0
 * @date 2016-9-30
 */
public class GimServerInitializer extends ChannelInitializer<SocketChannel> {

	private GimConfig gimConfig;

	public GimServerInitializer(GimConfig gimConfig) {
		this.gimConfig = gimConfig;
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		SslHandler sslHandler = SSLContextFactory.getOpenSslHandler(
				gimConfig.getSslConfig(), ch.alloc());
		if (sslHandler != null) {
			pipeline.addLast("ssl", sslHandler);
			pipeline.addLast(new SslCheckServerHandler());
		}

		// 全局流量监控
		if (gimConfig.getGlobalTrafficConfig().isMonitor()) {
			pipeline.addLast(gimConfig.getGlobalTrafficConfig()
					.getGlobalTrafficHandler());

			pipeline.addLast(gimConfig.getGlobalTrafficConfig()
					.getChannelTrafficHandler());

		}

		// ----配置Protobuf处理器----
		// 用于decode前解决半包和粘包问题（利用包头中的包含数组长度来识别半包粘包）
		pipeline.addLast(new ProtobufVarint32FrameDecoder());
		// 配置Protobuf解码处理器，消息接收到了就会自动解码，ProtobufDecoder是netty自带的，Message是自己定义的Protobuf类
		pipeline.addLast(new ProtobufDecoder(Message.getDefaultInstance()));

		// 用于在序列化的字节数组前加上一个简单的包头，只包含序列化的字节长度。
		pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
		// 配置Protobuf编码器，发送的消息会先经过编码
		pipeline.addLast(new ProtobufEncoder());
		// ----Protobuf处理器END----

		// 心跳起搏器
		pipeline.addLast(new IdleStateHandler(gimConfig.getReaderIdleTime(), 0,
				0, TimeUnit.SECONDS));
		// 心跳检测
		pipeline.addLast(new HeartBeatServerHandler());
		// ip过滤
		pipeline.addLast(new RuleBasedIpFilter(new IpFilterRuleHandler(
				gimConfig)));
		pipeline.addLast(new ConnectManageHandler());

		pipeline.addLast(new ChatServerHandler(gimConfig));

	}
}
