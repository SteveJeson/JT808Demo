package com.zenchn.server;

import org.apache.log4j.Logger;

import com.zenchn.vo.MsgHeader;

public class Message {
	private Logger log = Logger.getLogger(Message.class);

	private boolean flag = true;
	
	/**原始字符串**/
	protected String message;
	
	/**消息头**/
	protected MsgHeader msgHeader;
	
	/**消息体**/
	protected byte[] messageBody;// 消息体

	/**检验码**/
	protected int mesCode;// 消息效验码
	
	public void errorMessage(String str) {
		log.error("错误消息：" + str);
		flag = false;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte[] getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(byte[] messageBody) {
		this.messageBody = messageBody;
	}

	public int getMesCode() {
		return mesCode;
	}

	public void setMesCode(int mesCode) {
		this.mesCode = mesCode;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
