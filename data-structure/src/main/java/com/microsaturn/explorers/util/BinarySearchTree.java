package com.microsaturn.explorers.util;

/**
 * 
 * http://blog.csdn.net/javazejian/article/details/53727333
 * 二叉查找树
 * 设定:对于树中任一给定节点X， 
 * 		其左子树中的所有项的值均小于X中的值，
 * 		而其右子树中的所有项的值均大于X中的值
 * @author saturn
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BinaryNode<T> root;//根节点
	
	public BinarySearchTree() {
		root = null;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(T data) {
		root = insert(data, root);
	}
	
	private BinaryNode<T> insert(T data, BinaryNode<T> t) {
		if(t == null) {
			return new BinaryNode<>(null, data, null);// 创建root节点
		}
		
		int compareResult = data.compareTo(t.getData());
		
		if(compareResult < 0) {// 插入左边
			insert(data, t.getLeft());
		} else if(compareResult > 0) {// 插入右边
			insert(data, t.getRight());
		} else {
			// 重复数据，不做任何操作
		}
		
		return t;
	}
}
