package com.microsaturn.explorers.util;

import java.util.Objects;

/**
 * JDK HashMap Explorer
 * @author saturn
 *
 * @param <K>
 * @param <v>
 */
public class HashMap<K, v> {

	static final float DEFAULT_LOAD_FACTOR = 0.75f;// 默认负载因子为0.75
	
	int threshold;             // 扩容阈值 
	final float loadFactor;    // 负载因子
	transient int modCount;    // 出现线程问题时，负责及时抛异常
	transient int size;        // HashMap中实际存在的Node数量
	
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

	
	// 哈希桶
	static class Node<K,V> {
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
}
