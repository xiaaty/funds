package com.gqhmt.extServInter.fetchService;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.core.util.ResourceUtil;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Filename:    com.gqhmt.extServInter.fetchService.FetchDataUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/16 11:36
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/16  于泳      1.0     1.0 Version
 */
@Component
public class FetchDataService {



    public  <T> List<T> featchData(Class<T> tClass, String urlType, Map<String, String> map) throws FssException {
        String url = this.parseUrl(urlType,map);
        InputStream inputStream = this.sendData(url);
        String result = this.parseResponse(inputStream);

        return JsonUtil.getInstance().parseJsonToList(result,tClass);
    }

    public  <T> T featchDataSingle(Class<T> tClass, String utlType, Map<String, String> map){
        return null;

    }

    public  <T> T featchDataSingle(Class<T> tClass, String urlType,String key, String param) throws FssException {
        String url = this.parseUrl(urlType,key,param);
        InputStream inputStream = this.sendData(url);
        String result = this.parseResponse(inputStream);
        return JsonUtil.getInstance().parseJson(result,tClass);
    }

    public  <T> T featchDataSingleNotJson(Class<T> tClass, String urlType,String key, String param) throws FssException {
        String url = this.parseUrl(urlType,key,param);
        InputStream inputStream = this.sendData(url);
        String result = this.parseResponse(inputStream);
        return parseBaseType(result ,tClass);
    }



    public  Map<String,String> featchDataMap(String utlType, Map<String, String> map){
        return null;

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

    private String parseUrl(String urlType,String key,String param) throws FssException {
        String url = ResourceUtil.getValue("config.appContext",urlType);
        if(url == null || "".equals(url)){
            throw new FssException("90099007");
        }

            url = this.parseParam(url,key,param);


        return url;
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



    private InputStream sendData(String urlParam) throws FssException {
        PrintWriter out = null;
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
            URL realUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection)realUrl.openConnection();
            // 设置请求
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");

            conn.setRequestMethod("GET");

            //            conn.setReadTimeout(60*1000);
            conn.setDoInput(true);
            if(param == null){
                conn.setDoOutput(false);
            }else{
                conn.setDoOutput(true);
                // 获得对象输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // 输出流缓冲
                out.flush();
            }




            // 定义输入流得到响应
            return conn.getInputStream();
        } catch (MalformedURLException e) {
            throw new FssException("90099004");
        } catch (IOException e) {
            throw new FssException("90099004");
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                System.err.println("关闭POST请求错误!" + e);
            }
        }
    }

    private String  parseResponse(InputStream is) throws FssException {
        if(is == null){
            throw new FssException("90099009");
        }
        BufferedReader in = null;
        String result = "";
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(is));
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
}
