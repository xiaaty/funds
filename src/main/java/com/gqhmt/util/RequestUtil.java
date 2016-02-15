package com.gqhmt.util;

import com.gqhmt.core.util.GlobalConstants;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;


public class RequestUtil {
	public static SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

	public static int getInt(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		if (tempStr == null || "".equals(tempStr) || "null".equalsIgnoreCase(tempStr)) {
			return 0;
		}
		return Integer.parseInt(tempStr);
	}

	public static int getInt(HttpServletRequest request, String str, int defaultInt) {
		String tempStr = (String) request.getParameter(str);
		int tempInt = 0;
		if (tempStr == null || "".equals(tempStr)|| "null".equalsIgnoreCase(tempStr)) {
			return defaultInt;
		}
		try {
			tempInt = Integer.parseInt(tempStr);
		} catch (Exception e) {
			tempInt = defaultInt;
		}
		return tempInt;
	}

	public static int getCPageInt(HttpServletRequest request, String str, int defaultInt) {
		String tempStr = (String) request.getParameter(str);
		int startRow = 0;
		if (tempStr == null || "".equals(tempStr) | "1".equalsIgnoreCase(str)) {
			return defaultInt;
		} else {
			startRow = (Integer.parseInt(tempStr) - 1) * GlobalConstants.PAGE_SIZE;
		}
		return startRow;
	}

	public static Integer getInteger(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		if (tempStr == null || "".equals(tempStr)) {
			return null;
		} else {
			return Integer.parseInt(tempStr);
		}
	}

	public static BigDecimal getBigDecimal(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		BigDecimal tempBigDecimal = null;
		if (tempStr == null || "".equals(tempStr)) {
			tempStr = "0.00";
		}
		try {
			tempBigDecimal = new BigDecimal(tempStr).setScale(2, BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			tempBigDecimal = new BigDecimal(0.00);
		}
		return tempBigDecimal;
	}

	public static BigDecimal[] getBigDecimals(HttpServletRequest request, String str) {
		String[] strArr = request.getParameterValues(str);
		int len = strArr.length;
		BigDecimal[] tempBigDecimal = new BigDecimal[len];
		for (int j = 0; j < len; j++) {
			if (strArr[j] == null || "".equals(strArr[j])) {
				strArr[j] = "0.00";
			}
			try {
				tempBigDecimal[j] = new BigDecimal(strArr[j]).setScale(2, BigDecimal.ROUND_HALF_UP);
			} catch (Exception e) {
				tempBigDecimal[j] = new BigDecimal(0.00);
			}
		}
		return tempBigDecimal;

	}

	public static String getStringNull(HttpServletRequest request, String str) {
		String tempStr = null;
		try {
			tempStr = request.getParameter(str);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return tempStr;
	}
	
	public static String getStringDefault(HttpServletRequest request,String name,String defaultString){
		return StringUtils.isEmpty(request.getParameter(name))?defaultString:request.getParameter(name).trim();
	}

	public static String getStrings(HttpServletRequest request, String str) {
		String tempStr = "";
		String[] temps = request.getParameterValues(str);
		if (temps == null) {
			return null;
		}
		for (String temp : temps) {
			tempStr += temp + ",";
		}
		return tempStr;
	}

	public static Date getSQLDate(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = Date.valueOf(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static java.util.Date getUtilDate(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		java.util.Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = sd.parse(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static java.util.Date getUtilDateTime(HttpServletRequest request, String str) {
		String tempStr = (String) request.getParameter(str);
		java.util.Date tempDate = null;
		if (tempStr != null) {
			try {
				tempDate = sdt.parse(tempStr);
			} catch (Exception e) {
				tempDate = null;
			}
		}

		return tempDate;
	}

	public static Date[] getSqlDate(HttpServletRequest request, String str) {
		String[] strArr = request.getParameterValues(str);
		int length = strArr.length;
		Date[] dateArr = new Date[length];
		for (int i = 0; i < length; i++) {
			if (strArr[i] != null) {
				try {
					dateArr[i] = Date.valueOf(strArr[i]);
				} catch (Exception e) {
					dateArr[i] = null;
				}
			}
		}
		return dateArr;
	}
}
