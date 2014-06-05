package com.semeureka.fault.comm;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilter.NextFilter;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.DemuxingProtocolDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommHandler extends Observable implements IoHandler {
	public static final Logger logger = LoggerFactory.getLogger(CommHandler.class);
	private DemuxingProtocolDecoder protocolDecoder = new DemuxingProtocolDecoder();
	private ProtocolDecoderOutput out = new ProtocolDecoderOutputImpl();

	public CommHandler() {
		setChanged();
	}

	@Override
	protected synchronized void clearChanged() {
		// Do nothing
	}

	public void setObservers(List<Observer> observers) {
		for (Observer observer : observers) {
			addObserver(observer);
		}
	}

	public void setMessageDecoders(List<MessageDecoder> messageDecoders) {
		for (MessageDecoder messageDecoder : messageDecoders) {
			protocolDecoder.addMessageDecoder(messageDecoder);
		}
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// Do nothing
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// Do nothing
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// Do nothing
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		// Do nothing
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("An exception occurred while processing {} message", session, cause);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO
		logger.debug("Sent message: {}, session: {}", message, session);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		// TODO
		logger.debug("Received message: {}, session: {}", message, session);
		if (message instanceof IoBuffer) {
			IoBuffer in = (IoBuffer) message;
			protocolDecoder.decode(session, in, out);
		} else {
			notifyObservers(message);
		}
	}

	private final class ProtocolDecoderOutputImpl implements ProtocolDecoderOutput {
		@Override
		public void write(Object message) {
			notifyObservers(message);
		}

		@Override
		public void flush(NextFilter nextFilter, IoSession session) {
			// Do nothing
		}
	}
}
