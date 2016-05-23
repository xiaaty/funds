package com.gqhmt.util;


public class FuncUtil {
	public static boolean isInString(Integer id,String s){
		if(StringUtils.isEmpty(s))
			return false;
		return s.indexOf(","+id+",")>=0;
	}
}
