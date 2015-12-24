package com.gqhmt.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * 
 * <p>公共类</p>
 * <p>日期格式化、金额格式化、解压等实现</p>
 * @author yangfei
 * @version 1.0
 */

public class CommonUtil {
	private static Log log = LogFactory.getLog("CommonUtil.class");
	public static String APP_DIR = "";
	public static String WEBINF_DIR = "";
    public static final int DEFAULT_BUFFER_SIZE = 10 * 1024;
	public CommonUtil() {
	}
	
	private static Hashtable<Long, String> hs = new Hashtable<Long, String>();
	private static Random random = new Random(); 


	
	private final static byte[] val = { 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x00, 0x01,       
        0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F,       
        0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F, 0x3F };   
	

	/**
	 * format data
	 * 
	 * @param str
	 * @param i
	 * @return
	 */
	public static String replicate(String str, int i) {
		if (str != null && str.length() < i) {
			while (str.length() < i) {
				str = "0" + str;
			}
		}
		return str;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void unzipFile(File zip, File dir) throws IOException {
		ZipFile zipFile = new ZipFile(zip.getPath());
		Enumeration e = zipFile.getEntries();
		ZipEntry zipEntry = null;
		List folderList = new ArrayList();
		List fileList = new ArrayList();
		while (e.hasMoreElements()) {
			zipEntry = (ZipEntry) e.nextElement();
			if (zipEntry.isDirectory()) {
				folderList.add(zipEntry);
			} else if (!zipEntry.isDirectory()) {
				fileList.add(zipEntry);
			}
		}
		for (int i = 0; i < folderList.size(); i++) {
			zipEntry = (ZipEntry) folderList.get(i);
			String name = zipEntry.getName();
			File f = new File(dir, name);
			name = name.substring(0, name.length() - 1);
			f.mkdirs();
		}
		for (int j = 0; j < fileList.size(); j++) {
			zipEntry = (ZipEntry) fileList.get(j);
			String name = zipEntry.getName();
			File f = new File(dir, name);
			if (!f.exists()) {
				f.createNewFile();
				InputStream in = zipFile.getInputStream(zipEntry);
				FileOutputStream out = new FileOutputStream(f);
				int c;
				byte[] by = new byte[1024];
				while ((c = in.read(by)) != -1) {
					out.write(by, 0, c);
				}
				out.close();
				in.close();
			}
		}
		zipFile.close();
	}
	public static String getMaxString(String pattern,int number){
		DecimalFormat df = new DecimalFormat(pattern);
		String code = df.format(number);
		return code;
	}
	
	public static String getString(String oriStr, String oriCharset, String newCharset){
		log.debug("oriString:" + oriStr);
		try {
			oriStr = new String(oriStr.getBytes(oriCharset),newCharset);
		} catch (UnsupportedEncodingException e) {
			log.error("encoding failed.",e);
		}
		return oriStr;
	}
	
	public static byte[] inputStream2Bytes(InputStream in) throws Exception {
        byte[] b = new byte[DEFAULT_BUFFER_SIZE];
        int borb = -1;
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        while ((borb = in.read(b)) != -1) {
                fos.write(b, 0, borb);
        }
        return fos.toByteArray();
    }
	
	
	/**
	 * 获取文件扩张名
	 * @param filename 文件名
	 * @param defExt   没有扩展名  返回一个默认值
	 * @return
	 */
	public static String getExtension(String filename, String defExt) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int i = filename.lastIndexOf('.'); 

            if ((i >-1) && (i < (filename.length() - 1))) { 
                return filename.substring(i + 1); 
            } 
        } 
        return defExt; 
    } 
	
	/**
	 * 获取真实文件名
	 * @param filename 文件名
	 * @param defExt   没有扩展名  返回一个默认值
	 * @return
	 */
	public static String getTrueFileName(String filename, String defExt) { 
        if ((filename != null) && (filename.length() > 0)) { 
            int i = filename.lastIndexOf('.'); 

            if ((i >-1) && (i < (filename.length() - 1))) { 
                return filename.substring(0,i); 
            } 
        } 
        return defExt; 
    } 
	
	
	/**
	 * 直接输出纯字符串.
	 */
	public  static void writeResponse(HttpServletResponse res, String text) {
		res.setContentType("text/plain;charset=UTF-8");
		try {
			res.getWriter().write(text);
			res.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取转义后的文件名
	 * @param fileName
	 * @return
	 */
	public static String EscapeFileName(String fileName){
		int i2 = fileName.lastIndexOf("\\");
		if (i2 > -1)
			fileName = fileName.substring(i2 + 1);
		fileName = new StringBuffer(Calendar.getInstance()
				.getTimeInMillis()
				+ "").append("_").append(fileName).toString();
		return fileName;
	}
	
	 /**
     * 时间格式转换(Date to String) 
     * @return
     */
	public static String dateToString(Date date){
		
		if (date == null || date.equals("")) {
			return "";
		}
		
    	String pioDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
    	return pioDate;
    }
	 /**
     * 时间格式转换(Date to String) 
     * @return
     */
	public static String dateTostring(Date date){
		if (date == null || date.equals("")) {
			return "";
		}
    	String pioDate = new SimpleDateFormat("yyyyMMdd").format(date);
    	return pioDate;
    }
	
	
	public static String dateToMonthDay(Date date){
		if (date == null || date.equals("")) {
			return "";
		}
		String pioDate = new SimpleDateFormat("MM-dd").format(date);
    	return pioDate;
	}
	
	public static String dateToYearMonth(Date date){
		if (date == null || date.equals("")) {
			return "";
		}
		String pioDate = new SimpleDateFormat("yyyy-MM").format(date);
		return pioDate;
	}
	
	/**
	 * 24小时制
	 * @param date
	 * @return
	 */
	public static String dateToString_24(Date date){
		if (date == null || date.equals("")) {
			return "";
		}
    	String pioDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    	return pioDate;
    }
	
	
	/**
     * 时间格式转换(String to Date)
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString){
    	if (dateString == null  || dateString.equals("")) {
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return date;
    }
  
    /** 
    * @Title: stringToDate 
    * @author 王晓鹏
    * @Description: 日期转换
    * @date 2014年8月26日 下午6:19:57 
    * @param dateString
    * @param formatString
    * @return   
    */ 
    public static Date stringToDate(String dateString,String formatString){
    	SimpleDateFormat sdf = new SimpleDateFormat(formatString);  
        Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        return date;
    }
    
	
	public static String createRandom(){
		
		Long num = random.nextLong();
		num = Math.abs(num);
		if(hs.get(num)!=null){
			createRandom();
		}else{
			hs.put( num, num.toString());
		}
		return num.toString();
	}	
	
	
  
	/**
	 * escape 解码 
	 * 	前台javascript escape 编码 
	 * @param s
	 * @return
	 */
	public static String unescape(String s) {       
        StringBuffer sbuf = new StringBuffer();       
        int i = 0;       
        int len = s.length();       
        while (i < len) {       
        int ch = s.charAt(i);       
        if ('A' <= ch && ch <= 'Z') {   
        sbuf.append((char) ch);       
        } else if ('a' <= ch && ch <= 'z') {        
        sbuf.append((char) ch);       
        } else if ('0' <= ch && ch <= '9') {    
            sbuf.append((char) ch);       
        } else if (ch == '-' || ch == '_'|| ch == '.' || ch == '!' || ch == '~' || ch == '*'|| ch == '\'' || ch == '(' || ch == ')') {       
        sbuf.append((char) ch);       
        } else if (ch == '%') {   
            int cint = 0;       
            if ('u' != s.charAt(i + 1)) {       
            cint = (cint << 4) | val[s.charAt(i + 1)];       
            cint = (cint << 4) | val[s.charAt(i + 2)];       
            i += 2;       
            } else {       
                cint = (cint << 4) | val[s.charAt(i + 2)];       
                cint = (cint << 4) | val[s.charAt(i + 3)];       
                cint = (cint << 4) | val[s.charAt(i + 4)];       
                cint = (cint << 4) | val[s.charAt(i + 5)];       
                i += 5;       
            }       
            sbuf.append((char) cint);   
        } else {       
            sbuf.append((char) ch);       
        }       
        i++;       
        }       
        return sbuf.toString();       
    }  
	
	/**
	 * 
	 * @Title: getYearMonth 
	 * @Description: 出借提成的年月 
	 * @author lidaqing   
	 * @date 2014-11-5 下午5:11:53  
	 * @return
	 * String
	 */
	public static String getYearMonth(){
		String dateStr = dateToYearMonth(new Date());
		int dateYear = Integer.parseInt(dateStr.substring(0,4));
		String dateMonth = dateStr.substring(5,6);
		if(dateMonth.equals("0")){
			dateMonth = dateStr.substring(6,7);
		}else{
			dateMonth = dateStr.substring(5,7);
		}
		int dateMonths = Integer.parseInt(dateMonth);
		if(dateMonths<2){
			dateMonths = 12;
			dateYear-=1;
		}else{
			dateMonths-=1;
		}
		dateStr = dateYear+"-"+dateMonths;
		return dateStr;
	}
	
	/**
	 * @Title: getYieldDay 
	 * @Description: 返回两日期相差的天数 (赎回适用,出借需+1)
	 * @author lidaqing   
	 * @date 2014-6-25 上午10:29:39  
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return int 相差天数
	 * @throws java.text.ParseException
	 */
	public static int getDifferDay(String startDate,String endDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(startDate));
		long t1 = c.getTimeInMillis();
		c.setTime(sdf.parse(endDate));
		long t2 = c.getTimeInMillis();
		return Integer.parseInt(String.valueOf((t2-t1)/(24*60*60*1000)));
	}
	
	
	/**
	 * @Title: getYieldDay 
	 * @Description: 返回两日期相差的天数 (赎回适用,出借需+1)
	 * @author lidaqing   
	 * @date 2014-6-25 上午10:29:39  
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return int 相差天数
	 * @throws java.text.ParseException
	 */
	public static int getDifferDay(Date startDate,Date endDate) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		long t1 = c.getTimeInMillis();
		c.setTime(endDate);
		long t2 = c.getTimeInMillis();
		return Integer.parseInt(String.valueOf((t2-t1)/(24*60*60*1000)));
	}
	
	
	
	
	/**
	 * @Title:  getContractEndDate 
	 * @Description: 根据开始日期 和 相差天数 计算结束日期
	 * @author guofu   
	 * @date 2014-6-25 上午10:29:39  
	 * @param startDate 开始时间
	 * @param amount 相差天数
	 * @return endDate 结束时间
	 */
	public static Date getContractEndDate(Date startDate,int amount) {
		
		if (startDate == null ) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DAY_OF_MONTH, amount);
		c.getTime();
		
		return c.getTime();
	}
	
	/**
	 * 金额转成千分位格式
	 * @param str
	 * @return
	 */
	public static String getDecimalFormat(String str){
		 double  initValue=0;
       String outStr = "";
       if(str != null && !"".equals(str.trim())){
           initValue=Double.parseDouble(str);
           DecimalFormat  fmt = new DecimalFormat("##,###,###,###,##0.00");  
           double d;
           try {
        	   if(str.equals("0.00")){
        		   outStr = "0.00";
        	   }else{
	               d = Double.parseDouble(String.valueOf(initValue));
	               outStr = fmt.format(d);
        	   }
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return outStr;
   }
	

	/**
	 * 将金额转成对应的人民币大写
	 * @param money
	 * @return
	 */
	public static String toUpperRMB(double money) {  
	    char[] s1 = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};  
	    char[] s4 = {'分', '角', '元', '拾', '佰', '仟', '万', '拾', '佰', '仟', '亿', '拾', '佰', '仟', '万'};  
	    String str = String.valueOf(Math.round(money * 100 + 0.00001));  
	    String result = "";  
	    for (int i = 0; i <str.length(); i++) {  
		    int n = str.charAt(str.length() - 1 - i) - '0';  
		    result = s1[n] + "" + s4[i] + result;  
	    }  
        result = result.replaceAll("零仟", "零");  
        result = result.replaceAll("零佰", "零");  
        result = result.replaceAll("零拾", "零");  
        result = result.replaceAll("零亿", "亿");  
        result = result.replaceAll("零万", "万");  
        result = result.replaceAll("零元", "元");  
        result = result.replaceAll("零角", "零");  
        result = result.replaceAll("零分", "零");  
        result = result.replaceAll("零零", "零");  
        result = result.replaceAll("零亿", "亿");  
        result = result.replaceAll("零零", "零");  
        result = result.replaceAll("零万", "万");  
        result = result.replaceAll("零零", "零");  
        result = result.replaceAll("零元", "元");  
        result = result.replaceAll("亿万","亿");  
        result = result.replaceAll("零$", "");  
        result = result.replaceAll("元$", "元");  
        result = result.replaceAll("角$", "角");  
	    return result;  
    }
	
	/**
	 * list排序 第一个参数 要排序的列表，第二个字段(asc)升序还是降序(desc)，第三个字段到第5个字段要排序的 字段英字名称, 最多支持3列排序。
	 * 例子 listSort(list, "asc", "amount","name"，"time")
	 * @param list
	 * @param sort
	 * @param fieldNames
	 * @throws Exception
	 */
	public static <T> void listSort(List<T> list, final String sort, final String...fieldNames) throws Exception{  

		
		Collections.sort(list,new Comparator<T>(){
			Method method1 = null;
			Method method2 = null;
			public int compare(T arg0, T arg1) {

				Field field= null;
				String type= "";
				int i = 0;
				int j = 0;
				try {
					method1 = Class.forName(arg0.getClass().getName()).getDeclaredMethod("get" + fieldNames[0].substring(0,1).toUpperCase() +fieldNames[0].substring(1));
					method2 = Class.forName(arg1.getClass().getName()).getDeclaredMethod("get" + fieldNames[0].substring(0,1).toUpperCase() +fieldNames[0].substring(1));
					
					field = Class.forName(arg0.getClass().getName()).getDeclaredField(fieldNames[0]);
					type = field.getType().getName();
					
					if (sort.equals("asc")) {
						if (type.equals("java.lang.String")) {
							i = ((String) (method1.invoke(arg0))).compareTo((String) (method2.invoke(arg1)));
						} else if (type.equals("java.lang.Integer")) {
							i = ((Integer) (method1.invoke(arg0))).compareTo((Integer) (method2.invoke(arg1)));
						} else if (type.equals("java.lang.Long")) {
							i = ((Long) (method1.invoke(arg0))).compareTo((Long) (method2.invoke(arg1)));
						} else if (type.equals("java.util.Date")) {
							i = ((Date) (method1.invoke(arg0))).compareTo((Date) (method2.invoke(arg1)));
						} else if (type.equals("java.math.BigDecimal")) {
							i = ((BigDecimal) (method1.invoke(arg0))).compareTo((BigDecimal) (method2.invoke(arg1)));
						}
					} else {
						if (type.equals("java.lang.String")) {
							i = ((String) (method2.invoke(arg1))).compareTo((String) (method1.invoke(arg0)));
						} else if (type.equals("java.lang.Integer")) {
							i = ((Integer) (method2.invoke(arg1))).compareTo((Integer) (method1.invoke(arg0)));
						} else if (type.equals("java.lang.Long")) {
							i = ((Long) (method2.invoke(arg1))).compareTo((Long) (method1.invoke(arg0)));
						} else if (type.equals("java.util.Date")) {
							i = ((Date) (method2.invoke(arg1))).compareTo((Date) (method1.invoke(arg0)));
						} else if (type.equals("java.math.BigDecimal")) {
							i = ((BigDecimal) (method2.invoke(arg1))).compareTo((BigDecimal) (method1.invoke(arg0)));
						}
					}
					if (fieldNames.length >= 2) {
						if (i == 0) {
					
							method1 = Class.forName(arg0.getClass().getName()).getDeclaredMethod("get" + fieldNames[1].substring(0,1).toUpperCase() +fieldNames[1].substring(1));
							method2 = Class.forName(arg1.getClass().getName()).getDeclaredMethod("get" + fieldNames[1].substring(0,1).toUpperCase() +fieldNames[1].substring(1));
							
							field = Class.forName(arg0.getClass().getName()).getDeclaredField(fieldNames[1]);
							type = field.getType().getName();
							
							if (sort.equals("asc")) {
								if (type.equals("java.lang.String")) {
									j = ((String) (method1.invoke(arg0))).compareTo((String) (method2.invoke(arg1)));
								} else if (type.equals("java.lang.Integer")) {
									j = ((Integer) (method1.invoke(arg0))).compareTo((Integer) (method2.invoke(arg1)));
								} else if (type.equals("java.lang.Long")) {
									j = ((Long) (method1.invoke(arg0))).compareTo((Long) (method2.invoke(arg1)));
								} else if (type.equals("java.util.Date")) {
									j = ((Date) (method1.invoke(arg0))).compareTo((Date) (method2.invoke(arg1)));
								} else if (type.equals("java.math.BigDecimal")) {
									j = ((BigDecimal) (method1.invoke(arg0))).compareTo((BigDecimal) (method2.invoke(arg1)));
								}
							} else {
								if (type.equals("java.lang.String")) {
									j = ((String) (method2.invoke(arg1))).compareTo((String) (method1.invoke(arg0)));
								} else if (type.equals("java.lang.Integer")) {
									j = ((Integer) (method2.invoke(arg1))).compareTo((Integer) (method1.invoke(arg0)));
								} else if (type.equals("java.lang.Long")) {
									j = ((Long) (method2.invoke(arg1))).compareTo((Long) (method1.invoke(arg0)));
								} else if (type.equals("java.util.Date")) {
									j = ((Date) (method2.invoke(arg1))).compareTo((Date) (method1.invoke(arg0)));
								} else if (type.equals("java.math.BigDecimal")) {
									j = ((BigDecimal) (method2.invoke(arg1))).compareTo((BigDecimal) (method1.invoke(arg0)));
								}
							}
							
							return j;
						}
						
						if (fieldNames.length >= 3) {
							if (j == 0) {
						
								method1 = Class.forName(arg0.getClass().getName()).getDeclaredMethod("get" + fieldNames[2].substring(0,1).toUpperCase() +fieldNames[2].substring(1));
								method2 = Class.forName(arg1.getClass().getName()).getDeclaredMethod("get" + fieldNames[2].substring(0,1).toUpperCase() +fieldNames[2].substring(1));
								
								field = Class.forName(arg0.getClass().getName()).getDeclaredField(fieldNames[2]);
								type = field.getType().getName();
								
								if (sort.equals("asc")) {
									if (type.equals("java.lang.String")) {
										j = ((String) (method1.invoke(arg0))).compareTo((String) (method2.invoke(arg1)));
									} else if (type.equals("java.lang.Integer")) {
										j = ((Integer) (method1.invoke(arg0))).compareTo((Integer) (method2.invoke(arg1)));
									} else if (type.equals("java.lang.Long")) {
										j = ((Long) (method1.invoke(arg0))).compareTo((Long) (method2.invoke(arg1)));
									} else if (type.equals("java.util.Date")) {
										j = ((Date) (method1.invoke(arg0))).compareTo((Date) (method2.invoke(arg1)));
									} else if (type.equals("java.math.BigDecimal")) {
										j = ((BigDecimal) (method1.invoke(arg0))).compareTo((BigDecimal) (method2.invoke(arg1)));
									}
								} else {
									if (type.equals("java.lang.String")) {
										j = ((String) (method2.invoke(arg1))).compareTo((String) (method1.invoke(arg0)));
									} else if (type.equals("java.lang.Integer")) {
										j = ((Integer) (method2.invoke(arg1))).compareTo((Integer) (method1.invoke(arg0)));
									} else if (type.equals("java.lang.Long")) {
										j = ((Long) (method2.invoke(arg1))).compareTo((Long) (method1.invoke(arg0)));
									} else if (type.equals("java.util.Date")) {
										j = ((Date) (method2.invoke(arg1))).compareTo((Date) (method1.invoke(arg0)));
									} else if (type.equals("java.math.BigDecimal")) {
										j = ((BigDecimal) (method2.invoke(arg1))).compareTo((BigDecimal) (method1.invoke(arg0)));
									}
								}
								
								return j;
							}
						}
					}
					
				}catch (NoSuchMethodException | SecurityException
						| ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return i;
			}
		});
	}
	
	
    /**
     * excel 下载
     * @author guofu
     */
	public static void downFile(HttpServletResponse response,File file){
		try {
			FileInputStream fs = new FileInputStream(file);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\""+new String(file.getName().getBytes("gbk"), "iso8859-1")+"\"");
			PrintWriter out = response.getWriter();
			int b = 0;
			while ((b = fs.read()) != -1) {
				out.write(b);
			}
			fs.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
    /**
     * 去特殊字符“-”,“/” 日期，月份不足2位 前补0
     * @author guofu
     */
	public static  String dateFormatDelete (String date) {
    	
    	if (date != null && !"".equals(date)) {
    		date = date.replace("/", "-");
    		String dateArray[] = date.split("-");
    		
    		if (dateArray.length == 3) {
    			String month = dateArray[1];
    			if (month.length()==1) {
    				month = "0"+month;
    			}
    			String day = dateArray[2];
    			if (day.length()==1) {
    				day = "0"+day;
    			}
    			date = dateArray[0] + month + day;
    		}

    	}
    	return date;
    }
	
    /**
     * 判断字符串是否为数字
     * @author guofu
     */
	public static boolean isNumeric(String str){
		for (int i = str.length();--i>=0;){   
			if (!Character.isDigit(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	
    /** 
     * 获得一个UUID 
     * @return String UUID 
     * @author guofu
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.replace("-", ""); 
    } 
}
