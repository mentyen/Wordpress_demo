package com.homework.utils;

public class WaitUtils {
	
	public static void sleep(long inMillis) {
		try {
			Thread.sleep(inMillis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
