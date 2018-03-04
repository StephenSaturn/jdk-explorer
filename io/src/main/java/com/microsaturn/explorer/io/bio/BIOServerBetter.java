package com.microsaturn.explorer.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServerBetter {

	// 默认的端口号
	private static int DEFAULT_PORT = 2030;

	private static ServerSocket serverSocket;

	public static void start() throws IOException {
		start(DEFAULT_PORT);
	}

	public synchronized static void start(int port) throws IOException {
		if (serverSocket != null)
			return;
		ExecutorService service = Executors.newFixedThreadPool(5);
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("服务器已启动, 端口号:" + port);
			while (true) {
				Socket socket = serverSocket.accept();
				service.execute(new ServerHandler(socket));
			}
		} finally {
			if (serverSocket != null) {
				System.out.println("服务器已关闭.");
				serverSocket.close();
				serverSocket = null;
			}
			service.shutdown();
		}
	}
}
