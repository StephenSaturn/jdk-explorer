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
	
	// 伪双向链表
	class DoubleNode<E> {
		E e;// 当前元素
		
		DoubleNode<E> prev;// 上一个节点
		DoubleNode<E> next;// 下一个节点
	}
	
	public static void main(String[] args) {
		 ArrayList<String> arr = new ArrayList<String>();
		 arr.add("saturn");
		 arr.add("arnold");
		 arr.add("oracle");
		 print(arr);
		 arr.remove(1);
		 System.out.println("\n");
		 print(arr);
	}
	
	public static void print(ArrayList temp) {
		for(int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i) + " ");
		}
	}
}
