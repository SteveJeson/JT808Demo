package com.zenchn.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;


public class MessageCodecFactory implements ProtocolCodecFactory {
	private final MessageEncoder encoder;
    private final MessageDecoder decoder;


	 public MessageCodecFactory() {
	        encoder = new MessageEncoder(); //±àÂëÆ÷
	        decoder = new MessageDecoder(); //½âÂëÆ÷
    }


	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return decoder;
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		return encoder;
	}

}
