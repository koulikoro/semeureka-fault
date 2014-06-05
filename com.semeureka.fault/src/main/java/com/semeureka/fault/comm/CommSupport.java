package com.semeureka.fault.comm;

import gnu.io.CommPortIdentifier;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.rmi.ConnectException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.semeureka.frame.misc.IoBuffers;

@Component
@DependsOn("libraryPathRegister")
public class CommSupport {
	public static final Logger logger = LoggerFactory.getLogger(CommSupport.class);
	private IoAcceptor server = new NioSocketAcceptor();
	private IoConnector client = new NioSocketConnector();
	private IoConnector serial = new SerialConnector();
	private boolean serverStart;
	private boolean clientStart;
	private boolean serialStart;
	private Integer project;
	@Autowired
	@Qualifier("serverHandler")
	private IoHandler serverHandler;
	@Autowired
	@Qualifier("clientHandler")
	private IoHandler clientHandler;

	@Value("${fault.server.address}")
	public void setServerAddress(SocketAddress serverAddress) {
		if (!server.isActive()) {
			this.server.setDefaultLocalAddress(serverAddress);
		}
	}

	@Value("${fault.client.address}")
	public void setClientAddress(SocketAddress clientAddress) {
		if (!client.isActive()) {
			this.client.setDefaultRemoteAddress(clientAddress);
		}
	}

	@Value("${fault.serial.address}")
	public void setSerialAddress(SocketAddress serialAddress) {
		this.serial.setDefaultRemoteAddress(serialAddress);
	}

	public boolean isServerStart() {
		return serverStart;
	}

	@Value("${fault.server.start}")
	public void setServerStart(boolean serverStart) {
		this.serverStart = serverStart;
	}

	public boolean isClientStart() {
		return clientStart;
	}

	@Value("${fault.client.start}")
	public void setClientStart(boolean clientStart) {
		this.clientStart = clientStart;
	}

	public boolean isSerialStart() {
		return serialStart;
	}

	@Value("${fault.serial.start}")
	public void setSerialStart(boolean serialStart) {
		this.serialStart = serialStart;
	}

	public Integer getProject() {
		return project;
	}

	@Value("${fault.client.project}")
	public void setProject(Integer project) {
		this.project = project;
	}

	public IoAcceptor getServer() {
		return server;
	}

	public IoConnector getClient() {
		return client;
	}

	public IoConnector getSerial() {
		return serial;
	}

	@PostConstruct
	public synchronized void toggleServer() throws IOException {
		if (serverStart && !server.isActive()) {
			server.setHandler(serverHandler);
			server.bind();
			logger.info("Start server {} success", server.getDefaultLocalAddress());
		} else if (!serverStart && server.isActive()) {
			server.unbind();
			logger.info("Stop server {} success", server.getDefaultLocalAddress());
		}
	}

	@PostConstruct
	public synchronized void toggleClient() throws IOException {
		if (clientStart && !client.isActive()) {
			client.setHandler(clientHandler);
			ConnectFuture connect = client.connect().awaitUninterruptibly();
			if (!connect.isConnected()) {
				throw new ConnectException("Connection timed out: connect");
			} else {
				connect.getSession().write(projectIdentity(project));
			}
			logger.info("Start client {} success", client.getDefaultRemoteAddress());
		} else if (!clientStart && client.isActive()) {
			Iterator<IoSession> iterator = client.getManagedSessions().values().iterator();
			while (iterator.hasNext()) {
				iterator.next().close(true);
			}
			logger.info("Stop client {} success", client.getDefaultRemoteAddress());
		}
	}

	private IoBuffer projectIdentity(Integer project) {
		String clientIp = "127.0.0.1";
		try {
			clientIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		IoBuffer buffer = IoBuffer.allocate(255);
		buffer.putUnsignedShort(0x0C1C);
		buffer.putUnsignedShort(clientIp.length());
		buffer.put(clientIp.getBytes());
		buffer.putUnsignedShort(0x0C1C);
		buffer.putUnsignedShort(0x0001);
		buffer.putUnsignedShort(project.toString().length());
		buffer.put(project.toString().getBytes());
		buffer.putUnsignedShort(IoBuffers.crc16(buffer, 0, buffer.position()));
		return buffer.flip();
	}

	@PostConstruct
	public synchronized void toggleSerial() throws IOException {
		if (serialStart && !serial.isActive()) {
			serial.setHandler(new CommHandler());
			ConnectFuture connect = serial.connect().awaitUninterruptibly();
			if (!connect.isConnected()) {
				throw new ConnectException("Connection timed out: connect");
			}
			logger.info("Start serial {} success", serial.getDefaultRemoteAddress());
		} else if (!serialStart && serial.isActive()) {
			Iterator<IoSession> iterator = serial.getManagedSessions().values().iterator();
			while (iterator.hasNext()) {
				iterator.next().close(true);
			}
			logger.info("Stop serial {} success", serial.getDefaultRemoteAddress());
		}
	}

	@PreDestroy
	public synchronized void stopAll() {
		server.unbind();
		Iterator<IoSession> iterator1 = client.getManagedSessions().values().iterator();
		while (iterator1.hasNext()) {
			iterator1.next().close(true);
		}
		Iterator<IoSession> iterator2 = serial.getManagedSessions().values().iterator();
		while (iterator2.hasNext()) {
			iterator2.next().close(true);
		}
	}

	public List<CommPortIdentifier> getCommPorts() {
		List<CommPortIdentifier> CommPorts = new LinkedList<CommPortIdentifier>();
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			CommPorts.add((CommPortIdentifier) portList.nextElement());
		}
		return CommPorts;
	}

	@Scheduled(initialDelay = 60000, fixedDelay = 60000)
	public synchronized void checkAll() {
		if (serverStart && !server.isActive()) {
			try {
				toggleServer();
			} catch (IOException e) {
				logger.error("restart server {} failed", server, e);
			}
		} else if (clientStart && !client.isActive()) {
			try {
				toggleClient();
			} catch (IOException e) {
				logger.error("restart client {} failed", server, e);
			}
		} else if (serialStart && !serial.isActive()) {
			try {
				toggleSerial();
			} catch (IOException e) {
				logger.error("restart serial {} failed", server, e);
			}
		}
	}
}
