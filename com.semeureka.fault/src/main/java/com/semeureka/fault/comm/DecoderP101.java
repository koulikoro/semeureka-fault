package com.semeureka.fault.comm;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DecoderP101 extends MessageDecoderAdapter {
	public static final Logger logger = LoggerFactory.getLogger(DecoderP101.class);

	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		int head = in.getUnsigned();
		if (head == 0x10) { // 1、起始标志
			if (in.remaining() < 5) { // 3、报文长度
				return NEED_DATA;
			}
			byte crc = 0;
			for (int i = 0; i < 3; i++) {
				crc += in.get();
			}
			if (crc != in.get()) { // 4、加和校验
				return NOT_OK;
			}
			return in.getUnsigned() == 0x16 ? OK : NOT_OK; // 5、结束标志
		} else if (head == 0x68) { // 1、起始标志
			if (in.remaining() < 1) { // 2、长度位置
				return NEED_DATA;
			}
			int len = in.getUnsigned();
			if (in.remaining() < len + 4) { // 3、报文长度
				return NEED_DATA;
			}
			byte crc = 0;
			in.skip(2);
			for (int i = 0; i < len; i++) {
				crc += in.get();
			}
			if (crc != in.get()) { // 4、加和校验
				return NOT_OK;
			}
			return in.getUnsigned() == 0x16 ? OK : NOT_OK; // 5、结束标志
		}
		return NOT_OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int oldPos = in.position();
		int head = in.getUnsigned();
		int len = 6;
		byte[] linkAddr = new byte[2];
		if (head == 0x10) {
			in.skip(1).get(linkAddr);
		} else {
			len = in.getUnsigned() + 6;
			in.skip(3).get(linkAddr);
		}
		in.position(oldPos);
		byte[] content = new byte[len];
		in.get(content);
		out.write(content);
		return OK;
	}
}
