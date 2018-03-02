package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		CountDownLatch counter = new CountDownLatch(3);
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(new MyRunnable(counter));
		service.execute(new MyRunnable(counter));
		service.execute(new MyRunnable(counter));
		
		counter.await();
		System.out.println("System will shutdown.");
		service.shutdown();
	}
}
