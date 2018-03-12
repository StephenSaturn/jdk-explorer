package com.microsaturn.explorers.util;

/**
 * 
 * 二叉查找树
 * 约定:对于树中任一给定节点X，其值大于所有左子树所有节点的值，且小于所有右子树所有节点的值 
 * @author saturn
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

	private BinaryNode<T> root;//根节点
	
	public BinarySearchTree() {
		root = null;
	}
	
	public String preOrder() {
		String str = preOrder(root);
		if(str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public String inOrder() {
		String str = inOrder(root);
		if(str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public String postOrder() {
		String str = postOrder(root);
		if(str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public String levelOrder() {
		//String str = levelOrder(root);
		String str = levelOrderTraverse();
		if(str.length() > 0) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(T data) {
		if(data == null)
			throw new RuntimeException("Data is not Null.");
		root = insert(data, root);
	}
	
	/**
	 * 从树中删除指定元素
	 * @param t
	 */
	public void remove(T t) {
		if(t == null)
			throw new RuntimeException("Data is Null.");
		root = removeNode(t, root);
	}
	
	/**
	 * 查找树中的最小值
	 * @return
	 */
	public T findMin() {
		if(isEmpty()) 
			throw new RuntimeException("BinarySearchTree is Empty.");
		return findMin(root).data;
	}
	
	/**
	 * 查找树中的最大值
	 * @return
	 */
	public T findMax() {
		if(isEmpty()) 
			throw new RuntimeException("BinarySearchTree is Empty.");
		return findMax(root).data;
	}
	
	/**
	 * 求树的高度:最大层次所在的节点
	 * @return
	 */
	public int height() {
		return height(root);
	}
	
	/**
	 * 计算树的大小, 树的节点数
	 * @return
	 */
	public int size() {
		if(root == null) {
			return 0;
		}
		return size(root);
	}
	
	// 是否包含指定的值
	public boolean contains(T t) {
		if(t == null)
			return false;
		if(size() == 0)
			return false;
		BinaryNode<T> subTree = root;
		while(subTree != null) {
			int rest = t.compareTo(subTree.data);
			if(rest == 0) {
				return true;
			} else if(rest > 0) {
				subTree = subTree.right;
			} else if(rest < 0) {
				subTree = subTree.left;
			}
		}
		return false;
	}

	
	/**
	 * 先根次序遍历递归实现
	 * 
	 * @param subTree
	 * @return
	 */
	private String preOrder(BinaryNode<T> subTree) {
		StringBuffer sb = new StringBuffer();
		if(subTree != null) {
			// 先访问根节点
			sb.append(subTree.data + ",");
			// 再遍历左子树
			sb.append(preOrder(subTree.left));
			// 最后遍历右子树
			sb.append(preOrder(subTree.right));
		}
		return sb.toString();
	}
	
	/**
	 * 中根次序遍历递归实现
	 * @param subTree
	 * @return
	 */
	private String inOrder(BinaryNode<T> subTree) {
		StringBuffer sb = new StringBuffer();
		if(subTree != null) {
			// 先遍历左子树
			sb.append(inOrder(subTree.left));
			// 再访问根节点
			sb.append(subTree.data + ",");
			// 最后遍历右子树
			sb.append(inOrder(subTree.right));
		}
		return sb.toString();
	}
	
	/**
	 * 后根次序遍历递归实现
	 * @param subTree
	 * @return
	 */
	private String postOrder(BinaryNode<T> subTree) {
		StringBuffer sb = new StringBuffer();
		if(subTree != null) {
			// 先遍历左子树
			sb.append(postOrder(subTree.left));
			// 再遍历右子树
			sb.append(postOrder(subTree.right));
			// 最后是根节点
			sb.append(subTree.data + ",");
		}
		return sb.toString();
	}
	
	private LinkedQueue<BinaryNode<T>> queue = new LinkedQueue<>();
	
	/**
	 * 层次遍历递归实现:同层顺序输出, 需要借助FIFO队列 
	 * @param subTree
	 * @return
	 */
	private String levelOrder(BinaryNode<T> subTree) {
		StringBuffer sb = new StringBuffer();
		if(subTree != null) {
			sb.append(subTree.data + ",");
			// 左孩子入队
			if(subTree.left != null) {
				queue.enqueue(subTree.left);
			}
			// 右孩子入队
			if(subTree.right != null) {
				queue.enqueue(subTree.right);
			}
			if(!queue.isEmpty()) {
				sb.append(levelOrder(queue.dequeue()));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 层次遍历非递归实现
	 * @return
	 */
	private String levelOrderTraverse() {
		ArrayQueue<BinaryNode<T>> arrayQueue = new ArrayQueue<>();
		BinaryNode<T> p = this.root;
		StringBuffer sb = new StringBuffer();
		while(p != null) {
			sb.append(p.data + ",");
			
			if(p.left != null) {
				arrayQueue.enqueue(p.left);
			}
			
			if(p.right != null) {
				arrayQueue.enqueue(p.right);
			}
			
			if(arrayQueue.size() != 0) {
				p = arrayQueue.dequeue();
			} else {
				p = null;
			}
		}
		return sb.toString();
	}
	
	private int size(BinaryNode<T> subTree) {
		if(subTree == null) 
			return 0;
		return size(subTree.left) + 1 + size(subTree.right);
	}
	
	private int height(BinaryNode<T> subTree) {
		if(subTree == null) 
			return 0;
		int l = height(subTree.left);
		int r = height(subTree.right);
		return (l > r) ? (l + 1) : (r + 1);// 返回并加上当前层
	}
	
	/**
	 * 1.叶子节点:立即删除
	 * 2.有一个孩子节点的节点(左孩子或右孩子):调整该节点(p)的父节点(q.left或q.right)指向其孩子节点(p.left或p.right)
	 * 3.拥有两个孩子节点:用该节点(p)的右子树的最小值替代p的值，并递归删除用于替代的节点
	 * @param t
	 * @param p
	 * @return
	 */
	private BinaryNode<T> removeNode(T t, BinaryNode<T> p) {
		if(p == null) // 没有找到要删除的元素
			return p;
		
		int compareResult = t.compareTo(p.data);
		
		if(compareResult < 0) {// 从左边查找删除节点
			p.left = removeNode(t, p.left);
		} else if(compareResult > 0) {// 从右边查找删除节点
			p.right = removeNode(t, p.right);
		} else if(p.left != null && p.right != null) {// 两个孩子节点的节点
			// 中继替换，找到右子树中最小的元素并替换需要删除的元素值
			p.data = findMin(p.right).data;
			// 递归删除用于替代的节点
			p.right = removeNode(p.data, p.right);
		} else {// 单个孩子节点的节点或叶子节点
			p = (p.left != null) ? p.left : p.right;
		}
		
		return p;// 返回该节点
	}	
	
	/**
	 * 查找指定节点的子孙节点中的最小值节点
	 * @param t
	 * @return
	 */
	private BinaryNode<T> findMin(BinaryNode<T> p) {
		if(p == null) {
			return null;
		} else if(p.left == null) {
			return p;
		}
		return findMin(p.left);
	}
	
	/**
	 * 查找指定节点的子孙节点中的最大值节点
	 * @param t
	 * @return
	 */
	private BinaryNode<T> findMax(BinaryNode<T> p) {
		if(p == null) {
			return null;
		} else if(p.right == null) {
			return p;
		}
		return findMax(p.right);
	}
	
	private BinaryNode<T> insert(T data, BinaryNode<T> p) {
		if(p == null) {
			return new BinaryNode<>(null, data, null);
		}
		
		int compareResult = data.compareTo(p.data);
		
		if(compareResult < 0) {// 插入左边
			p.left = insert(data, p.left);
		} else if(compareResult > 0) {// 插入右边
			p.right = insert(data, p.right);
		} else {
			// 重复数据，不做任何操作
		}
		return p;
	}
	
	/**
	 * 二叉树节点类
	 * @author Saturn
	 *
	 * @param <T>
	 */
	private static class BinaryNode<T> {
		T data;
		BinaryNode<T> left;
		BinaryNode<T> right;
		
		public BinaryNode(BinaryNode<T> left, T element, BinaryNode<T> right) {
			this.left = left;
			this.data = element;
			this.right = right;
		}
		
		//判断是否是叶子节点
		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}
	}
}
