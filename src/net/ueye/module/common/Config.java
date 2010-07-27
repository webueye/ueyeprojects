package net.ueye.module.common;

import java.io.IOException;
import java.util.Properties;

public class Config {
	
	private static Properties prop = new Properties();
	
	static{
		try {
			prop.load(Config.class.getClassLoader().getResourceAsStream("net/ueye/module/common/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return prop.getProperty(key);
	}
	
	public static void main(String[] args) {
		
		p(prop.get("a"));
		
	}
	
	public static void p(Object value){
		System.out.println(value);
	}

}
