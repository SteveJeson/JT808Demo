package com.zenchn.server;

import org.apache.log4j.Logger;

public class ServerMessage {
	
	Logger log=Logger.getLogger(ServerMessage.class);
	private ReqMessage reqMessage;
	public byte [] getResponse(Message message) {
		// 检查 message 是否为PosReqMessage的对象
		if(message instanceof ReqMessage){
			reqMessage =(ReqMessage) message;
			//服务器响应 返回消息
			return reqMessage.getResponse();
		}
		else
		{
			log.debug("message 不是同一个对象 ");
			return null;
		}
	}

}
