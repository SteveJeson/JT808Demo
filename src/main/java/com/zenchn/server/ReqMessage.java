package com.zenchn.server;

import org.apache.log4j.Logger;

import com.zenchn.util.ByteUtil;
import com.zenchn.util.ReqMessageUtil;
import com.zenchn.vo.MsgHeader;

public class ReqMessage extends Message {

	private static Logger log = Logger.getLogger(ReqMessage.class);

	// 头部信息验证 消息体确认
	public void setMessage(byte[] reqMes) {
		if (reqMes == null) {
			log.info("reqMes 为空。。。。");
		}
		
		if (reqMes.length <= ByteUtil.TITLE_CHAR_LEN) {
			log.info("数据内容错误，长度小于" + ByteUtil.TITLE_CHAR_LEN + "个字节");
		}
		
		msgHeader = new MsgHeader();
		
		//消息id
		byte[] m = new byte[2];
		m[0] = reqMes[1];
		m[1] = reqMes[2];
		msgHeader.setMsgId(Integer.parseInt(ByteUtil.toHexString(m), 16));
		
		//终端手机号
		byte[] p = new byte[6];
		p[0] = reqMes[5];
		p[1] = reqMes[6];
		p[2] = reqMes[7];
		p[3] = reqMes[8];
		p[4] = reqMes[9];
		p[5] = reqMes[10];
		msgHeader.setTerminalPhone(ByteUtil.toHexString(p));
		
		//消息流水号
		byte[] f = new byte[2];
		f[0] = reqMes[11];
		f[1] = reqMes[12];
		msgHeader.setFlowId(Integer.parseInt(ByteUtil.toHexString(f), 16));
		
		//消息长度
		int length = reqMes[4];
		msgHeader.setMsgBodyLength(length);
		
//		messageBody = new byte[length];
		// 内容的长度，传输的内容长度大0时进行获取内容进行校验
		if (length > 0
				&& length == (reqMes.length - ByteUtil.FLAG_BIT_HEAD_LENGTH - ByteUtil.FLAG_BIT_CHECK_LENGTH)) {
//			for (int i = 0; i < length; i++) { // 业务数据messageBody
//				messageBody[i] = reqMes[i + ByteUtil.FLAG_BIT_HEAD_LENGTH];
//			}
			messageBody = reqMes;
		} else {
			log.info("setMessage:错误！");
		}
	}

	/**
	 * 方法名称:getResponse 
	 * 方法描述: 业务处理
	 * @return
	 * @author chenbaoyuan
	 * @version 1.0
	 */
	public byte[] getResponse() {
		if (ByteUtil.REGISTER == msgHeader.getMsgId()) {
			log.info("终端注册>>>>>>>>>消息id："+msgHeader.getMsgId()+";终端手机号："+msgHeader.getTerminalPhone()+";流水号："+msgHeader.getFlowId()+">>>>>");
			return ReqMessageUtil.doRegisterMsg(messageBody, msgHeader); // 注册协议
		} else {
			log.info("未知协议>>>>>>>>>");
		}
		return null;
	}

}
