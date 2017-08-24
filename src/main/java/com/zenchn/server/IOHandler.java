package com.zenchn.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.zenchn.util.ByteUtil;

public class IOHandler extends IoHandlerAdapter {

	public static Logger logger = Logger.getLogger(IOHandler.class);

	 @Override
	 public void sessionCreated(IoSession session) throws Exception {
		 logger.info("服务端与客户端:"+session.getRemoteAddress()+" 创建连接...");
	 }

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端:"+session.getRemoteAddress()+" 连接打开...");
		super.sessionOpened(session);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("收到客户端的数据："+message.toString());
		String msg = message.toString();
		if(msg.indexOf(" ") > -1){
 			msg = msg.replaceAll(" +","");
 		}
 		byte[] msgByte = ByteUtil.toByteArray(msg);
		ServerMessage serverMessage = new ServerMessage();
		ServerData serverData = new ServerData();
		ReqMessage posReqMessage = (ReqMessage) serverData.setMessage(msgByte);
		// 业务处理  并返回前台所需回应
		byte[] result = serverMessage.getResponse(posReqMessage); 
		
		session.write(ByteUtil.toHexString(result));
		
		//byte[] line = (byte[]) message;
		// System.out.println(line.length +"我接受到的数据的长度是啊 ！！！！！！！！！！！！！！！！");
		// ByteUtil.out(); // 测试接收到数据包数
//		ServerMessage serverMessage = new ServerMessage();
		// 获得服务器端数据
//		if (null != line) {
//			ServerData serverData = new ServerData();
//			ReqMessage posReqMessage = (ReqMessage) serverData.setMessage(line); // 校验头部消息 返回ReqMessage对象
//			if (null != posReqMessage) {
//				posReqMessage.setIoSession(session);
//				byte[] result = serverMessage.getResponse(posReqMessage); // 业务处理  并返回前台所需回应
//				if (null != result) {
//					session.write(IoBuffer.wrap(result));// 发送消息 这里是发送字节数组的重点
//				}else{
//					log.info("result为空！！！！！！！！");
//				}
//			} else {
//				log.info("posReqMessage信息为空！！！！！");
//			}
//		} else {
//			log.info("line信息为空！！！！！");
//		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("服务端发送信息成功:"+message);
	}

//	 @Override
//	 public void sessionClosed(IoSession session) throws Exception {
//		 logger.info("服务端关闭了与"+session.getRemoteAddress()+"的连接");
//	 }

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
//		 if (session != null && session.isConnected()) {
//		 session.close(true);
//		 }
//		 logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		if (session != null && session.isConnected()) {
			session.close(true);
		}
		logger.error("服务端发送异常...", cause);
	}
}
