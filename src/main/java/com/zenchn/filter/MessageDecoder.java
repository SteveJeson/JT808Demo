package com.zenchn.filter;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.zenchn.util.ByteUtil;

public class MessageDecoder extends CumulativeProtocolDecoder {
	
	private static Logger logger = Logger.getLogger(MessageDecoder.class); 
	
	@Override
	protected boolean doDecode(IoSession session, IoBuffer buffer,
			ProtocolDecoderOutput out) throws Exception {
        // 验证数据
        if (buffer.hasRemaining()) {
        	String msg = buffer.getString(Charset.forName("GBK").newDecoder());
    		if(msg.indexOf(" ") > -1){
    			msg = msg.replaceAll(" +","");
    		}
    		byte[] msgByte = ByteUtil.toByteArray(msg);
            if (ByteUtil.FLAG_BIT == msgByte[0] && ByteUtil.FLAG_BIT == msgByte[msgByte.length-1]) {//校验头尾的标识位
            	//验证消息长度 
            	int lengthB = msgByte[4];//消息体长度
            	if(lengthB == (msgByte.length-ByteUtil.FLAG_BIT_HEAD_LENGTH-ByteUtil.FLAG_BIT_CHECK_LENGTH)){
            		//验证校验码
            		byte code = ByteUtil.checkCode(msgByte,2,msgByte.length-2);
            		if(code == msgByte[msgByte.length-2]){
     					out.write(msgByte);
     					return true;
            		}else{
            			logger.info("校验码不正确："+msg);
            			return false;
            		}
            	}else{
        			logger.info("消息体长度不正确："+msg);
        			return false;
        		}
            }else{
    			logger.info("消息头尾标识位不正确："+msg);
    			return false;
    		}
        }
        return false;
	}
}
