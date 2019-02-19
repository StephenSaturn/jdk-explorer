package com.microsaturn.explorer.io.keepalive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class KeepAliveServer {

	private int port;
	private volatile boolean started = false;
	private long receiveTimeDelay = 3000;
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<Class, ObjectAction>();
	private Thread connWatchDog;
	
	public KeepAliveServer(int port) {  
        this.port = port;  
    }
	
	public void start() {
		if (started)
			return;
		started = true;
		connWatchDog = new Thread(new ConnWatchDog());
		connWatchDog.start();
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		if (started)
			started = false;
		if (connWatchDog != null)
			connWatchDog.stop();
	}
	
	public interface ObjectAction {
		Object doAction(Object rev, KeepAliveServer server);
	}

	public static final class DefaultObjectAction implements ObjectAction {
		public Object doAction(Object rev, KeepAliveServer server) {
			System.out.println("handle and return:" + rev);
			return rev;
		}
	}
	
	public void addActionMap(Class<Object> cls, ObjectAction action) {
		actionMapping.put(cls, action);
	}

	class ConnWatchDog implements Runnable {
		public void run() {
			try {
				ServerSocket ss = new ServerSocket(port, 5);
				while (started) {
					Socket s = ss.accept();
					new Thread(new SocketAction(s)).start();
				}
			} catch (IOException e) {
				e.printStackTrace();
				KeepAliveServer.this.stop();
			}
		}
	}

	class SocketAction implements Runnable {
		Socket socket;
		boolean run = true;
		long lastReceiveTime = System.currentTimeMillis();

		public SocketAction(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			while (started && run) {
				if (System.currentTimeMillis() - lastReceiveTime > receiveTimeDelay) {
					overThis();
				} else {
					try {
						InputStream in = socket.getInputStream();
						if (in.available() > 0) {
							ObjectInputStream ois = new ObjectInputStream(in);
							Object obj = ois.readObject();
							lastReceiveTime = System.currentTimeMillis();
							System.out.println("receive:" + obj);
							ObjectAction oa = actionMapping.get(obj.getClass());
							oa = oa == null ? new DefaultObjectAction() : oa;
							Object out = oa.doAction(obj, KeepAliveServer.this);
							if (out != null) {
								ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
								oos.writeObject(out);
								oos.flush();
							}
						} else {
							Thread.sleep(10);
						}
					} catch (Exception e) {
						e.printStackTrace();
						overThis();
					}
				}
			}
		}

		private void overThis() {
			if (run)
				run = false;
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println("close:" + socket.getRemoteSocketAddress());
		}
	}
	
	public static void main(String[] args) {  
        int port = 65432;  
        KeepAliveServer server = new KeepAliveServer(port);  
        server.start();  
    } 
}
