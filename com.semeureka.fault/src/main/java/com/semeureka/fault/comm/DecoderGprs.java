package com.semeureka.fault.comm;

import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Rawdata;
import com.semeureka.fault.service.GroupService;

/**
 * 解析公司自定的故障GPRS协议，将字节流转化为用户自定义的对象。调用流程请参考{@link MessageDecoder}的说明。
 */
@Component
public class DecoderGprs extends MessageDecoderAdapter {
	public static final Logger logger = LoggerFactory.getLogger(DecoderGprs.class);
	@Autowired
	private GroupService groupService;

	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		int oldPos = in.position();
		if (in.getUnsigned() == 0x68) { // 1、起始标志
			if (in.remaining() < 10) { // 2、长度位置
				return NEED_DATA;
			}
			int len = in.skip(8).getUnsignedShort();
			if (in.remaining() < len + 2) { // 3、报文长度
				return NEED_DATA;
			}
			byte crc = 0;
			in.position(oldPos);
			for (int i = 0; i < len + 11; i++) {
				crc += in.get();
			}
			if (crc != in.get()) { // 4、加和校验
				// return NOT_OK;
			}
			return in.getUnsigned() == 0x16 ? OK : NOT_OK; // 5、结束标志
		}
		return NOT_OK;
	}

	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int head = in.position();
		byte[] hostCode = new byte[6];
		in.skip(1).get(hostCode);
		int len = in.skip(2).getUnsignedShort();
		byte[] content = new byte[len + 13];
		in.position(head).get(content);
		Group group = groupService.findByHostCode(hostCode);
		Rawdata rawdata = new Rawdata(content, group, Phase.M, new Date());
		out.write(rawdata);
		return OK;
	}
}
