package com.gqhmt.util;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class Resources {
	private static Logger log = Logger.getLogger(Resources.class);
	/** 国际化资源 */
	public static ResourceBundle resourceBundle;

	static {
		resourceBundle = ResourceBundle.getBundle("application");
	}

	public static void close() {
		resourceBundle = null;
	}

	public static String myString() {
		return resourceBundle.toString();
	}

	public static String getString(String key) {
		try {
			if (!resourceBundle.containsKey(key)) {
				return "";
			}
			return resourceBundle.getString(key);
		} catch (Exception e) {
			log.error(e);
			return "";
		}
	}

	public static int getConfigAsInt(String key) {
		return Integer.valueOf(getString(key));
	}

	/**
	 * 从资源文件中返回字符串 我们不希望程序崩溃，所以如果没有找到Key，就直接返回Key
	 */
	public static String getString(String key, Object[] args) {
		try {
			return MessageFormat.format(getString(key), args);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return "";
		}
	}
}
