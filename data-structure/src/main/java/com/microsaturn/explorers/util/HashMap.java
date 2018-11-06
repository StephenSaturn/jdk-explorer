package com.microsaturn.explorers.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * JDK HashMap Explorer
 * @author saturn
 *
 * @param <K>
 * @param <v>
 */
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

	private static final long serialVersionUID = 8516058327983556054L;

	/**
	  *   默认初始容量是16，必须是2的幂
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
	
	/**
	  *  最大容量，必须是2的幂且小于2的30次方，传入容量过大将被这个值替换
	 */
	static final int MAXIMUM_CAPACITY = 1 << 30;
	
	/**
	  *  默认负载因子
	 */
	static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	/**
	  *  存储数组的Entry数组，长度是2的幂
	 * HashMap是采用拉链法实现的，每一个Entry本质上是一个单向链表
	 */
	transient Node<K, V>[] table;
	
	/**
	 * HashMap的大小，它是HashMap保存的键值对的数量
	 */
	transient int size;
	
	/**
	  *  扩容阈值，用于判断是否需要调整HashMap的容量（threshold = 容量 * 负载因子）
	 */
	int threshold;
	
	/**
	  *  负载因子的实际大小
	 */
	final float loadFactor;
	
	/**
	 * HashMap被改变的次数，出现线程问题时，负责及时抛异常
	 */
	transient int modCount;            
	
	/**
	  *  指定"容量大小"和"加载因子"的构造函数
	 * @param initialCapacity   初始容量
	 * @param loadFactor        加载因子
	 */
	public HashMap(int initialCapacity, float loadFactor) {
		if(initialCapacity < 0) 
			throw new IllegalArgumentException("Illegal initial capacity: " +
                                              initialCapacity);
		// HashMap的最大值只能是MAXIMUM_CAPACITY
		if(initialCapacity > MAXIMUM_CAPACITY)
			initialCapacity = MAXIMUM_CAPACITY;
		if(loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("Illegal load factor: " +
                                              loadFactor);
		this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
	} 
	
	public HashMap() {
		loadFactor = DEFAULT_LOAD_FACTOR;
	}
	
	/* ---------------- Static utilities -------------- */
	
	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
	
	static int indexFor(int h, int length) {
        return h & (length-1);
    }
	
	public static void main(String[] args) {
		
	}
	
	static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

	
	// 哈希桶
	static class Node<K,V> implements Map.Entry<K,V>{
	    final int hash;// 定位数组索引位置
	    final K key;
	    V value;
	    Node<K,V> next;// 链表的下一个node

	    Node(int hash, K key, V value, Node<K,V> next) {
	        this.hash = hash;
	        this.key = key;
	        this.value = value;
	        this.next = next;
	    }

	    public final K getKey()        { return key; }
	    public final V getValue()      { return value; }
	    public final String toString() { return key + "=" + value; }

	    public final int hashCode() {
	        return Objects.hashCode(key) ^ Objects.hashCode(value);
	    }

	    public final V setValue(V newValue) {
	        V oldValue = value;
	        value = newValue;
	        return oldValue;
	    }

	    public final boolean equals(Object o) {
	        if (o == this)
	            return true;
	        if (o instanceof Node) {
	        	Node<?,?> e = (Node<?,?>)o;
	            if (Objects.equals(key, e.getKey()) &&
	                Objects.equals(value, e.getValue()))
	                return true;
	        }
	        return false;
	    }
	}


	@Override
	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
}
