package com.microsaturn.explorers.util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 基于HashMap源码探索
 * JDK HashMap Explorer
 * @author saturn
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

	private static final long serialVersionUID = 8516058327983556054L;

	/**
	  *   默认初始容量是16，必须是2的幂
	 */
	static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka(also known as) 16
	
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

    /* ---------------- 构造方法开始 -------------- */

    /**
     * 构建一个指定负载因子的空的HashMap
     */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // 其他字段均默认(all other fields defaulted)
    }

    /**
     * 构建一个指定初始容量和默认负载因子的空的HashMap
     * @param initialCapacity 初始容量
     */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     *  指定"容量大小"和"加载因子"的构造函数
     * @param initialCapacity   初始容量
     * @param loadFactor        加载因子
     */
    public HashMap(int initialCapacity, float loadFactor) {
        if(initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +  initialCapacity);
        }
        // HashMap的最大值只能是MAXIMUM_CAPACITY
        if(initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if(loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);// 调整给定初始化容量值
    }

	/* ---------------- 构造方法结束 -------------- */
	
	/* ---------------- Static utilities -------------- */


	static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 计算hash值
     * @param h
     * @param length
     * @return
     */
	static int indexFor(int h, int length) {
        return h & (length-1);
    }

    /**
     * 返回一个比给定整数大且最接近的2的幂次方整数，如给定5，则返回8
     * @param cap
     * @return
     */
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
	static class Node<K,V> implements Map.Entry<K,V> {
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

	    @Override
	    public final K getKey()        { return key; }

	    @Override
	    public final V getValue()      { return value; }

	    @Override
	    public final String toString() { return key + "=" + value; }

	    @Override
	    public final int hashCode() {
	        return Objects.hashCode(key) ^ Objects.hashCode(value);
	    }

	    @Override
	    public final V setValue(V newValue) {
	        V oldValue = value;
	        value = newValue;
	        return oldValue;
	    }

	    @Override
	    public final boolean equals(Object o) {
	        if (o == this) {
                return true;
            }
	        if (o instanceof Node) {
	        	Map.Entry<?,?> e = (Map.Entry<?,?>)o;
	            if (Objects.equals(key, e.getKey()) && Objects.equals(value, e.getValue())) {
                    return true;
                }
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
