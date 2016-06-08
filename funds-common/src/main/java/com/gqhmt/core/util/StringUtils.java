package com.gqhmt.core.util;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.*;
import java.util.Date;

public class StringUtils {
	public static boolean isNotEmptyString(String str) {
		return (str != null) && (!"".equals(str)) && (!"null".equalsIgnoreCase(str));
	}

	public static int strToInt(String str) {

		try {
			if (isEmpty(str)) {
				return 0;
			}
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	public static Double strToDou(String str) {
		try {
			if (isEmpty(str)) {
				return 0.0;
			}
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}
	public static long strToLong(String str) {

		try {
			if (isEmpty(str)) {
				return 0l;
			}
			return Long.parseLong(str);
		} catch (NumberFormatException e) {
			return 0l;
		}
	}
	public static int strToInt(String str,int defaultValue) {

		try {
			if (isEmpty(str)) {
				return defaultValue;
			}
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}
	public static BigDecimal strToBigDecimal(String str) {

		try {
			if (isEmpty(str)) {
				return  new BigDecimal(0);
			}
			return new BigDecimal(str);
		} catch (NumberFormatException e) {
			return new BigDecimal(0);
		}
	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0 || str.equalsIgnoreCase(null) || str.equalsIgnoreCase("null");
	}

	public static String encodeUTF8(String value) {
		try {
			return URLEncoder.encode(value, "utf-8");
		} catch (Exception e) {

			System.out.println("encodeUTF8" + e.toString());
			return null;
		}
	}
	public static String  delComma(String  str){
		 if(StringUtils.isNotEmptyString(str)){
     		if(str.endsWith(",")){
     			str=str.substring(0, str.length()-1);
         	}
	       return str;
        }
		 return  null;
	}
	
	public static Date strToDate(String str) throws ParseException{
		if(isEmpty(str)){
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		
		return df.parse(str.replace("-", "").replace("/",""));
	}

    public static String toLengthFour(long length) {
        NumberFormat nf = new DecimalFormat("0000");
        nf.format(length);
        return nf.format(length);
    }
}
