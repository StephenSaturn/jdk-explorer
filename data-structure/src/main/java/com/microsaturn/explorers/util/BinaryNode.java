package com.microsaturn.explorers.util;

import java.io.Serializable;

/**
 * 二叉树节点类
 * @author saturn
 *
 * @param <T>
 */
public class BinaryNode<T extends Comparable<T>>  implements Serializable {
	
	private static final long serialVersionUID = 3167137863262285770L;
	
	private T data;
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	
	public BinaryNode() {}
	
	public BinaryNode(BinaryNode<T> left, T element, BinaryNode<T> right) {
		this.left = left;
		this.data = element;
		this.right = right;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public BinaryNode<T> getLeft() {
		return left;
	}

	public void setLeft(BinaryNode<T> left) {
		this.left = left;
	}

	public BinaryNode<T> getRight() {
		return right;
	}

	public void setRight(BinaryNode<T> right) {
		this.right = right;
	}
	
	/**
	 * 判断是否是叶子节点
	 * @return
	 */
	public boolean isLeaf() {
		return this.left == null && this.right == null;
	}
}
