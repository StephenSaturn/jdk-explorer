package com.microsaturn.explorers.util;

/**
 * LIFO (last-in-first-out)
 * 
 * https://www.cnblogs.com/CherishFX/p/4608880.html
 * 
 * 基于数组实现的顺序栈
 * @author Saturn
 * 
 * @param <E>
 */
public class Stack<E> {

	private Object[] data = null;
	private int size = 0; // 栈容量
	private int top = -1; // 栈顶指针
	
	Stack() {
		this(10);
	}

	Stack(int initialSize) {
		if(initialSize < 0)
			throw new RuntimeException("Illegal Capacity: " + initialSize);
		this.size = initialSize;
		data = new Object[initialSize];
		top = -1;
	}
	
	/**
	 * 判空
	 * @return
	 */
	public boolean empty() {
		return top == -1 ? true : false;
	}
	
	public boolean push(E e) {
		if(top == size -1 )
			throw new RuntimeException("Stack is full.");
		data[++top] = e;
		return true;
	}
	
	/**
	 * 偷窥栈中元素
	 * @return
	 */
	public E peek() {
		if(top == -1) { 
			return null;
		} else {
			return (E)data[top];
		}
	}
	
	public E pop() {
		if(top == -1) {
			return null;
		} else {
			E temp = (E)data[top];
			data[top] = null;
			return temp;
		}
	}
	
	//返回对象在堆栈中的位置，以 1 为基数
    public int search(E e){
        int i=top;
        while(top != -1){
            if(peek() != e){
                top --;
            }else{
                break;
            }
        }
        int result = top+1;
        top = i;
        return result;      
    }
}
