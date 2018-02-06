package com.microsaturn.explorers.util;

import java.util.Iterator;
import java.util.ServiceLoader;

import com.microsaturn.explorers.util.service.UserManager;

public class Client {

	public static void main(String[] args) {
		ServiceLoader<UserManager> loader = ServiceLoader.load(UserManager.class);
		System.out.println(loader);
		
		Iterator<UserManager> iterator = loader.iterator();
		while(iterator.hasNext()) {
			UserManager manager = iterator.next();
			manager.addUser("saturn", "Saturn2012");
			manager.deleteUser("saturn");
		}
	}
}
