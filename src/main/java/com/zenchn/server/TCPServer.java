package com.zenchn.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.SimpleBufferAllocator;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.zenchn.filter.MessageCodecFactory;

public class TCPServer {
	
	private static Logger logger = Logger.getLogger(TCPServer.class); 
	private ExecutorService filterExecutor = new OrderedThreadPoolExecutor();
	IoAcceptor acceptor = null;
	public  TCPServer(int port) {
		
		acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors()+1); //数据解析线程数cpu+1
	    acceptor.getFilterChain().addLast("threadPool",new ExecutorFilter(filterExecutor));//设置线程池，以支持多线程
	    IoBuffer.setUseDirectBuffer(false); //缓冲区大小可更改
	    IoBuffer.setAllocator(new SimpleBufferAllocator()); //设置分配器可使用新的缓冲区
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);// 读写通道10秒内无操作进入空闲状态
		acceptor.getSessionConfig().setWriteTimeout(30000);//设置超时时间
		acceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MessageCodecFactory())); //添加自定义过滤器
		acceptor.setHandler(new IOHandler());// 绑定逻辑处理器
		try {
			acceptor.bind(new InetSocketAddress(port)); //绑定端口
			logger.info("服务端启动成功... 端口号为：" + port);
		} catch (IOException e) {
			logger.error("服务端启动异常....", e);
			e.printStackTrace();
		}
	}

}

