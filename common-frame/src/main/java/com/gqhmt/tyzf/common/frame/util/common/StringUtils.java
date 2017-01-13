package com.gqhmt.tyzf.common.frame.util.common;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 字符串工具类
 */
public class StringUtils {

	private StringUtils(){
		throw new IllegalAccessError("Utility class");
	}

	public static boolean isNotEmptyString(String str) {
		return (str != null) && (!"".equals(str)) && (!"null".equalsIgnoreCase(str));
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || str.equalsIgnoreCase(null) || "null".equalsIgnoreCase(str);
	}

	public static String encodeUTF8(String value) {
		try {
			return URLEncoder.encode(value, "utf-8");
		} catch (Exception e) {
			System.out.println("encodeUTF8" + e.toString());
			return null;
		}
	}

	/**
	 * 去掉字段串的最后一个逗号
	 * @param str
	 * @return
	 */
	public static String  delComma(String  str){
		 if(StringUtils.isNotEmptyString(str)){
     		if(str.endsWith(",")){
     			str=str.substring(0, str.length()-1);
         	}
	       return str;
        }
		return  null;
	}

	/**
	 * 将数字转成四位长度的字符串
	 * @param length
	 * @return
	 */
    public static String toLengthFour(long length) {
        NumberFormat nf = new DecimalFormat("0000");
        nf.format(length);
        return nf.format(length);
    }

	/**
	 * md5加密
	 * @param s
	 * @return
	 */
	public final static String getMD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
