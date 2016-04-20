package com.gqhmt.core.connection;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename:    com.gqhmt.core.connection
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/19 16:42
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/19  于泳      1.0     1.0 Version
 */
public class UrlConnectUtil {


    private static UrlConnectUtil urlConnectUtil = new UrlConnectUtil();

    private UrlConnectUtil(){}

    public static <T> T sendDataReturnAutoSingleObject(Class<T> tClass, String urlType, Object object) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType);
        boolean isParam = false;
        if(url.contains("{")){
            isParam = true;
        }

        if(isParam){

            if(object != null &&  object instanceof Map) return  sendDataReturnSingleObjcet(tClass,urlType,(Map<String, String>) object);

            throw new FssException("90099008");
        }else{
            if(object == null && object instanceof  Map)  return sendDataReturnSingleObjcet(tClass,urlType,(Map<String, String>) object);

            if(object instanceof  String) return  sendJsonDataReturnObject(tClass,urlType,(String)object);

            return sendJsonDataReturnObject(tClass,urlType,JsonUtil.getInstance().getJson(object));

        }
    }


    public static <T> T sendDataReturnSingleObjcet(Class<T> tClass, String urlType, Map<String, String> map) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType,map);
        InputStream inputStream = urlConnectUtil.sendData(url);
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }

    public static <T> T sendDataReturnSingleObjcet(Class<T> tClass, String urlType, String  key,String  param) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType,key,param);
        InputStream inputStream = urlConnectUtil.sendData(url);
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }


    public static <T> T sendDataReturnSingleObjcetNotJson(Class<T> tClass, String urlType, String key, String param) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType,key,param);
        InputStream inputStream = urlConnectUtil.sendData(url);
        String result = urlConnectUtil.parseResponse(inputStream);
        return urlConnectUtil.parseBaseType(result ,tClass);
    }


    public static <T> List<T> sendDataReturnObjectList(Class<T> tClass, String urlType, Map<String, String> map) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType,map);
        InputStream inputStream = urlConnectUtil.sendData(url);
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJsonToList(result,tClass);
    }


    public static <T> T sendJsonDataReturnObject(Class<T> tClass, String urlType, Map<String,String> param) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType);
        InputStream inputStream = urlConnectUtil.sendJsonData(url,JsonUtil.getInstance().getJson(param));
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }

    public static <T> T sendJsonDataReturnObject(Class<T> tClass, String urlType, String param) throws FssException {
        String url = urlConnectUtil.parseUrl(urlType);
        InputStream inputStream = urlConnectUtil.sendJsonData(url,param);
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }



    public static <T> T sendJsonDataReturnObjectUrl(Class<T> tClass, String url, String param) throws FssException {
        InputStream inputStream = urlConnectUtil.sendJsonData(url,param);
        String result = urlConnectUtil.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }

    private String  parseResponse(InputStream is) throws FssException {
        if(is == null){
            throw new FssException("90099009");
        }
        BufferedReader in = null;
        String result = "";
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(is,"utf-8"));
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private <T> T parseBaseType(String result, Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
            if(t instanceof Long){
                Long tmp  = (Long)t;
                tmp = Long.valueOf(result);
            }else if(t instanceof Integer){
                Integer tmp = (Integer) t;
                tmp = Integer.valueOf(result);
            }else if(t instanceof Short){
                Short tmp = (Short) t;
                tmp = Short.valueOf(result);
            }else if(t instanceof Double){
                Double tmp = (Double) t;
                tmp = Double.parseDouble(result);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        return t;
    }



    private String parseUrl(String urlType,Map<String, String> map) throws FssException {
        String url = ResourceUtil.getValue("config.appContext",urlType);
        if(url == null || "".equals(url)){
            throw new FssException("90099007");
        }

        if(map != null && map.size()>0){
            url = this.parseParam(url,map);
        }
        return url;
    }


    private String parseUrl(String urlType) throws FssException {
        String url = ResourceUtil.getValue("config.appContext",urlType);
        LogUtil.info(this.getClass(),"获取地址:"+url);
        return url;
    }

    private String parseUrl(String urlType,String key,String param) throws FssException {
        String url = ResourceUtil.getValue("config.appContext",urlType);
        if(url == null || "".equals(url)){
            throw new FssException("90099007");
        }

        url = this.parseParam(url,key,param);


        return url;
    }


    private String  parseParam(String url,String key,String param) throws FssException {
        if(param == null || param.length() == 0){
            throw new FssException("90099008");
        }

        try {
            url = url.replace("{"+key+"}", URLEncoder.encode(param,"utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw  new FssException("90099010",e);
        }

        return  url;
    }


    private String  parseParam(String url,Map<String, String> map) throws FssException {
        if(map == null || map.size() == 0){
            throw new FssException("90099008");
        }

        Set<String> set = map.keySet();


        try {
            for(String tmp:set){
                url = url.replace("{"+tmp+"}", URLEncoder.encode(map.get(tmp),"utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            throw  new FssException("90099010",e);
        }


        return  url;
    }


    private  InputStream getData(String url) throws IOException {
        URLConnection conn = connectionMethodGet(url);
        return conn.getInputStream();
    }

    private  InputStream postData(String url,String param) throws IOException {
        URLConnection conn = connectionMethodPost(url);
        this.postData(conn,param);
        return conn.getInputStream();
    }

    private  InputStream postDataByJson(String url,String param) throws IOException {
        URLConnection conn = connectionMethodPostByJson(url);
        this.postData(conn,param);
        return conn.getInputStream();
    }

    private void postData(URLConnection conn,String param) throws IOException {
        LogUtil.info(this.getClass(),"发送数据:"+conn.getURL()+"参数:"+param);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        // 发送请求参数
        out.print(param);
        // 输出流缓冲
        out.flush();
        out.close();
    }


    private URLConnection connectionMethodGet(String  url) throws IOException {
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        // 设置请求
        this.connectPropertyAcceptAll(conn);

        conn.setDoInput(true);
        conn.setDoOutput(false);
        return conn;
    }

    private URLConnection connectionMethodPost(String  url) throws IOException {
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        // 设置请求
        this.connectPropertyAcceptAll(conn);
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        return conn;
    }



    private URLConnection connectionMethodPostByJson(String  url) throws IOException {
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
        conn.setRequestMethod("POST");
        // 设置请求
        this.connectPropertyByJson(conn);

        conn.setDoOutput(true);
        return conn;
    }


    private void connectProperty(URLConnection conn){
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Charset", "UTF-8");
        conn.setRequestProperty("Accept-Encoding", "gzip");
        //                    conn.setReadTimeout(60*1000);
        
        conn.setDoInput(true);
    }

    private void connectPropertyAcceptAll(URLConnection conn){
        this.connectProperty(conn);
    }
    private void connectPropertyByJson(URLConnection conn){
        conn.setRequestProperty("Content-Type","application/json");
        this.connectProperty(conn);
    }


    private InputStream sendJsonData(String  url,String param) throws FssException {

        if(param == null || url == null){
            throw new FssException("90099007");
        }

        try {
            return this.postDataByJson(url,param);
        } catch (MalformedURLException e) {
            throw new FssException("90099004");
        } catch (IOException e) {
            throw new FssException("90099004");
        }
    }

    private  InputStream sendData(String urlParam) throws FssException {
        String param = null;
        String url = urlParam;
        if(urlParam == null){
            throw new FssException("90099007");
        }
        if(urlParam.contains("?")){
            String[] urlTmp = urlParam.split("\\?");
            url = urlTmp[0];
            param = urlTmp[1];
        }

        try {
            if(param == null){
                return this.getData(url);
            }else{
                return this.postData(url,param);
            }
        } catch (MalformedURLException e) {
            throw new FssException("90099004");
        } catch (IOException e) {
            throw new FssException("90099004");
        }
    }
}
