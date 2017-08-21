package com.zenchn;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;

public class Test {

	public static void main(String[] args) {
		try {
			MyMinaClient mmc = new MyMinaClient();
			IoConnector connector = mmc.creatClient();
			IoSession session = mmc.getIoSession(connector, "127.0.0.1", 8181);
//			for(int i=0;i<5;i++){
				String str = "7E 01 00 00 2D 91 61 22 70 01 10 00 01 00 2C 01 2C 37 30 35 30 33 48 54 36 31 31 41 30 30 30 30 30 30 30 30 30 30 30 30 30 30 02 07 00 00 01 01 00 01 D4 C1 42 31 32 33 34 35 A7 7E";
				byte[] b = str.getBytes(Charset.forName("GBK"));
				System.out.println(b.toString());
				mmc.sendMsg(session, str);
//				Thread.sleep(2000);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
