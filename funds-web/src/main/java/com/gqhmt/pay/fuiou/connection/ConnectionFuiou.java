package com.gqhmt.pay.fuiou.connection;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.XmlUtil;
import com.gqhmt.pay.exception.ApplicationNotConnectionRemoteUrl;
import com.gqhmt.pay.fuiou.util.SecurityUtils;
import com.gqhmt.pay.unionPay.connection.Connection;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Filename:    com.fuiou.connection
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/31 16:41
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/31  于泳      1.0     1.0 Version
 */
public class ConnectionFuiou {


    public static Map send(String url,Map map) throws ApplicationNotConnectionRemoteUrl {



        String param = getParam(map);

        LogUtil.debug(ConnectionFuiou.class,"第三方支付:"+url+":发送数据参数:"+(param == null?"无参数":param.replace("&","|")));
//        LogUtil.debug(ConnectionFuiou.class,"thirdpaty__"+url+":send:"+param);
        try{
            String result = sendHttpRequest(url,param);
            LogUtil.info(ConnectionFuiou.class,"第三方支付:"+url+":result:"+result);
            Map reMap = getResult(result);
            Map apMap = (Map)reMap.get("ap");
            LogUtil.info(ConnectionFuiou.class,"返回结果:"+apMap);
            String sign = (String)apMap.get("signature");
            check(result,sign);
            Map resultMap = (Map)apMap.get("plain");
            return resultMap;
        } catch (IOException e) {
            throw new ApplicationNotConnectionRemoteUrl("90099004",e);
        }

    }

    private static String getParam(Map map){
        Set<String> keySet = map.keySet();
        StringBuffer result = new StringBuffer();
        for(String key:keySet){
            result.append(key);
            result.append("=");
            try {
                String t = (String) map.get(key);
                if(t!= null && !"".equals(t)){
                    result.append(URLEncoder.encode(t, "utf-8"));
                }else{
                    result.append("");
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.append("&");
        }

        return result.toString();
    }

    /**
     * 处理response数据，返回map
     * @param result
     * @return
     */
    private static Map getResult(String result){

        return  XmlUtil.getMap(result);
    }

    /**
     * 校验签名
     * @param result
     */
    private static void check(String result,String sign){
         result = result.substring(result.indexOf("<plain>"),result.lastIndexOf("<signature>"));

        boolean isCheck = SecurityUtils.verifySign(result,sign);

        if (!isCheck) {
            throw new RuntimeException("验签失败");
        }

    }


    private static String parseResponse(InputStream is) throws IOException {
        if(is == null){
            throw new IOException("通道为空");
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
            LogUtil.error(ConnectionFuiou.class,e);
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (Exception e){
                    LogUtil.error(ConnectionFuiou.class,e);
                }
            }
        }
        return result;
    }

    /**
     * 发送数据到大钱
     * @param url
     * @param param
     * @return
     * @throws Exception
     */
    private static String sendHttpRequest(String url, String param) throws IOException {
        PrintWriter out = null;
        long startTime = new Date().getTime();
        String result = "";
        InputStream is = null;
        URL realUrl = null;
        try {
            realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            // 设置请求
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
//            conn.setReadTimeout(60*1000);
            // 获得对象输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // 输出流缓冲
            out.flush();
            // 定义输入流得到响应

            is =  conn.getInputStream();

            result = parseResponse(is);
            long endTime = new Date().getTime();
            LogUtil.info(ConnectionFuiou.class,"连接富友："+url+"?"+param+":返回结果:"+result+"时长:"+(endTime-startTime));
            return result;
        } catch (MalformedURLException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                LogUtil.error(ConnectionFuiou.class,"关闭POST请求错误!",e);
            }
            if(is != null){
                try {
                    is.close();
                }catch (Exception e){
                    LogUtil.error(ConnectionFuiou.class,"关闭相应结果流错误!",e);
                }
            }
        }


        // 打开连接




        /*catch () {
            System.err.println("发送POST请求错误!" + e);
            throw e;
        } */

    }
}
