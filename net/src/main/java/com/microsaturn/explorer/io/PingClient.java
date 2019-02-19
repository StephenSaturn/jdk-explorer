package com.microsaturn.explorer.io;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 模拟Ping命令测试两台机器之间的连通性
 * @author Saturn
 *
 */
public class PingClient {

	public static void main(String[] args) {
		isAddressAvailable("127.0.0.1");
		isAddressAvailable("10.134.98.138");
		isAddressAvailable("10.134.98.139");
		isAddressAvailable("10.148.68.136");
	}
	
	public static void isAddressAvailable(String ip) {
		try {
			InetAddress address = InetAddress.getByName(ip);
			if(address instanceof Inet4Address) {
				System.out.println(ip + " is ipv4 address.");
			} else if(address instanceof Inet6Address) {
				System.out.println(ip + " is ipv4 address.");
			} else {
				System.out.println(ip + " is unrecongized");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
