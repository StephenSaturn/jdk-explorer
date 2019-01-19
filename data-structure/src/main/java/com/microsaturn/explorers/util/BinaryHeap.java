package com.microsaturn.explorers.util;

/**
 * 堆(优先队列)
 * @author Saturn
 *
 */
public class BinaryHeap<E extends Comparable<E>> {

    private E[] arr;
	private int size;
	
	public BinaryHeap() {
		this(10);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(int capacity) {
		arr = (E[]) new Comparable[capacity + 1];
	}
	
	/**
	 * 查看堆顶元素
	 * @return
	 */
	public E peek() {
		if(isEmpty()) 
			throw new RuntimeException("Heap is Empty.");
		return arr[1];
	}
	
	/**
	 * 向堆中添加元素:
	 * 		1.为将元素e插入堆中，找到空闲位置，创建一个空穴，若满足堆序性(Heap Order)，则插入完成
	 * 		2.否则将父节点值装入空穴，这样空穴便上移了
	 * 		3.如此反复，直至满足堆序性
	 * 这种策略称为"上虑"(percolate up)
	 * @param e
	 */
	public void insert(E e) {
		if(isFull()) 
			throw new RuntimeException("Heap is Full.");
		int i = ++size;
		for(; i > 1 && e.compareTo(arr[i / 2]) < 0; i /= 2)
			arr[i] = arr[i / 2];
		arr[i] = e;		
	} 
	
	public E deleteMin() {
		E oldValue = arr[1];
		
		E lastValue = arr[size];
		arr[size--] = null;
		
		int i = 1;
		while(arr[2 * i].compareTo(lastValue) > 0 || arr[2 * i + 1].compareTo(lastValue) > 0) {
			if(arr[2 * i].compareTo(arr[2 * i + 1]) < 0) {
				arr[i] = arr[2 * i];
				i = 2 * i;
			} else {
				arr[i] = arr[2 * i + 1];
				i = 2 * i + 1;
			}
		}
		
		arr[i] = lastValue;
		return oldValue;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean isFull() {
		return size == arr.length - 1;
	}
}
