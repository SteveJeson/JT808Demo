package com.zenchn;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;

import com.zenchn.util.ByteUtil;

public class Test {

	public static void main(String[] args) {
		try {
			MyMinaClient mmc = new MyMinaClient();
			IoConnector connector = mmc.creatClient();
			IoSession session = mmc.getIoSession(connector, "127.0.0.1", 8181);
			// for(int i=0;i<5;i++){
			String register = "7E 01 00 00 2D 91 61 22 70 01 10 00 01 00 2C 01 2C 37 30 35 30 33 48 54 36 31 31 41 30 30 30 30 30 30 30 30 30 30 30 30 30 30 02 07 00 00 01 01 00 01 D4 C1 42 31 32 33 34 35 A7 7E";
			String register_answer = "7E 81 00 00 0E 91 61 22 70 01 10 00 01 00 01 00 31 32 33 34 35 36 37 38 39 30 5A 67 7E";
			byte[] reqMes = ByteUtil.toByteArray(register);
			System.out.println(ByteUtil.checkCode(reqMes,2,reqMes.length-2));
			mmc.sendMsg(session, register);
			// Thread.sleep(2000);
			// }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
