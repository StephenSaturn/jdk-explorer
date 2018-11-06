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
	
	static void testMyLinkedList() {
		LinkedList<String> myLinkedList = new LinkedList<String>();
		myLinkedList.add("saturn");
		myLinkedList.add("arnold");
		myLinkedList.add(0, "Jupiter");
		System.out.println("MyLinkedList ==== size: " + myLinkedList.size());
		
		System.out.println("MyLinkedList ==== 1处原始数据:" + myLinkedList.set(1, "Apollo"));
		System.out.println("MyLinkedList ==== 1处修改后的数据:" + myLinkedList.get(1));
		
		System.out.println("MyLinkedList ==== 1处原始数据:" + myLinkedList.remove(1));
		System.out.println("MyLinkedList ==== size: " + myLinkedList.size());
		
		System.out.println("MyLinkedList ==== 0:" + myLinkedList.get(0));
		System.out.println("MyLinkedList ==== 1:" + myLinkedList.get(1));
		//System.out.println("MyLinkedList ==== 2:" + myLinkedList.get(2));
	}
	
	static void testLinkedList() {
		java.util.LinkedList<String> linkedList = new java.util.LinkedList<String>();
		linkedList.add("saturn");
		linkedList.add("arnold");
		linkedList.add(0, "Jupiter");
		System.out.println("LinkedList ==== size: " + linkedList.size());
		System.out.println("LinkedList ==== 0:" + linkedList.get(0));
		System.out.println("LinkedList ==== 1处原始数据:" + linkedList.set(1, "Apollo"));
		System.out.println("LinkedList ==== 1处修改后的数据:" + linkedList.get(1));
		
		System.out.println("MyLinkedList ==== 1处原始数据:" + linkedList.remove(1));
		System.out.println("MyLinkedList ==== size: " + linkedList.size());
		
		System.out.println("MyLinkedList ==== 0:" + linkedList.get(0));
		System.out.println("MyLinkedList ==== 1:" + linkedList.get(1));
		//System.out.println("MyLinkedList ==== 2:" + linkedList.get(2));
	}
	
	static void testLinkedStack() {
		LinkedStack<String> myStack = new LinkedStack<String>();
		myStack.push("saturn");
		myStack.push("arnold");
		myStack.push("martian");
		myStack.push("jupiter");
		System.out.println("myStack ==== size:" + myStack.size());
		System.out.println("myStack ==== 出栈：" + myStack.pop());
		System.out.println("myStack ==== 出栈：" + myStack.pop());
		System.out.println("myStack ==== 出栈：" + myStack.pop());
		System.out.println("myStack ==== 出栈：" + myStack.pop());
		System.out.println("myStack ==== size:" + myStack.size());
	}
	
	static void testArrayStack() {
		ArrayStack<String> myStack = new ArrayStack<String>();
		myStack.push("saturn");
		myStack.push("arnold");
		myStack.push("martian");
		myStack.push("jupiter");
		myStack.push("Apollo");
		
		myStack.push("Diana");
		myStack.push("Minerva");
		myStack.push("Ceres");
		myStack.push("Vulcanus");
		myStack.push("Juno");
		
		System.out.println("myStack ==== 出栈：" + myStack.pop());
		
		myStack.push("Mercury");
//		System.out.println("myStack ==== size:" + myStack.size());
//		
//		System.out.println("myStack ==== stack top:" + myStack.peek());
//		System.out.println("myStack ==== 出栈：" + myStack.pop());
//		
//		System.out.println("myStack ==== stack top:" + myStack.peek());
//		System.out.println("myStack ==== 出栈：" + myStack.pop());
//		
//		System.out.println("myStack ==== stack top:" + myStack.peek());
//		System.out.println("myStack ==== 出栈：" + myStack.pop());
//		
//		System.out.println("myStack ==== stack top:" + myStack.peek());
//		System.out.println("myStack ==== 出栈：" + myStack.pop());
	}
	
	static void testArrayQueue() {
		ArrayQueue<String> queue = new ArrayQueue<String>();
//		queue.enqueue("saturn");
//		queue.enqueue("arnold");
//		queue.enqueue("martian");
//		queue.enqueue("jupiter");
		
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		queue.enqueue("Apollo");
//		queue.enqueue("Diana");
//		queue.enqueue("Minerva");
//		queue.enqueue("Ceres");
//		queue.enqueue("Vulcanus");
//		queue.enqueue("Juno");
		
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		System.out.println("Queue ==== 出队数据:" + queue.dequeue());
//		
//		queue.enqueue("Mercury");
		
		queue.enqueueWithCircular("saturn");
		queue.enqueueWithCircular("arnold");
		queue.enqueueWithCircular("martian");
		queue.enqueueWithCircular("jupiter");
		queue.enqueueWithCircular("Apollo");
		queue.enqueueWithCircular("Diana");
		queue.enqueueWithCircular("Minerva");
		queue.enqueueWithCircular("Ceres");
		queue.enqueueWithCircular("Vulcanus");
		queue.enqueueWithCircular("Juno");
		
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		System.out.println("Queue ==== 出队数据:" + queue.dequeueWithCircular());
		
		
//		queue.enqueueWithCircular("Mercury");
//		
		System.out.println("Queue ==== size:" + queue.size());
	}
	
	static void testLinkedQueue() {
		LinkedQueue<String> queue = new LinkedQueue<String>();
//		print("Linked Queue ==== empty queue:" + queue.dequeue());
		
//		print("Linked Queue ==== peek empty queue:" + queue.peek());
		
		queue.enqueue("saturn");
//		print("Linked Queue ==== peek queue:" + queue.peek());
		
		queue.enqueue("arnold");
		queue.enqueue("martian");
		queue.enqueue("jupiter");
		
		print("Linked Queue ==== size:" + queue.size());
		
		print("Linekd Queue ==== 出队数据:" + queue.dequeue());
		print("Linekd Queue ==== 出队数据:" + queue.dequeue());
		print("Linekd Queue ==== 出队数据:" + queue.dequeue());
		print("Linekd Queue ==== 出队数据:" + queue.dequeue());
		
//		queue.enqueue("Apollo");
//		queue.enqueue("Diana");
//		queue.enqueue("Minerva");
//		queue.enqueue("Ceres");
//		queue.enqueue("Vulcanus");
//		queue.enqueue("Juno");
		
		print("Linked Queue ==== size:" + queue.size());
	}
	
	/**
	 * 测试JDK提供的基于双端链表实现的Dequeue
	 */
	static void testLinkedListWithDequeue() {
		java.util.LinkedList<String> dequeue = new java.util.LinkedList<String>();
//		print("JDK Dequeue base DLNode ==== size:" + dequeue.size());
//		print("JDK Dequeue base DLNode ==== remove First:" + dequeue.removeFirst());
		
		dequeue.addFirst("saturn");
		dequeue.addLast("arnold");
		dequeue.addFirst("mars");
		dequeue.addLast("Apollo");
		print("JDK Dequeue base DLNode ==== size:" + dequeue.size());
		print("JDK Dequeue base DLNode ==== remove First:" + dequeue.removeFirst());
		print("JDK Dequeue base DLNode ==== remove Last:" + dequeue.removeLast());
		print("JDK Dequeue base DLNode ==== size:" + dequeue.size());
		
//		print("JDK Dequeue base DLNode ==== get First:" + dequeue.getFirst());
//		print("JDK Dequeue base DLNode ==== get Last:" + dequeue.getLast());
	}
	
	static void testLinkedDequeue() {
		LinkedDequeue<String> dequeue = new LinkedDequeue<String>();
//		print("Mine Dequeue base DLNode ==== size:" + dequeue.size());
//		print("Mine Dequeue base DLNode ==== remove First:" + dequeue.removeFirst());
		
		dequeue.addFirst("saturn");
		dequeue.addLast("arnold");
		dequeue.addFirst("mars");
		dequeue.addLast("Apollo");
		
		print("Mine Dequeue base DLNode ==== size:" + dequeue.size());
		print("Mine Dequeue base DLNode ==== remove First:" + dequeue.removeFirst());
		print("Mine Dequeue base DLNode ==== remove Last:" + dequeue.removeLast());
		print("Mine Dequeue base DLNode ==== size:" + dequeue.size());
		
		
//		print("Mine Dequeue base DLNode ==== get First:" + dequeue.first());
//		print("Mine Dequeue base DLNode ==== get Last:" + dequeue.last());
	}
	
	static void testBinarySearchTree() {
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
		print("BinarySearchTree ==== size:" + binarySearchTree.size());
		print("BinarySearchTree ==== height:" + binarySearchTree.height());// 0
		binarySearchTree.insert(50);
		print("BinarySearchTree ==== height:" + binarySearchTree.height());// 1
		print("BinarySearchTree ==== size:" + binarySearchTree.size());// 1
		binarySearchTree.insert(40);
		print("BinarySearchTree ==== size:" + binarySearchTree.size());
		binarySearchTree.insert(60);
		binarySearchTree.insert(38);
		binarySearchTree.insert(49);
		binarySearchTree.insert(59);
		binarySearchTree.insert(62);
		binarySearchTree.insert(32);
		binarySearchTree.insert(39);
		binarySearchTree.insert(48);
		
		print("BinarySearchTree ==== Contains 100:" + binarySearchTree.contains(100));
		print("BinarySearchTree ==== Contains 49:" + binarySearchTree.contains(49));
		print("BinarySearchTree ==== Contains 1:" + binarySearchTree.contains(1));
		print("BinarySearchTree ==== Contains 60:" + binarySearchTree.contains(60));
		print("BinarySearchTree ==== Contains 47:" + binarySearchTree.contains(47));
		print("BinarySearchTree ==== Contains 40:" + binarySearchTree.contains(40));
		
		print("BinarySearchTree ==== pre Order traversal:" + binarySearchTree.preOrder());
		print("BinarySearchTree ==== in Order traversal:" + binarySearchTree.inOrder());
		print("BinarySearchTree ==== post Order traversal:" + binarySearchTree.postOrder());
		print("BinarySearchTree ==== level Order traversal:" + binarySearchTree.levelOrder());
		
		print("BinarySearchTree ==== size:" + binarySearchTree.size());
		print("BinarySearchTree ==== height:" + binarySearchTree.height());// 5
		print("BinarySearchTree ==== minValue:" + binarySearchTree.findMin());// 7
		print("BinarySearchTree ==== maxValue:" + binarySearchTree.findMax());// 17
		binarySearchTree.remove(17);
		binarySearchTree.remove(7);
		print("BinarySearchTree ==== size:" + binarySearchTree.size());
		print("BinarySearchTree ==== minValue:" + binarySearchTree.findMin());// 16
		print("BinarySearchTree ==== maxValue:" + binarySearchTree.findMax());// 10
	}
	
	@SuppressWarnings("unchecked")
	static void testBinaryHeap() {
		BinaryHeap heap = new BinaryHeap<>();
		heap.insert(89);
		heap.insert(60);
		heap.insert(23);
		heap.insert(12);
		heap.insert(90);
		heap.insert(100);
		heap.insert(23);
		heap.insert(78);
		heap.insert(101);
		heap.insert(802);
		
		System.out.println(heap.peek());
		System.out.println(heap.deleteMin());
		System.out.println(heap.peek());
		
//		heap.insert(103);
//		heap.insert(1000);
		
	}
	
	public static void main(String[] args) throws InterruptedException {
//		testLinkedStack();
		
		testArrayStack();
		
		//testBinaryHeap();
		
		//testBinarySearchTree();
		
//		testLinkedDequeue();
//		System.out.println("****************************");
//		Thread.sleep(3000);
//		testLinkedListWithDequeue();
		
//		testArrayQueue();
//		testLinkedQueue();
		
//		testMyLinkedList();
//		System.out.println("****************************");
//		Thread.sleep(3000);
//		testLinkedList();
		//System.out.println(10 >> 1);
	}
	
	static void print(Object o) {
		System.out.println(o);
	}
	
	public static void print(ArrayList temp) {
		for(int i = 0; i < temp.size(); i++) {
			System.out.print(temp.get(i) + " ");
		}
	}
}
