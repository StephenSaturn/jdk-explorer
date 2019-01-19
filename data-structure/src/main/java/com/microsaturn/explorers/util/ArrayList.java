package com.microsaturn.explorers.util;

/**
 * 线性列表
 * @author saturn
 *
 * @param <E>
 */
public class ArrayList<E> {

	private static final int DEFAULT_CAPACITY = 10;
	
	private int size;
	private Object[] elementData;
	
	public ArrayList() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}
	
	public void ensureCapacity(int newCapacity) {
		if(newCapacity < size) {
            return;
        }
		Object[] newArr = new Object[newCapacity];
		for(int i = 0; i < size(); i++) {
			newArr[i] = elementData[i];
		}
		this.elementData = newArr;
	}
	
	public int size() {
		return this.size;
	}
	
	public boolean add(E e) {
		add(size(), e);
		return true;
	}

	public void add(int index, E e) {
		if(elementData.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}
		for(int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = e;
		size ++;
	}
	
	@SuppressWarnings("unchecked")
	public E remove(int index) {
		E temp = (E) elementData[index];
		for(int i = index; i < size; i++) {
			elementData[i] = elementData[i + 1];
		}
		size --;
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public E get(int index) {
		return (E) elementData[index];
	}
	
	@SuppressWarnings("unchecked")
	public E set(int index, E element) {
		E temp = (E) elementData[index];
		elementData[index] = element;
		return temp;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public void clear() {
		size = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public void trimToSize() {
		ensureCapacity(size());
	}
}
