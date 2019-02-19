package com.microsaturn.explorer.net.keepalive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * C/S架构的客户端对象, 持有该对象, 可以随时向服务端发送消息.
 * @author Saturn
 *
 */
public class KeepAliveClient {

	private String ip;
	private int port;
	private Socket socket;
	private boolean started = false;// 连接状态
	
	private ConcurrentHashMap<Class, ObjectAction> actionMapping = new ConcurrentHashMap<>();
	
	private long lastSendTime;// 最后一次发送数据的时间
	
	public KeepAliveClient(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}
	
	public void start() throws IOException {
		if(started)
			return;
		socket = new Socket(ip, port);
		System.out.println("KeepAlive Client Port:" + socket.getPort());
		lastSendTime = System.currentTimeMillis();
		started = true;
		new Thread(new KeepAliveWatch()).start();  // 保持长连接的线程, 每隔2秒向服务器发一个保持连接的心跳消息  
	    new Thread(new ReceiveWatch()).start();    // 接受消息的线程, 处理消息  
	} 
	
	public void stop() {
		if(started)
			started = false;
	}
	  
    public void addActionMap(Class<Object> cls,ObjectAction action){  
        actionMapping.put(cls, action);  
    }  
  
    public void sendObject(Object obj) throws IOException {  
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
        oos.writeObject(obj);  
        System.out.println("send:" + obj);  
        oos.flush();  
    } 
	
	class KeepAliveWatch implements Runnable {
		long checkDelay = 10;  
        long keepAliveDelay = 2000;  
        public void run() {  
            while(started){  
                if(System.currentTimeMillis() - lastSendTime > keepAliveDelay){  
                    try {  
                        KeepAliveClient.this.sendObject(new KeepAlive());  
                    } catch (IOException e) {  
                        e.printStackTrace();  
                        KeepAliveClient.this.stop();  
                    }  
                    lastSendTime = System.currentTimeMillis();  
                } else {  
                    try {  
                        Thread.sleep(checkDelay);  
                    } catch (InterruptedException e) {  
                        e.printStackTrace();  
                        KeepAliveClient.this.stop();  
                    }  
                }  
            }  
        }  
	}
	
	class ReceiveWatch implements Runnable {
		@Override
		public void run() {
			while(started) {
				try {
					InputStream in = socket.getInputStream();
					if(in.available() > 0) {
						ObjectInputStream ois = new ObjectInputStream(in);  
                        Object obj = ois.readObject();  
                        System.out.println("receive:" + obj);  
                        ObjectAction oa = actionMapping.get(obj.getClass());  
                        oa = oa== null ? new DefaultObjectAction(): oa;  
                        oa.doAction(obj, KeepAliveClient.this);  
					} else {
						Thread.sleep(10);
					}
				} catch(Exception e) {
					e.printStackTrace();
					System.exit(1);
				} 
			} 
		}
	}
	
	// 处理服务器端发回的对象, 可实现该接口
	public static interface ObjectAction {
		void doAction(Object obj, KeepAliveClient client);
	}
	
	public static final class DefaultObjectAction implements ObjectAction {

		@Override
		public void doAction(Object obj, KeepAliveClient client) {
			System.out.println("handle :" + obj.toString());
		}
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {  
        String serverIp = "127.0.0.1";  
        int port = 65432;  
        KeepAliveClient client = new KeepAliveClient(serverIp, port);  
        client.start();  
    } 
}
