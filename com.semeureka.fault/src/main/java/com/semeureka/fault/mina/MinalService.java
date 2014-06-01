package com.semeureka.fault.mina;

import gnu.io.CommPortIdentifier;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("libraryPathRegister")
public class MinalService {
	public static final Logger logger = LoggerFactory.getLogger(MinalService.class);
	public static final int TIME_OUT = 6000;
	private IoAcceptor acceptor = new NioSocketAcceptor();
	private IoConnector connector = new NioSocketConnector();
	private IoConnector serial = new SerialConnector();

	public IoAcceptor getAcceptor() {
		return acceptor;
	}

	public IoConnector getConnector() {
		return connector;
	}

	public IoConnector getSerial() {
		return serial;
	}

	public List<CommPortIdentifier> getCommPorts() {
		List<CommPortIdentifier> CommPorts = new LinkedList<CommPortIdentifier>();
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			CommPorts.add((CommPortIdentifier) portList.nextElement());
		}
		return CommPorts;
	}

	@PostConstruct
	public void init() {
		acceptor.setHandler(new IoHandlerAdapter());
		connector.setHandler(new IoHandlerAdapter());
		serial.setHandler(new IoHandlerAdapter());
	}

	@PreDestroy
	public void destory() {
		stop(acceptor);
		stop(connector);
		stop(serial);
	}

	public void stop(IoService service) {
		if (service instanceof IoAcceptor) {
			((IoAcceptor) service).unbind();
		} else {
			Iterator<IoSession> iterator = service.getManagedSessions().values().iterator();
			while (iterator.hasNext()) {
				IoSession ioSession = iterator.next();
				ioSession.close(true);
			}
		}
	}

	public void start(IoService service, SocketAddress defaultAddress) throws IOException {
		if (service instanceof IoAcceptor) {
			((IoAcceptor) service).setDefaultLocalAddress(defaultAddress);
			((IoAcceptor) service).bind();
		} else {
			((IoConnector) service).setDefaultRemoteAddress(defaultAddress);
			ConnectFuture connect = ((IoConnector) service).connect();
			connect.awaitUninterruptibly(TIME_OUT);
			if (!connect.isConnected()) {
				throw new ConnectException("Connection timed out: connect");
			}
		}
	}

	public void toggle(IoService service, SocketAddress defaultAddress) throws IOException {
		try {
			if (service.isActive()) {
				stop(service);
			} else {
				start(service, defaultAddress);
			}
		} catch (IOException e) {
			logger.error("An exception occurred while operating {}", defaultAddress, e);
			throw e;
		}
	}
}
