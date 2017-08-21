package com.zenchn.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.zenchn.util.ByteUtil;
import com.zenchn.util.ByteUtil;
import com.zenchn.util.ReqMessageUtil;


public class ReqMessage extends BikeMessage{
	
	private static Logger log = Logger.getLogger(ReqMessage.class);
	private IoSession ioSession;
	//头部信息验证 消息体确认
	public void setMessage(byte[] reqMes) {
		if (reqMes == null) {
			log.info("reqMes 为空。。。。");
		}
		if (reqMes.length <= ByteUtil.TITLE_CHAR_LEN) {
			log.info("数据内容错误，长度小于" + ByteUtil.TITLE_CHAR_LEN + "个字节");
		}
		for (int i = 0; i < ByteUtil.TITLE_CHAR_LEN; i++) {
			super.mes[i] = reqMes[i];
		}
		int length = reqMes.length - 8 ;
		byte[] mesCodeB = new byte[2];
		mesCodeB[0] = reqMes[ByteUtil.TITLE_CHAR_LEN + length]; //校验码
		mesCodeB[1] = reqMes[ByteUtil.TITLE_CHAR_LEN + length + 1]; //校验码
		messageBody = new byte[length];
		if (length > 0 && (reqMes.length == ByteUtil.TITLE_CHAR_LEN + 2 +length)) {// 内容的长度，传输的内容长度大0时进行获取内容进行校验
			for (int i = 0; i < reqMes.length - ByteUtil.TITLE_CHAR_LEN - 2; i++) { //业务数据messageBody
				messageBody[i] = reqMes[i + ByteUtil.TITLE_CHAR_LEN]; 
			}
			int mes = CLibrary.INSTANCE.CRC16_Table(messageBody,(short) messageBody.length); //计算校验码
			byte[] bcB = ByteUtil.writeInt(mes); 
			if (bcB[0] == mesCodeB[0] && bcB[1] == mesCodeB[1]) {
				log.info("校验码正确！");
			} else {
				log.info("校验码错误！");
			}
		} else {
			log.info("错误！");
		}		
	}

	public IoSession getIoSession() {
		return ioSession;
	}

	public void setIoSession(IoSession ioSession) {
		this.ioSession = ioSession;
	}
	/**
	 * 
	 * 方法名称:getResponse
	 * 方法描述: 业务处理
	 * @return
	 * Sep 27, 2011 
	 *
	 * @author<a href=zhanghuaup@126.com>张华</a>
	 * @version 1.0
	 *
	 */
	 
	public byte[] getResponse() {	
		if(ByteUtil.HEARTBEAT_CODE1 == messageBody[1] && ByteUtil.HEARTBEAT_CODE2 == messageBody[0]){ 
			return ReqMessageUtil.doHeatBeat(messageBody, ioSession); //心跳协议
		}else if(ByteUtil.TAKEBIKE_CODE1 == messageBody[1] && ByteUtil.TAKEBIKE_CODE2 == messageBody[0]){
			return ReqMessageUtil.doTakeBike(messageBody, ioSession); //租车协议
		}else if(ByteUtil.BACKBIKE_CODE1 == messageBody[1] && ByteUtil.BACKBIKE_CODE2 == messageBody[0]){
			return ReqMessageUtil.doBackBike(messageBody, ioSession); //还车协议
		}else if(ByteUtil.TAKEBIKE_YC_CODE1 == messageBody[1] && ByteUtil.TAKEBIKE_YC_CODE2 == messageBody[0]){
			return ReqMessageUtil.doYcTakeBike(messageBody, ioSession);	//租车异常
		}else if(ByteUtil.BACKBIKE_YC_CODE1 == messageBody[1] && ByteUtil.BACKBIKE_YC_CODE2 == messageBody[0]){
			return ReqMessageUtil.doYcBackBike(messageBody, ioSession); //还车异常
		}else if(ByteUtil.TAKEBIKE_RECOVER_CODE1 == messageBody[1] && ByteUtil.TAKEBIKE_RECOVER_CODE2 == messageBody[0]){
			return ReqMessageUtil.doTakeBikeRecover(messageBody, ioSession); //租车恢复
		}else if(ByteUtil.BACKBIKE_RECOVER_CODE1 == messageBody[1] && ByteUtil.BACKBIKE_RECOVER_CODE2 == messageBody[0]){
			return ReqMessageUtil.doBackBikeRecover(messageBody, ioSession); //还车恢复
		}else if(ByteUtil.BIKEPILE_STATE_INFOMATION_CODE1 == messageBody[1] && ByteUtil.BIKEPILE_STATE_INFOMATION_CODE2 == messageBody[0]){
			return ReqMessageUtil.doBikePileStateInfomation(messageBody, ioSession); //桩位信息
		}else if(ByteUtil.IPC_ERROR_INFOMATION_CODE1 == messageBody[1] && ByteUtil.IPC_ERROR_INFOMATION_CODE2 == messageBody[0]){
			return ReqMessageUtil.doIPCErrorInfomation(messageBody, ioSession); //工控机出错信息
		}else if(ByteUtil.BIKE_UP_DOWN_SHELF_CODE1 == messageBody[1] && ByteUtil.BIKE_UP_DOWN_SHELF_CODE2 == messageBody[0]){
			return ReqMessageUtil.doBikeUpDownShelf(messageBody, ioSession);//	车辆上下架
		}else{
			log.info("协议编码错误！！！！！！1111>>>>>>>>>" +messageBody[0]+ "<<<<<<<<<<<<<1  2>>>>>>" + messageBody[1]+"<<<<<<<<<22");
		}
		return null;
	}
	
	// 调用c的dll文件
	public interface CLibrary extends Library {
		CLibrary INSTANCE = (CLibrary) Native.loadLibrary(
				(Platform.isWindows() ? "crc16" : "c"), CLibrary.class);
		int CRC16_Table(byte[] body, short count);
	}

}
