package com.microsaturn.explorers.util.service.impl;

import com.microsaturn.explorers.util.service.UserManager;

public class UserManagerImpl implements UserManager {

	public void addUser(String username, String password) {
		System.out.println("add user success.");	
	}

	public void deleteUser(String username) {
		System.out.println("delete user success.");
	}
}
