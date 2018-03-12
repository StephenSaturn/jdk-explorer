package com.microsaturn.explorer.io.keepalive;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * 维持连接的消息对象(心跳对象)
 * @author Saturn
 *
 */
public class KeepAlive implements Serializable {

	private static final long serialVersionUID = 2304108656832728138L;

	@Override
	public String toString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+" keep alive.";
	}
}
