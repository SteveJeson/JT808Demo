package com.zenchn.server;

import org.apache.log4j.Logger;

import com.zenchn.util.ByteUtil;


public class ServerData {
	
	public static Logger log = Logger.getLogger(ServerData.class);
	
	public ServerData(){
		
	}
	
	/**
	 * @param reqMes
	 * @return
	 */
	public Message setMessage(byte[] reqMes){

		if(reqMes.length<=0){
			log.info("reqMes中数据为空");
			return null;
		}
		//校验头尾的标识位
		 if (ByteUtil.FLAG_BIT == reqMes[0] && ByteUtil.FLAG_BIT == reqMes[reqMes.length-1]) {
			//验证消息长度 
         	int lengthB = reqMes[4];//消息体属性
         	if(lengthB == (reqMes.length-ByteUtil.FLAG_BIT_HEAD_LENGTH-ByteUtil.FLAG_BIT_CHECK_LENGTH)){
         		//验证校验码
         		byte code = ByteUtil.checkCode(reqMes,2,reqMes.length-2);
         		if(code == reqMes[reqMes.length-2]){
         			ReqMessage posReqMessage = new ReqMessage();
         			posReqMessage.setMessage(reqMes);
         			return posReqMessage;
         		}else{
         			log.info("消息校验码不正确");
         		}
         	}else{
         		log.info("消息长度不正确");
         	}
		}else{
			log.info("标识位不正确");
		}
		return null;
	}

}
