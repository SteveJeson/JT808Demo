package com.zenchn.util;

import org.apache.mina.core.buffer.IoBuffer;

public class IoBufferTOString {
	public static String ioBufferToString(IoBuffer iobuffer){    
		       System.out.println("message = " + iobuffer + iobuffer.limit());    
		        iobuffer.flip();    //调换buffer当前位置，并将当前位置设置成0    
		       byte[] b = new byte[iobuffer.limit()];    
		       iobuffer.get(b);    
		        //此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。     
		        StringBuffer stringBuffer = new StringBuffer();    
		            
		        for(int i = 0; i < b.length; i++){    
		           System.out.println("====" + b[i]);    
		           stringBuffer.append((Byte) b[i]); //可以根据需要自己改变类型    
		           System.out.println(b[i] +"---------" +i);    
		        }    
		        return stringBuffer.toString();    
		    }   
}
