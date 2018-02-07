package com.microsaturn.explorers.util.concurrent;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Saturn
 * 
 * SynchronousQueue是一个没有数据缓冲的BlockingQueue(队列中只能存储一个元素).
 * 生产者线程对其的插入操作put必须等待消费者的移除操作take, 反过来也一样, 消费者移除数据操作必须等待生产者的插入.
 * 
 * SynchronousQueue内部并没有数据缓存空间, 你不能调用peek()方法来看队列中是否有数据元素,
 * 因为数据元素只有当你试着取走的时候才可能存在, 不取走而只想偷窥一下是不行的, 当然遍历这个队列的操作也是不允许的.
 * 队列头元素是第一个排队要插入数据的线程, 而不是要交换的数据.
 * 数据是在配对的生产者和消费者线程之间直接传递的, 并不会将数据缓存到队列中.
 * 
 * 生产者和消费者互相等待对方, 握手, 然后一起离开.
 *
 */
public class SynchronousQueueTest {
	
	public static void getData() throws InterruptedException {
		SynchronousQueue<String> sc = new SynchronousQueue<String>();
		 
		//sc.take();// 没有元素则阻塞在此处, 等待其他线程向sc添加元素才会获取元素向下执行
		//sc.poll();// 没有元素不阻塞在此处直接返回null向下执行
		sc.poll(5, TimeUnit.SECONDS);// 没有元素阻塞在此处等待指定时间, 如果还是没有元素直接返回null向下执行
		
		System.out.println("test");
	}
	
	public static void saveData() throws InterruptedException {
		SynchronousQueue<String> sc = new SynchronousQueue<String>();
		
		//sc.put("Saturn");// 没有线程等待获取元素的话, 阻塞在此处, 直到有线程取走元素
		//sc.offer("Saturn");// 没有线程等待获取元素的话, 不阻塞在此处, 如果该元素已添加到此队列, 则返回 true, 否则返回 false
		sc.offer("Saturn", 5, TimeUnit.SECONDS); // 没有线程等待获取元素的话, 阻塞在此处等待5s, 如果该元素已添加到此队列, 则返回true, 否则返回 false  
		
		System.out.println("test");
	}
	
	/**
	 * take和put是阻塞的获取和存储元素的方法, poll和offer是不阻塞的获取元素和存储元素的方法, 并且poll和offer可以指定超时时间.
	 * take, poll和put, offer可以组合使用, 可以根据实际业务需求选择.
	 */
	
	public static void test() throws InterruptedException {
		Random random = new Random();
		SynchronousQueue<String> sc = new SynchronousQueue<String>();
		new Thread(() -> {
			while(true) {
				try {
					String str = "test ---- " + random.nextInt(100);
					//sc.put(str);// 将指定元素添加到此队列, 阻塞, 直到其他线程取走它.  
					//System.out.println("add '" + str + "' success.");
					
					// 如果另一个线程正在等待以接收元素, 则将指定元素插入到此队列.如果没有等待接受数据的线程则直接返回false  
					//System.out.println("add '" + str + "' success：" + sc.offer(str));
					
					// 如果没有等待的线程, 则等待5s.在等待时间还没有接受数据的线程的话, 直接返回false  
					System.out.println("add '" + str + "' success：" + sc.offer(str, 5, TimeUnit.SECONDS));
					
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		new Thread(() -> {
			while(true) {
				try {
					System.out.println("get data ::'" + sc.take() + "'.");
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void main(String[] args) throws InterruptedException {
		//getData();
		//saveData();
		test();
	}
}
