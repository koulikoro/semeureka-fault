package com.semeureka.fault.mina;

import gnu.io.CommPortIdentifier;

import java.io.IOException;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

@Component
@DependsOn("libraryPathRegister")
public class MinaService {
	private IoAcceptor server = new NioSocketAcceptor();
	private IoConnector client = new NioSocketConnector();
	private IoConnector serial = new SerialConnector();
	private SocketAddress serverAddress;
	private SocketAddress clientAddress;
	private SocketAddress serialAddress;
	private boolean serverStart;
	private boolean clientStart;
	private boolean serialStart;

	public SocketAddress getServerAddress() {
		return serverAddress;
	}

	@Value("${fault.server.address}")
	public void setServerAddress(SocketAddress serverAddress) {
		this.server.setDefaultLocalAddress(serverAddress);
		this.serverAddress = serverAddress;
	}

	public SocketAddress getClientAddress() {
		return clientAddress;
	}

	@Value("${fault.client.address}")
	public void setClientAddress(SocketAddress clientAddress) {
		this.client.setDefaultRemoteAddress(clientAddress);
		this.clientAddress = clientAddress;
	}

	public SocketAddress getSerialAddress() {
		return serialAddress;
	}

	@Value("${fault.serial.address}")
	public void setSerialAddress(SocketAddress serialAddress) {
		this.serial.setDefaultRemoteAddress(serialAddress);
		this.serialAddress = serialAddress;
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
	public void toggleServer() throws IOException {
		if (serverStart && !server.isActive()) {
			server.setHandler(new IoHandlerAdapter()); // TODO
			server.setDefaultLocalAddress(serverAddress);
			server.bind();
		} else {
			server.unbind();
		}
	}

	@PostConstruct
	public void toggleClient() throws IOException {
		if (clientStart && !client.isActive()) {
			client.setHandler(new IoHandlerAdapter()); // TODO
			client.setDefaultRemoteAddress(clientAddress);
			client.connect().awaitUninterruptibly();
		} else {
			Iterator<IoSession> iterator = client.getManagedSessions().values().iterator();
			while (iterator.hasNext()) {
				iterator.next().close(true);
			}
		}
	}

	@PostConstruct
	public void toggleSerial() throws IOException {
		if (serialStart && !serial.isActive()) {
			serial.setHandler(new IoHandlerAdapter()); // TODO
			serial.setDefaultRemoteAddress(serialAddress);
			serial.connect().awaitUninterruptibly();
		} else {
			Iterator<IoSession> iterator = serial.getManagedSessions().values().iterator();
			while (iterator.hasNext()) {
				iterator.next().close(true);
			}
		}
	}

	@PreDestroy
	public void stopAll() {
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
}
