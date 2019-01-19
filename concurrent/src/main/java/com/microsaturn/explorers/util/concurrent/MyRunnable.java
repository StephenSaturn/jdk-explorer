package com.microsaturn.explorers.util.concurrent;

public class MyRunnable implements Runnable {
	
	public MyRunnable() {
		
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(Thread.currentThread().getName() + " run");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
