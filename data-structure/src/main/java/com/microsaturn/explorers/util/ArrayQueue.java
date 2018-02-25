package com.microsaturn.explorers.util;

/**
 * 队列:基于数组队列(FIFO)
 * @author saturn
 *
 * @param <E>
 */
public class ArrayQueue<E> {

	private Object[] elementData;
	private int currentSize;// 队列当前元素个数
	private int maxSize;// 队列最大容量
	private int front;// 队列头, 允许删除
	private int rear;// 队列尾, 允许插入
	
	public ArrayQueue() {
		this(10);
	}

	public ArrayQueue(int initialCapacity) {
		maxSize = initialCapacity;
		elementData = new Object[maxSize];
		front = rear = currentSize = 0;
	}
	
	/**
	 * 入队
	 * @param e
	 * @return
	 */
	public boolean enqueue(E e) {
		if(currentSize >= maxSize)
			throw new RuntimeException("队列已满.");
		elementData[rear++] = e;
		currentSize ++;
		return true;
	}
	
	/**
	 * 循环队列
	 * @param e
	 * @return
	 */
	public boolean enqueueWithCircular(E e) {
		if(currentSize >= maxSize)
			throw new RuntimeException("队列已满.");
		elementData[rear] = e;
		rear = (++rear) % maxSize;//*********
		currentSize ++;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public E dequeue() {
		if(currentSize <= 0 ) {
			throw new RuntimeException("队列为空.");
		}
		E oldVal = (E)elementData[front];
		elementData[front++] = null;
		currentSize --;
		return oldVal;
	}
	
	
	@SuppressWarnings("unchecked")
	public E dequeueWithCircular() {
		if(currentSize <= 0 ) {
			throw new RuntimeException("队列为空.");
		}
		E oldVal = (E)elementData[front];
		elementData[front] = null;
		front = (++front) % maxSize;
		currentSize --;
		return oldVal;
	}
	
	public int size() {
		return currentSize;
	}
}
