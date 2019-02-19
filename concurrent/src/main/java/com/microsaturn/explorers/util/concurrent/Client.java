package com.microsaturn.explorers.util.concurrent;

public class Client implements Runnable {

	private MyThread myThread = new MyThread();

	public static void main(String[] args)  {
		Client c1 = new Client();
		Thread t1 = new Thread(c1);
		Thread t2 = new Thread(c1);
		t1.start();
		t2.start();
	}

	@Override
	public void run() {
		myThread.add();
	}
}

class MyThread {

	private static int num = 0;

	public synchronized void add() {
		num++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "是第" + num + "次跳用");
	}
}