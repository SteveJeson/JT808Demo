package com.zenchn;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MyMinaClient {
	
	/**
	 * 创建客户端
	 * @return
	 */
	public IoConnector creatClient() {
		
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.getFilterChain().addLast(
				    "codec",
				    new ProtocolCodecFilter(new TextLineCodecFactory(Charset
				      .forName("utf-8"))));
		connector.setHandler(new ClientHandler());
		return connector;
	}
	
	/**
	 * 获取 session
	 * @param connector
	 * @param ip
	 * @param port
	 * @return
	 */
	public IoSession getIoSession(IoConnector connector,String ip,int port){
		 IoSession session = null;
		ConnectFuture future = connector.connect(new InetSocketAddress(ip, port));
		future.awaitUninterruptibly();
		session = future.getSession();
	    return session;
	}
	
	/**
	 * 发送消息
	 * @param session
	 * @param object
	 */
	public void sendMsg(IoSession session,Object object){
		session.write(object);
	}
}
