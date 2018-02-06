package com.microsaturn.explorers.util.service;

public interface UserManager {

	void addUser(String username, String password);
	
	void deleteUser(String username);
}
