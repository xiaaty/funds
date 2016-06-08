package com.gqhmt.util;


import com.gqhmt.core.util.StringUtils;

public class FuncUtil {
	public static boolean isInString(Integer id,String s){
		if(StringUtils.isEmpty(s))
			return false;
		return s.indexOf(","+id+",")>=0;
	}
}
