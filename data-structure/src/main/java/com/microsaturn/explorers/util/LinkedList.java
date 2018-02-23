package com.microsaturn.explorers.util;

public class LinkedList<E> {

	private int size;
	private Node<E> beginMarker;
	private Node<E> endMarker;
	
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
	
	private void addBefore(Node<E> p, E x) {
		Node<E> newNode = new Node<E>(p.prev, x, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		size ++;
	}
}
