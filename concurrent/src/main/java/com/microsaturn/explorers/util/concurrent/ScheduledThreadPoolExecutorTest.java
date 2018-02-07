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
 * 
 * public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit)
 * 
 */
public class ScheduledThreadPoolExecutorTest {

	private static ScheduledThreadPoolExecutor scheduleThreadPoolExecutor;
	private static Runnable task;
	
	/**
	 * public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit)
	 */
	public static void testSchedule() {
		scheduleThreadPoolExecutor.schedule(new TaskThread(), 5, TimeUnit.SECONDS);
		scheduleThreadPoolExecutor.schedule(new TaskThread(), 5, TimeUnit.SECONDS);
	}
	
	/**
	 * public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
	 * startTime = lastStartTime + delay
	 */
	public static void testScheduleAtFixedRate() {
		task = initTask();
		
		scheduleThreadPoolExecutor.scheduleAtFixedRate(task, 15, 30, TimeUnit.SECONDS);
	}
	
	/**
	 * public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
	 * startTime = lastEndTime + delay
	 */
	public static void testScheduleWithFixedDelay() {
		task = initTask();
		
		scheduleThreadPoolExecutor.scheduleWithFixedDelay(task, 15, 30, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		System.out.println("======================== Start test ScheduledThreadPoolExecutor ========================");
		before();
		
		testSchedule();
		
		//testScheduleAtFixedRate();
		//testScheduleWithFixedDelay();
		
		after();
	}
	
	private static class TaskThread implements Runnable {
		@Override
		public void run() {
			sleep(TimeUnit.SECONDS, 2);
			print(Thread.currentThread().getName() + " run");
		}
	}
	
	private static void before() {
		scheduleThreadPoolExecutor = new ScheduledThreadPoolExecutor(2);
	}
	
	private static void after() {
		sleep(TimeUnit.SECONDS, 150);
		if(scheduleThreadPoolExecutor != null) {
			scheduleThreadPoolExecutor.shutdown();
		}
	}
	
	private static Runnable initTask() {
		long start = System.currentTimeMillis();
		return () -> {
			print("start task: " + getPeriod(start, System.currentTimeMillis())); 
			sleep(TimeUnit.SECONDS, 10); 
			print("end task: " + getPeriod(start, System.currentTimeMillis())); 
		};
	}
	
	private static void sleep(TimeUnit unit, long time) { 
		try { 
			unit.sleep(time); 
		} catch (InterruptedException e) { 
			e.printStackTrace(); 
		} 
	} 

	private static int getPeriod(long start, long end) { 
		return (int)(end - start) / 1000; 
	}

	private static void print(String msg) { 
		System.out.println(msg); 
	} 
}
