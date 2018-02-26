package com.microsaturn.explorers.util;

/**
 * Binary Tree:基于递归定义的二叉树
 * @author Saturn
 *
 * @param <E>
 */
public class BinaryTreeNode<E> {

	private E data;// 节点数据
	private BinaryTreeNode<E> left;// 左子树
	private BinaryTreeNode<E> right;// 右子树
	private BinaryTreeNode<E> parent;// 父亲节点
	
	public BinaryTreeNode() {}
	
	public BinaryTreeNode(BinaryTreeNode<E> parent, E element, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
		this.parent = parent;
		this.data = element;
		this.left = left;
		this.right = right;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public BinaryTreeNode<E> getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}

	public BinaryTreeNode<E> getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}

	public BinaryTreeNode<E> getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode<E> parent) {
		this.parent = parent;
	}
}
