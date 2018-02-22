package com.microsaturn.explorers.util;

public class Client {

	// 伪动态数组实现
	void dynamicArr() {
		int[] arr = new int[10];// 初始大小为10
		
		// do...
		
		int[] newArr = new int[arr.length * 2];// 需要扩充时
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		
		arr = newArr;
	}
	
	// 伪单向链表
	class Node<E> {
		E e; // 当前元素
		
		Node<E> next;// 下一个节点
	}
	
	// 伪双向链接
	class DoubleNode<E> {
		E e;// 当前元素
		
		DoubleNode<E> prev;// 上一个节点
		DoubleNode<E> next;// 下一个节点
	}
	
	public static void main(String[] args) {
		 ArrayList<String> arr = new ArrayList<String>();
		 System.out.println(arr.size());
		 arr.add("saturn");
		 System.out.println(arr.size());
		 arr.add("arnold");
		 arr.add("oracle");
		 System.out.println(arr.size());
	}
}
