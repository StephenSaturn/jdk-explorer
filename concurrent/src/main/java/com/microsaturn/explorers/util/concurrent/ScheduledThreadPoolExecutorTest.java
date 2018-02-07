package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Saturn
 * 
 * ScheduledThreadPool:任务调度器, 使用线程池执行定时任务的类
 * 		1.使用多线程执行任务, 不用担心任务执行时间过长而导致任务相互阻塞的情况
 * 		2.任务执行过程中, 如果线程死掉, 会新建线程执行任务
 */
public class ScheduledThreadPoolExecutorTest {

	public static void main(String[] args) {
		System.out.println("================== start test ScheduledThreadPoolExecutor ==================");
		ScheduledThreadPoolExecutor scheduleThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(() -> {
			System.out.println("Task one ......................." + System.nanoTime());
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(() -> {
			System.out.println("Task two ......................." + System.nanoTime());
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(() -> {
			System.out.println("Task three ......................." + System.nanoTime());
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(() -> {
			System.out.println("Task four ......................." + System.nanoTime());
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		scheduleThreadPoolExecutor.scheduleAtFixedRate(new MyRunnable(), 1000, 5000, TimeUnit.MILLISECONDS);
		
		System.out.println("Main");
	}
}
