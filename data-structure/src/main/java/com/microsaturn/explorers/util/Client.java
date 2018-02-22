package com.microsaturn.explorers.util;

public class Client {

	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		System.out.println(stack.empty());
		stack.push("saturn");
		stack.push("arnold");
		stack.push("martian");
		stack.push("jupiter");
		System.out.println(stack.empty());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
