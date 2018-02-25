package com.microsaturn.explorers.util;

/**
 * 链表
 * @author saturn
 *
 * @param <E>
 */
public class LinkedList<E> {

	private int size;
	private Node<E> beginMarker;// 链表始端标记
	private Node<E> endMarker;//链表末端标记
	
	public LinkedList() {
		beginMarker = new Node<E>(null, null, null);
		endMarker = new Node<E>(beginMarker, null, null);
		beginMarker.next = endMarker;
		size = 0;
	}

	private static class Node<E> {
		E data;
		Node<E> prev;
		Node<E> next;
		
		Node(Node<E> prev, E element, Node<E> next) {
			this.data = element;
			this.prev = prev;
			this.next = next;
		}
	}
	
	/**
	 * 新增节点在指定节点p的前面
	 * @param p
	 * @param e
	 */
	private void addBefore(Node<E> p, E e) {
		Node<E> newNode = new Node<E>(p.prev, e, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size ++;
	}
	
	/**
	 * 添加元素至链表的末端
	 * @param e
	 */
	private void addEnd(E e) {
		Node<E> newNode = new Node<E>(endMarker.prev, e, endMarker);
		endMarker.prev.next = newNode;
		endMarker.prev = newNode;
		size ++;
	}
	
	/**
	 * 移除节点
	 * @param p
	 * @return
	 */
	private E remove(Node<E> p) {
		p.prev.next = p.next;
		p.next.prev = p.prev;
		size --;
		return p.data;
	}
	
	/**
	 * 获取指定index处的节点
	 * @param index
	 * @return
	 */
	public Node<E> getNode(int index) {
		return getNode(index, 0, size - 1);
	}
	
	private Node<E> getNode(int index, int lower, int upper) {
		if(index < 0 || index > upper)
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);  
		Node<E> p;
		if(index < size / 2) {
			p = beginMarker.next;
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
		} else {
			p = endMarker.prev;
			for(int i = size - 1; i > index; i--) {
				p = p.prev;
			}
		}
		return p;
	}
	
	/**
	 * =====================以上是链表的核心部分=====================
	 */
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public boolean add(E e) {
		addEnd(e);
		//add(size(), e);
		return true;
	}
	
	public void add(int index, E e) {
		addBefore(getNode(index, 0, size()), e);
	}
	
	public E get(int index) {
		return getNode(index).data;
	}
	
	public E set(int index, E element) {
		Node<E> temp = getNode(index);
		E oldVal = temp.data;
		temp.data = element;
		return oldVal;
	}
	
	public E remove(int index) {
		return remove(getNode(index));
	}
}
