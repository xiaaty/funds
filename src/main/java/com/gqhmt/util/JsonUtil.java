package com.gqhmt.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

/**
 * Filename:    com.gqhmt.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2014/12/11 14:00
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2014/12/11  于泳      1.0     1.0 Version
 */
public class JsonUtil {

    private static final JsonUtil instance = new JsonUtil();

    private JsonGenerator jsonGenerator = null;

    private ObjectMapper objectMapper = new ObjectMapper();


    private JsonUtil(){}

    public static JsonUtil getInstance(){
        return instance;
    }

    public String getJson(Object obj){
        Writer strWriter = new StringWriter();
        try {
            objectMapper.writeValue(strWriter, obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strWriter.toString();
    }

    public <T> T parseJson(String json, Class<T>  class1){
        try {
            T t = objectMapper.readValue(json, class1);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    /** 
	* @Description: 将字符串以json格式输出
	* @param jsonStr
	* @throws java.io.IOException
	* @return void    
	*/ 
	public static void printStr(HttpServletResponse httpServletResponse,String jsonStr) throws IOException{
		httpServletResponse.setCharacterEncoding("UTF-8");
		httpServletResponse.setContentType("text/x-json;charset=UTF-8");
		PrintWriter pw = httpServletResponse.getWriter();
		pw.print(jsonStr);
		pw.flush();
		pw.close();
	}
    
	
	/*public static String toJson(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
	}
	*/
	
}
