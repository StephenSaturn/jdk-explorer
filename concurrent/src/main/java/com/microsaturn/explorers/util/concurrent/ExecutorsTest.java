package com.microsaturn.explorers.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTest {

	/**
	 * 固定大小线程池
	 * 		new ThreadPoolExecutor(3, 3, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
	 * coresize和maxsize相同，超时时间为0, 队列用的是LinkedBlockingQueue无界的FIFO队列
	 * 表明线程池始终只有size的线程在运行, 
	 * @throws InterruptedException
	 */
	public static void testFixedThreadPool() throws InterruptedException {
		ExecutorService service = Executors.newFixedThreadPool(3);
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		
		Thread.sleep(30000);
		service.shutdown();
	}
	
	
	/**
	 * 无界线程池
	 * 		new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
	 * 
	 * SynchronousQueue队列, 一个不存储元素的阻塞队列.
	 * 每个插入操作必须等到另一个线程调用移除操作.所以, 当我们提交第一个任务的时候, 是加入不了队列的, 这就满足了, 一个线程池条件"当无法加入队列的时候, 且任务没有达到maxsize时, 我们将新开启一个线程任务".
	 * 所以我们的maxsize比较大, 时间是60s, 当一个线程没有任务执行会暂时保存60s超时时间, 如果没有的新的任务的话, 会从cache中remove掉.
	 * @throws InterruptedException
	 */
	public static void testCachedThreadPool() throws InterruptedException {
		ExecutorService service = Executors.newCachedThreadPool();
		
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		
		Thread.sleep(30000);
		service.shutdown();
	}
	
	/**
	 * 大小为1的固定线程池
	 * 		new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
	 * @throws InterruptedException
	 */
	public static void testSingleThread() throws InterruptedException {
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		service.execute(new MyRunnable());
		
		Thread.sleep(30000);
		service.shutdown();
	}
	
	public static void main(String[] args) throws InterruptedException {
		//testFixedThreadPool();
		
		//testCachedThreadPool();
		
		testSingleThread();
	}
}
