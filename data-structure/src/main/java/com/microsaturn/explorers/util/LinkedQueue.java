package com.microsaturn.explorers.util;

/**
 * 队列:基于单链表队列(FIFO)
 * @author saturn
 *
 * @param <E>
 */
public class LinkedQueue<E> {

	private int size;
	private Node<E> front;// 队列头, 允许删除
	private Node<E> rear;// 队列尾, 允许插入
	
	public LinkedQueue() {}
	
	private static class Node<E> {
		E data;
		Node<E> next;
		
		Node(E element, Node<E> next) {
			this.data = element;
			this.next = next;
		}
	} 
	
	/**
	 * 入队
	 * @param e
	 * @return
	 */
	public boolean enqueue(E e) {
		if(size == 0) {
			rear = front = new Node<E>(e, null);// 队列为空, 则front, rear 都指向该节点
		} else {
			Node<E> newNode = new Node<E>(e, null);
			rear.next = newNode;// 让尾节点的next指向新增的节点
			rear = newNode;// 以新节点作为尾节点
		}
		size ++;
		return true;
	}
	
	/**
	 * 出队
	 * @return
	 */
	public E dequeue() {
		if(isEmpty()) 
			throw new RuntimeException("Queue is empty.");
		E oldVal = front.data;// 获取当前front节点元素
		Node<E> newFrontNode = front.next;// 获取当前front节点next节点
		front.next = null;// 将当前front节点的next设为null
		front = newFrontNode;// 将newFrontNode节点设为新的front节点
		size --;
		return oldVal;
	}
	
	/**
	 * 查看队列头元素
	 * @return
	 */
	public E peek() {
		if(isEmpty()) 
			throw new RuntimeException("Queue is empty.");
		return front.data;
	}
	
	/**
	 * 判空
	 * @return
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return size;
	}
}
