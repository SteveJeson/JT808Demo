package com.zenchn.server;

import org.apache.log4j.Logger;

import com.zenchn.util.ByteUtil;


public class BikeMessage {

	
	private boolean flag = true;
	private Logger log=Logger.getLogger(BikeMessage.class);
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public void errorMessage(String str){
		log.error("错误消息："+str);
		flag=false;
	}
	
	
	protected byte[] mes=new byte[ByteUtil.TITLE_CHAR_LEN];
	
	protected  String message;
	
	protected byte[] messageBody;//消息体
	
	protected int mesCode;//消息效验码
	
	protected void setMessage(String message){
		this.message=message;
	}
	
	//得到消息长度
	public int getLength(){
		byte[] b = new byte[2];
		b[0] = mes[4];
		b[1] = mes[5];
		return ByteUtil.lBytes2ToInt(b);
	}

}
