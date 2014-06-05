package com.semeureka.fault.comm;

import java.util.Date;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoderAdapter;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.semeureka.fault.entity.Current;
import com.semeureka.fault.entity.Device;
import com.semeureka.fault.entity.Device.Phase;
import com.semeureka.fault.entity.Group;
import com.semeureka.fault.entity.Rawdata;
import com.semeureka.fault.entity.Voltage;
import com.semeureka.fault.service.DeviceService;
import com.semeureka.fault.service.GroupService;
import com.semeureka.frame.misc.ByteUtil;

@Component
public class DecoderGprs extends MessageDecoderAdapter {
	public static final Logger logger = LoggerFactory.getLogger(DecoderGprs.class);
	@Autowired
	private GroupService groupService;
	@Autowired
	private DeviceService deviceService;

	@Override
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
			int crc = ByteUtil.crc8(in, oldPos, len + 11);
			if (crc != in.skip(len).getUnsigned()) { // 4、加和校验
				return NOT_OK;
			}
			return in.getUnsigned() == 0x16 ? OK : NOT_OK; // 5、结束标志
		}
		return NOT_OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		int oldPos = in.position();
		byte[] hostCode = ByteUtil.copyBytes(in.skip(1), 6);
		int len = in.skip(2).getUnsignedShort();
		byte[] content = ByteUtil.copyBytes(in.position(oldPos), len + 13);
		Group group = groupService.findByHostCode(hostCode);
		Rawdata rawdata = new Rawdata(content, group, Phase.M, new Date());
		out.write(rawdata);
		decodeRawdata(session, rawdata, out);
		return OK; // 返回前一定要消耗掉完整报文
	}

	public void decodeRawdata(IoSession session, Rawdata rawdata, ProtocolDecoderOutput out) {
		if (rawdata.getContent().length > 25) { // 非注册报文
			IoBuffer in = IoBuffer.wrap(rawdata.getContent());
			in.skip(23); // 跳过报头
			while (in.remaining() >= 7) { // 最小报文内容长度 + 校验 + 结束标志
				byte[] code = ByteUtil.copyBytes(in, 2);
				int type = in.getUnsigned(); // 数据类型
				Device device = deviceService.findByCode(code);
				switch (type) {
				case 0xDA: {
					Integer value = in.getUnsignedShort();
					out.write(new Voltage(value, device, rawdata.getCreateTime()));
					break;
				}
				case 0x90: {
					Integer value = 7;
					for (int i = 0; i < 8; i++) {
						value += in.getUnsignedShort();
					}
					Integer temperature = in.getUnsignedShort();
					out.write(new Current(value >> 3, temperature, device, rawdata.getCreateTime()));
					break;
				}
				default:
					logger.warn("未知的数据类型: {}, 导致不能推测后面数据格式: {}", type, in);
					in.skip(in.remaining());
					break;
				}
			}
		}
	}
}
