package com.microsaturn.explorer.io.nio.chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class ChatServer {

	// Default IP
	private final static String DEFAULT_IP = "127.0.0.1";
	// Default Port
	private final static int DEFAULT_PORT = 2030;
	
	private volatile boolean started = false;
	
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	
	private ChatServer(String ip, int port) {
		try {
			// 1.create Selector.
			selector = Selector.open();
			// 2.open server socket channel.
			serverSocketChannel = ServerSocketChannel.open();
			// 3.be placed non-blocking mode.
			serverSocketChannel.configureBlocking(false);
			
			// 4.Binds the ServerSocket to a specific address (IP address and port number),and requested maximum length of the queue of incoming connections.
			serverSocketChannel.socket().bind(new InetSocketAddress(ip, port), 1024);
			// 5.Registers socket-connect operations with the given selector.
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			
			started = true;
			System.out.println("Chat Server already start, and the port is " + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void start() {
		ChatServer chatServer = new ChatServer(DEFAULT_IP, DEFAULT_PORT);
	}
}
