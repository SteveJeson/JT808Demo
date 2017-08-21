package com.zenchn.server;

import com.zenchn.util.ByteUtil;




public class ServerData {
	
	public ServerData(){
		
	}
	/**
	 * 
	 * @date Aug 10, 2011 2:14:30 PM 
	 * @param reqMes
	 * @return
	 * @author zhangh
	 */
	public BikeMessage setMessage(byte[] reqMes){

		if(reqMes.length<=0){
			System.out.println("reqMes中数据为空");
			return null;
		}
		//验证头部信息正确性
		if(ByteUtil.START_CODE1 == reqMes[0] && ByteUtil.START_CODE2 == reqMes[1] && ByteUtil.START_CODE3 == reqMes[2] && ByteUtil.START_CODE4 == reqMes[3]){
			ReqMessage posReqMessage=new ReqMessage();
			posReqMessage.setMessage(reqMes);
			return posReqMessage;
		}
		return null;
	}

}
