package com.zenchn.util;


public class ByteUtil {

	/** 标识位 **/
	public static final byte FLAG_BIT = 0X7E;
	/** 标识位+消息头的长度 **/
	public static final int FLAG_BIT_HEAD_LENGTH = 13;
	/** 校验码+标识位长度 **/
	public static final int FLAG_BIT_CHECK_LENGTH = 2;
	/** 消息头长度 **/
	public static final int TITLE_CHAR_LEN = 12;
	/**鉴权码**/
	public static String REPLY_TOKEN = "1234567890Z";
	
	/**终端注册**/
	public final static int REGISTER = 0X0100;
	/**终端注册应答**/
	public final static int REGISTER_ANSWER = 0X8100;
	/**终端注册结果0：成功；1：车辆已被注册；2：数据库中无该车辆；3：终端已被注册；4：数据库中无该终端**/
	public final static byte REGISTER_RESULT = 00;
	
	/**
	 * 2个数组组合
	 * 
	 * @date Aug 10, 2011 10:19:27 AM
	 * @param no
	 * @param dateb
	 * @return
	 * @author zhangh
	 */
	public static byte[] plus2Bytes(byte no[], byte dateb[]) {
		if (null == no || null == dateb) {
			return null;
		}
		byte all[] = new byte[no.length + dateb.length];

		for (int j = 0; j < no.length; j++) {
			all[j] = no[j];
		}
		for (int k = no.length; k < all.length; k++) {
			all[k] = dateb[k - no.length];
		}
		return all;
	}

	/**
	 * 计算校验码(校验码指从消息头开始，同后一字节异或，直到校验码前一个字节，占用一个字节。)
	 * @param datas
	 * @return
	 */
	public static byte checkCode(byte[] datas,int start,int end){
	    byte temp = datas[1];
	    for (int i = start; i < end; i++) {
	        temp ^= datas[i];
	    }
	    return temp;
	}

	/**
	 * 16进制的字符串表示转成字节数组
	 *
	 * @param hexString 16进制格式的字符串            
	 * @return 转换后的字节数组
	 **/
	public static byte[] toByteArray(String hexString) {
	    if (hexString == null || "".equals(hexString))
	       return null;

	    hexString = hexString.toLowerCase();
	    final byte[] byteArray = new byte[hexString.length() / 2];
	    int k = 0;
	    for (int i = 0; i < byteArray.length; i++) {//因为是16进制，最多只会占用4位，转换成字节需要两个16进制的字符，高位在先
	        byte high = (byte) (Character.digit(hexString.charAt(k), 16) & 0xff);
	        byte low = (byte) (Character.digit(hexString.charAt(k + 1), 16) & 0xff);
	        byteArray[i] = (byte) (high << 4 | low);
	        k += 2;
	    }
	    return byteArray;
	}
	
	/**
	 * 字节数组转成16进制表示格式的字符串
	 *
	 * @param byteArray 要转换的字节数组
	 * @return 16进制表示格式的字符串
	 **/
	public static String toHexString(byte[] byteArray) {
	    if (byteArray == null || byteArray.length < 1)
	      return ""; 

	    final StringBuilder hexString = new StringBuilder();
	    for (int i = 0; i < byteArray.length; i++) {
	        if ((byteArray[i] & 0xff) < 0x10)//0~F前面不零
	            hexString.append("0");
	        hexString.append(Integer.toHexString(0xFF & byteArray[i]));
	    }
	    return hexString.toString().toUpperCase();
	}
	
	
}
