package com.microsaturn.explorers.util;

/**
 * Stack:基于线性列表栈(LIFO)
 * @author saturn
 *
 * @param <E>
 */
public class ArrayStack<E> {

	private Object[] elementData;
	private int size;
	private int top;// 栈顶指针
	private int maxSize;
	
	ArrayStack() {
		this(10);
	}
	
	ArrayStack(int initialCapacity) {
		maxSize = initialCapacity;
		elementData = new Object[maxSize];
		top = -1;
	}
	
	public boolean push(E e) {
		if(size >= maxSize) 
			throw new RuntimeException("栈已满.");
		elementData[++top] = e;
		size ++;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public E pop() {
		if(size < 0) 
			throw new RuntimeException("栈为空.");
		E oldVal = (E) elementData[top];
		elementData[top--] = null;
		size --;
		return oldVal;
	}
	
	@SuppressWarnings("unchecked")
	public E peek() {
		return (E)elementData[top];
	}
	
	public int size() {
		return size;
	}
	
	public boolean empty() {
		return size() == 0;
	}
}
