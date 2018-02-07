package com.microsaturn.explorers.util.concurrent;

public class MyRunnable implements Runnable {

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " run");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
