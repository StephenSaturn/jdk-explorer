package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.CountDownLatch;

public class MyRunnable implements Runnable {

	private CountDownLatch counter; 
	
	public MyRunnable() {
		
	}
	
	public MyRunnable(CountDownLatch counter) {
		this.counter = counter;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			counter.countDown();
			System.out.println(Thread.currentThread().getName() + " run");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
