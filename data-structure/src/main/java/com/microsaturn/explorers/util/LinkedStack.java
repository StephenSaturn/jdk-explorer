package com.microsaturn.explorers.util;

/**
 * Stack: 基于单链表栈(LIFO)
 * @author saturn
 *
 */
public class LinkedStack<E> {

	private int size;
	private Node<E> top;// 栈顶
	
	LinkedStack() {
		
	}
	
	private static class Node<E> {
		E data;
		Node<E> next;
		
		Node(E element, Node<E> next) {
			this.data = element;
			this.next = next;
		}
	}
	
	/**
	 * 入栈
	 * @param element
	 * @return
	 */
	public boolean push(E element) {
		top = new Node<E>(element, top);
		size ++;
		return true;
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public E pop() {
		Node<E> temp = top;
		top = top.next;
		size --;
		return temp.data;
	}
	
	/**
	 * 查看栈顶元素
	 * @return
	 */
	public E peek() {
		return top.data;
	}
	
	public int size() {
		return size;
	}
	
	public boolean empty() {
		return size() == 0;
	}
}
