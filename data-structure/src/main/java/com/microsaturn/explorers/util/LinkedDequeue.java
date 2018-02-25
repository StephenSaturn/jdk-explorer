package com.microsaturn.explorers.util;

/**
 * Double-ended Queue(双端队列):前端和后端都支持插入和删除操作
 * 基于DLNode(双向链表)
 * @author saturn
 *
 * @param <E>
 */
public class LinkedDequeue<E> {

	private int size;
	private DLNode<E> header;// 队列头
	private DLNode<E> trailer;// 队列尾
	
	public LinkedDequeue() {
		header = new DLNode<E>();
		trailer = new DLNode<E>(header, null, null);
		header.setNext(trailer);
		size = 0;
	}
	
	private static class DLNode<E> {
		private E data; //数据对象
		private DLNode<E> prev; // 前驱节点
		private DLNode<E> next; // 后继节点
		
		public DLNode() {
			this(null, null, null);
		}
		
		DLNode(DLNode<E> prev, E element, DLNode<E> next) {
			this.data = element;
			this.prev = prev;
			this.next = next;
		}
		
		public E get() {
			return data;
		}
		
		public E set(E newVal) {
			E oldVal = data;
			data = newVal;
			return oldVal;
		}

		public DLNode<E> getPrev() {
			return prev;
		}

		public void setPrev(DLNode<E> prev) {
			this.prev = prev;
		}

		public DLNode<E> getNext() {
			return next;
		}

		public void setNext(DLNode<E> next) {
			this.next = next;
		}
	}
	
	// 前端插入
	public void addFirst(E e) {
		DLNode<E> newNode = new DLNode<E>(header, e, header.getNext());
		header.setNext(newNode);
		newNode.next.setPrev(newNode);
		size ++;
	}
	
	// 后端插入
	public void addLast(E e) {
		DLNode<E> newNode = new DLNode<E>(trailer.prev, e, trailer);
		trailer.setPrev(newNode);
		newNode.prev.setNext(newNode);
		size ++;
	}
	
	// 前端删除
	public E removeFirst() {
		if(isEmpty()) 
			throw new RuntimeException("queue is empty.");
		DLNode<E> oldNode = header.next;
		header.setNext(oldNode.next);
		oldNode.next.setPrev(header);
		oldNode.setNext(null);
		oldNode.setPrev(null);
		size --;
		return oldNode.get();
	}
	
	// 后端删除
	public E removeLast() {
		if(isEmpty()) 
			throw new RuntimeException("queue is empty.");
		DLNode<E> oldNode = trailer.prev;
		trailer.setPrev(oldNode.prev);
		oldNode.prev.setNext(trailer);
		oldNode.setNext(null);
		oldNode.setPrev(null);
		size --;
		return oldNode.get();
	}
	
	// 返回队列首元素
	public E first() {
		if(isEmpty()) 
			throw new RuntimeException("queue is empty.");
		return header.next.get();
	}
	
	// 返回队列尾元素
	public E last() {
		if(isEmpty()) 
			throw new RuntimeException("queue is empty.");
		return trailer.prev.get();
	}
	
	// 返回队列中元素个数
	public int size() {
		return size;
	}
	
	// 判空
	public boolean isEmpty() {
		return size() == 0;
	}	
}
