package com.microsaturn.explorer.interview.util;

import java.util.HashMap;

public class HashMapTest {
	public static void main(String[] args) {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put(null, "saturn");
		
		// HashMap至多允许存在一条key为null的记录, 重复put key为null的记录
		//会修改已经存在的key为null的记录
		hashMap.put(null, "arnold");
		
		// 此时hashMap中key为null的记录为arnold
		System.out.println(hashMap.size());
		System.out.println(hashMap.get(null));
		
		// HashMap允许存在多条value为null的记录
		hashMap.put("name", null);
		hashMap.put("password", null);
		
		System.out.println(hashMap.size());
		System.out.println(hashMap.get("name"));
		System.out.println(hashMap.get("password"));
	}
}
