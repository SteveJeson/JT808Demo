package com.zenchn.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ByteUtil {
	
	public static final int TITLE_CHAR_LEN = 6;
	public static final String CHARSET = "UTF-8";   
	public final static byte START_CODE1 = (byte) 0xAA; //信息头1
	public final static byte START_CODE2 = 0x55; //信息头2
	public final static byte START_CODE3 = 0x67; //信息头3
	public final static byte START_CODE4 = 0x74; //信息头4
	
	public final static byte HEARTBEAT_CODE1 = (byte) 0x80; //心跳协议命令码1
	public final static byte HEARTBEAT_CODE2 = 0x00; //心跳协议命令码2
	
	public final static byte HEARTBEAT_BACK_CODE1 = 0x40; //心跳协议命令码应答1
	public final static byte HEARTBEAT_BACK_CODE2 = 0x00; //心跳协议命令码应答2
	
	public final static byte TAKEBIKE_CODE1 = (byte) 0x80; //租车协议命令码1
	public final static byte TAKEBIKE_CODE2 = 0x01; //租车协议命令码2
	
	public final static byte TAKEBIKE_BACK_CODE1 = 0x40; //租车协议命令码应答1
	public final static byte TAKEBIKE_BACK_CODE2 = 0x01; //租车协议命令码应答2
	
	public final static byte BACKBIKE_CODE1 =  (byte) 0x80; //还车协议命令码1
	public final static byte BACKBIKE_CODE2 =  0x02; //还车车协议命令码2
	
	public final static byte BACKBIKE_BACK_CODE1 =  0x40; //还车协议命令码应答1
	public final static byte BACKBIKE_BACK_CODE2 =  0x02; //还车协议命令码应答2
	
	public final static byte TAKEBIKE_YC_CODE1 =  (byte) 0x80; //异常租车协议命令码1
	public final static byte TAKEBIKE_YC_CODE2 =   0x03; //异常租车协议命令码2
	
	public final static byte TAKEBIKE_YC_BACK_CODE1 =   0x40; //异常租车协议命令码应答1
	public final static byte TAKEBIKE_YC_BACK_CODE2 =   0x03; //异常租车协议命令码应答1
	
	public final static byte BACKBIKE_YC_CODE1 =  (byte) 0x80; //异常还车协议命令码1
	public final static byte BACKBIKE_YC_CODE2 =  0x04; //异常还车协议命令码2
	
	public final static byte BACKBIKE_YC_BACK_CODE1 =  0x40; //异常还车协议命令码应答1
	public final static byte BACKBIKE_YC_BACK_CODE2 =  0x04; //异常还车协议命令码应答2
	
	public final static byte TAKEBIKE_RECOVER_CODE1 =  (byte) 0x80; //租车恢复命令码1
	public final static byte TAKEBIKE_RECOVER_CODE2 =  0x05; //租车恢复命令码2
	
	public final static byte TAKEBIKE_RECOVER_BACK_CODE1 =  0x40; //租车恢复命令码应答1
	public final static byte TAKEBIKE_RECOVER_BACK_CODE2 =  0x05; //租车恢复命令码应答2
	
	public final static byte BACKBIKE_RECOVER_CODE1 =  (byte) 0x80; //还车恢复命令码1
	public final static byte BACKBIKE_RECOVER_CODE2 =  0x06; //还车恢复命令码2
	
	public final static byte BACKBIKE_RECOVER_BACK_CODE1 =  0x40; //还车恢复命令码应答1
	public final static byte BACKBIKE_RECOVER_BACK_CODE2 =  0x06; //还车恢复命令码应答2
	
	public final static byte BIKEPILE_STATE_INFOMATION_CODE1 = (byte) 0x80; //车位实时信息命令码1
	public final static byte BIKEPILE_STATE_INFOMATION_CODE2 = 0x07; //车位实时信息命令码2
		
	public final static byte BIKEPILE_STATE_INFOMATION_BACK_CODE1 = 0x40; //车位实时信息命令码应答1
	public final static byte BIKEPILE_STATE_INFOMATION_BACK_CODE2 = 0x07; //车位实时信息命令码应答2
	
	public final static byte IPC_ERROR_INFOMATION_CODE1 = (byte) 0x80; //工控机出错信息命令码1
	public final static byte IPC_ERROR_INFOMATION_CODE2 = 0x08; //工控机出错信息命令码2
			
	public final static byte IPC_ERROR_INFOMATION_BACK_CODE1 =  0x40; //工控机出错信息命令码应答1 	
	public final static byte IPC_ERROR_INFOMATION_BACK_CODE2 =  0x08; //工控机出错信息命令码应答2
	
	public final static byte BIKE_UP_DOWN_SHELF_CODE1 = (byte) 0x80; //车辆上下架命令码1
	public final static byte BIKE_UP_DOWN_SHELF_CODE2 = 0x09; //车辆上下架命令码2
	
	public final static byte BIKE_UP_DOWN_SHELF_BACK_CODE1 = 0x40; //车辆上下架命令码应答1
	public final static byte BIKE_UP_DOWN_SHELF_BACK_CODE2 = 0x09; //车辆上下架命令码应答2
	
	public final static byte IPC_RECONCILIATION_CODE1 = (byte) 0x80; //工控机对帐命令码1
	public final static byte IPC_RECONCILIATION_CODE2 = 0x0a; //工控机对帐命令码1
	
	public final static byte IPC_RECONCILIATION_BACK_CODE1 = 0x40; //工控机对帐命令码应答1
	public final static byte IPC_RECONCILIATION_BACK_CODE2 = 0x0a; //工控机对帐命令码应答1
	
	public final static byte IPC_UPLOAD_FILE_CODE1 = (byte) 0x80; // 工控机上传文件命令码1
	public final static byte IPC_UPLOAD_FILE_CODE2 =  0x0b; // 工控机上传文件命令码2
	
	public final static byte IPC_UPLOAD_FILE_BACK_CODE1 = 0x40; //工控机上传文件命令码应答1
	public final static byte IPC_UPLOAD_FILE_BACK_CODE2 = 0x0b;//工控机上传文件命令码应答2
	
	public final static byte BACK_BIKE_WHITOUT_MONEY_CODE1 = (byte) 0x80; //还车未收费命令码1
	public final static byte BACK_BIKE_WHITOUT_MONEY_CODE2 = 0x0c; //还车未收费命令码2
	
	public final static byte BACK_BIKE_WHITOUT_MONEY_BACK_CODE1 = (byte) 0x40; //还车未收费命令码应答1
	public final static byte BACK_BIKE_WHITOUT_MONEY_BACK_CODE2 = (byte) 0x0C; //还车未收费命令码应答2

	public final static byte RENEWAL_FEES_CODE1 = (byte) 0x80; //欠费（还车后续收费)命令码1
	public final static byte RENEWAL_FEES_CODE2 = 0x0d; //欠费（还车后续收费)命令码2
	
	public final static byte RENEWAL_FEES_BACK_CODE1 = 0x40; //欠费（还车后续收费)命令码应答1
	public final static byte RENEWAL_FEES_BACK_CODE2 = 0x0d;//欠费（还车后续收费)命令码应答2
	
	public final static byte DIY_COLLECT_CODE1 = 0x00; // 手动采集命令码1
	public final static byte DIY_COLLECT_CODE2 = 0x01; // 手动采集命令码2

	public final static byte DIY_COLLECT_BACK_CODE1 = 0x0c; // 手动采集命令码应答1
	public final static byte DIY_COLLECT_BACK_CODE2 = 0x01; // 手动采集命令码应答2 
	
	public final static byte POS_EXACT_CODE1 = (byte) 0x80; // 	Pos机异常处理命令码1
	public final static byte POS_EXACT_CODE2 = 0x0e; // 	Pos机异常处理命令码2

	public final static byte POS_EXACT_BACK_CODE1 = 0x40; // Pos机异常处理命令码应答1
	public final static byte POS_EXACT_BACK_CODE2 = 0x0e; // Pos机异常处理命令码应答2 
	
 
	/**
	 * 2个数组组合
	 * @date Aug 10, 2011 10:19:27 AM 
	 * @param no
	 * @param dateb
	 * @return
	 * @author zhangh
	 */
	public  static byte[] plus2Bytes(byte no[],byte dateb[]){
		if(null == no || null == dateb){
			return null;
		}
		byte all[] = new byte[no.length+dateb.length];
		
		for(int j = 0;j<no.length;j++){
			all[j] = no[j];
		}
		for(int k = no.length;k<all.length;k++){
			all[k] = dateb[k-no.length];
		}
		return all;
	}
	/**
	  * 将高字节数组转换为int
	  * @param b byte[]
	  * @return int
	  */
	public static long hBytesToInt(byte[] b) {
		long s = 0;
	  for (int i = 0; i < 3; i++) {
	    if (b[i] >= 0) {
	    s = s + b[i];
	    } else {
	    s = s + 256 + b[i];
	    }
	    s = s * 256;
	  }
	  if (b[3] >= 0) {
	    s = s + b[3];
	  } else {
	    s = s + 256 + b[3];
	  }
	  return s;
	} 

	 /**  
     * 向输出流中写字符串 字符串 结构 为 一个指定字符串字节长度的短整型+实际字符串  
     *   
     * @param os  
     * @param str  
     * @throws IOException  
     */ 
	public static byte[] writeUTF(String input){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();   
        DataOutputStream os = new DataOutputStream(baos);     
        
        try {
        	byte[] data = input.getBytes(CHARSET);   
//            os.writeShort(Short.reverseBytes((short) data.length));  
            os.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     
        byte[] b = baos.toByteArray();  
        return b;
	}
	
	/**
	 * 2位低字节数组转化
	 * @date Aug 10, 2011 5:23:21 PM 
	 * @param b
	 * @return
	 * @author zhangh
	 */
	public static int lBytes2ToInt(byte[] b) {
		  int s = 0;
		  for (int i = 0; i < 1; i++) {
		    if (b[1-i] >= 0) {
		    s = s + b[1-i];
		    } else {
		    s = s + 256 + b[1-i];
		    }
		    s = s * 256;
		  }
		  if (b[0] >= 0) {
		    s = s + b[0];
		  } else {
		    s = s + 256 + b[0];
		  }
		  return s;
		} 
	/**
	 * 低字节数组转化long
	 * @date Aug 10, 2011 3:00:25 PM 
	 * @param b
	 * @return
	 * @author zhangh
	 */
	public static long lBytesToLong(byte[] b) {
		  long s = 0;
		  for (int i = 0; i < 3; i++) {
		    if (b[3-i] >= 0) {
		    s = s + b[3-i];
		    } else {
		    s = s + 256 + b[3-i];
		    }
		    s = s * 256;
		  }
		  if (b[0] >= 0) {
		    s = s + b[0];
		  } else {
		    s = s + 256 + b[0];
		  }
		  return s;
		} 
	/**
	 * 
	 * 方法名称:str2Bcd
	 * 方法描述: 字符串转化为bcd码
	 * @param asc
	 * @return
	 * Aug 17, 2011 
	 *
	 * @author<a href=zhanghuaup@126.com>张华</a>
	 * @version 1.0
	 *
	 */
	public static byte[] str2Bcd(String asc) {
	    int len = asc.length();
	    int mod = len % 2;

	    if (mod != 0) {
	     asc = "0" + asc;
	     len = asc.length();
	    }

	    byte abt[] = new byte[len];
	    if (len >= 2) {
	     len = len / 2;
	    }

	    byte bbt[] = new byte[len];
	    abt = asc.getBytes();
	    int j, k;

	    for (int p = 0; p < asc.length()/2; p++) {
	     if ( (abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
	      j = abt[2 * p] - '0';
	     } else if ( (abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
	      j = abt[2 * p] - 'a' + 0x0a;
	     } else {
	      j = abt[2 * p] - 'A' + 0x0a;
	     }

	     if ( (abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
	      k = abt[2 * p + 1] - '0';
	     } else if ( (abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
	      k = abt[2 * p + 1] - 'a' + 0x0a;
	     }else {
	      k = abt[2 * p + 1] - 'A' + 0x0a;
	     }

	     int a = (j << 4) + k;
	     byte b = (byte) a;
	     bbt[p] = b;
	    }
	    return bbt;
	}
	
	/**
	 * 在流中写入整形
	 * @date Aug 10, 2011 5:22:06 PM 
	 * @param input
	 * @return
	 * @author zhangh
	 */ 
	
	public static byte[] writeInt(int input){
		
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();   
	     DataOutputStream os = new DataOutputStream(baos); 
	     try {
	    	 os.writeInt(Integer.reverseBytes(input)); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	     byte[] b = baos.toByteArray();  
	     return b;
	}
	/**
	 * 
	 * @date Aug 11, 2011 5:23:06 PM 
	 * @param b
	 * @param cut
	 * @return
	 * @author zhangh
	 */
//	   public static byte[] byteCut(byte[] b, byte cut)
//       {
//           List<byte> list = new List<byte>();
//           list.AddRange(b);
//           for (int i = list.Count - 1; i >= 0; i--)
//           {
//               if (list[i] == cut)
//                   list.RemoveAt(i);
//           }
//           byte[] lastbyte = new byte[list.Count];
//           for (int i = 0; i < list.Count; i++)
//           {
//               lastbyte[i] = list[i];
//           }
//           return lastbyte;
//       }

	//去除数组0x00的值
	public static byte[] byteCut(byte[] b) {
		int count = 0;
		for (int i = 0; i < b.length; i++) {
			if (0x00 != b[i]) {
				count++;
			}
		}
		byte[] byteN = new byte[count];
		count = 0;
		for (int i = 0; i < b.length; i++) {
			if (0x00 != b[i]) {
				byteN[count] = b[i];
				count++;
			}
		}
		return byteN;
	}
	public synchronized static void out(){
		Constats.flag = Constats.flag + 1;
		System.out.println("第>>>>>"+Constats.flag+"<<<《《个");
	}
	public synchronized static void outOther(){
		Constats.flag1 = Constats.flag1 + 1;
		System.out.println("第>>>>>。。。。。"+Constats.flag1+"。。。。。<<<《《个");
	}
}
