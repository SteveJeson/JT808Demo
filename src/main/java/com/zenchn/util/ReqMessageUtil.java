package com.zenchn.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.zenchn.vo.MsgHeader;

public class ReqMessageUtil {
	
	public static long currTime = System.currentTimeMillis();
	
	/**
	 * 方法名称:doRegisterMsg
	 * 方法描述:解析注册协议
	 * @param messageBody
	 * @param msgHeader
	 * @return
	 */
	public static byte[] doRegisterMsg(byte[] messageBody ,MsgHeader msgHeader){
		
		//应答流水号、结果、鉴权码
		byte[] b = new byte[3];
		b[0] = messageBody[11];
		b[1] = messageBody[12];
		b[2] = ByteUtil.REGISTER_RESULT;//结果：成功
		//鉴权码
		byte[] t = ByteUtil.REPLY_TOKEN.getBytes();
		
		byte[] body = ByteUtil.plus2Bytes(b, t);
		
		//消息头消息ID、消息体属性、终端手机号、消息流水号
		//消息体属性
		byte[] l = BitOperator.integerTo2Bytes(body.length);
		
		//消息ID
		byte[] i = BitOperator.integerTo2Bytes(ByteUtil.REGISTER_ANSWER);
		
		//终端手机号
		byte[] p = new byte[6];
		p[0] = messageBody[5];
		p[1] = messageBody[6];
		p[2] = messageBody[7];
		p[3] = messageBody[8];
		p[4] = messageBody[9];
		p[5] = messageBody[10];
		
		//流水号
		byte[] f = new byte[2];
		f[0] = messageBody[11];
		f[1] = messageBody[12];
		byte[] pf = ByteUtil.plus2Bytes(p,f);
				
		byte[] il = ByteUtil.plus2Bytes(i,l);
		
		byte[] header = ByteUtil.plus2Bytes(il,pf);
		
		return BuildMessage(header, body);
	}
	
	/**
	 * 方法名称:BuildMessage
	 * 方法描述: 组合应答报文
	 */
	public static byte[] BuildMessage(byte[] header,byte[] body){

		byte[] cbody = ByteUtil.plus2Bytes(header, body);
		
		//校验码的生成
		byte code = ByteUtil.checkCode(cbody,0,cbody.length);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream os = new DataOutputStream(baos);
		try {
			os.writeByte(ByteUtil.FLAG_BIT);
			os.write(cbody);
			os.writeByte(code);
			os.writeByte(ByteUtil.FLAG_BIT);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
	


}
