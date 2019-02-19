package com.microsaturn.explorer.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO Server
 * @author saturn
 *
 */
public final class BIOServerNormal {

	// 默认的端口号
	private static int DEFAULT_PORT = 2030;
	
	private static ServerSocket serverSocket;
	
	public static void start() throws IOException {
		start(DEFAULT_PORT);
	}
	
	public synchronized static void start(int port) throws IOException {
		if(serverSocket != null) return;
		
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("服务器已启动, 端口号:" + port);
			while(true) {
				Socket socket = serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		}  finally {
			if(serverSocket != null) {
				System.out.println("服务器已关闭.");
				serverSocket.close();
				serverSocket = null;
			}
		}
	}
}
