package com.gqhmt.core.util;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

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


    private JsonUtil(){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

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

    public <T> List<T> parseJsonToList(String json, Class<T> tClass){
        try {

            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class,tClass);
            List<T> list = (List<T>) objectMapper.readValue(json, javaType);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
