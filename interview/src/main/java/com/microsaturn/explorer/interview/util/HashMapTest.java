package com.microsaturn.explorer.interview.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HashMapTest {
	
	// 验证特性：HashMap至多允许存在一条key为null的记录,重复put key为null的记录,会修改已经存在的key为null的记录
	static void test1() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put(null, "saturn");
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
	
	// fail-fast, iterator过程中,破坏数据结构(如新增数据)会throw Concurrent Modification Exception,而修改数据内容不会报异常
	static void test2() {
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("Galilei", "15640215－16420108");
		hashMap.put("Newton", "16430104－17270331");
		hashMap.put("Einstein", "18790314－19560420");
		hashMap.put("Hawking", "19420108－20180314");
		Iterator<Map.Entry<String, Object>> iterator = hashMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Map.Entry<String, Object> mapEntry = iterator.next();
			System.out.println("Before === Key:" + mapEntry.getKey() + " value:" + mapEntry.getValue());
			if(mapEntry.getKey().equals("Einstein")) {
				hashMap.put("Einstein", "18790314－19550418");// 只修改原数据内容,并未破坏数据结构,执行成功
				//hashMap.put("Turing", "19120623－19540607");// 新增数据,破坏了原有结构,throw Concurrent Modification Exception
			}
			System.out.println("After === Key:" + mapEntry.getKey() + " value:" + mapEntry.getValue());
		}
	}
	
	static void basicTest() {
		HashMap<String, Object> hashMap = new HashMap<>();
		
		hashMap.put("physics", 94);
		hashMap.put("chemistry", 82);
		hashMap.put("biology", 77);
		hashMap.put("mathematics", 94);
		hashMap.put("Chinese", 65);
		hashMap.put("English", 82);
	}
	
	public static void main(String[] args) {
		//test1();
		test2();
		//basicTest();
	}
}