package com.homework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigsReader {

	public static Properties prop;
	
	public static void readProperties(String filePath) {
		
		try {
			FileInputStream fis=new FileInputStream(filePath);
			prop=new Properties(); 
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * This method will return you value as string
	 * @param key
	 * @return value
	 * 
	 */
	public static String getAsString (String key) {
		return prop.getProperty(key);
	}
	/*
	 * This method will return you value as int
	 * @param key
	 * @return int
	 * 
	 */
	
	public static int getAsInt(String key) {
		return Integer.parseInt(prop.getProperty(key));
	}
}
