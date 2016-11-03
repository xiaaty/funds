package com.gqhmt.pay.fuiou.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.MultiValueMap;

public class HttpClientUtil {
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	// 功能: postBody形式发送数据
	// @param urlPath 对方地址
	// @param json 要传送的数据
	// @return 2016-10-25注释掉
	// @throws Exception
/*	public static String postBody(String urlPath, String json) throws Exception {
		try {
			URL url = new URL(urlPath);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			//设置连接主机时间为1S
			urlConnection.setConnectTimeout(1000);
			//设置读取数据时间为5S
			urlConnection.setReadTimeout(5000);
			// 设置doOutput属性为true表示将使用此urlConnection写入数据
			urlConnection.setDoOutput(true);
			// 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
			urlConnection.setRequestProperty("content-type","application/x-www-form-urlencoded");
			// 得到请求的输出流对象
			OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());
			// 把数据写入请求的Body
			out.write(json);
			out.flush();
			out.close();
			// 从服务器读取响应
			InputStream inputStream = urlConnection.getInputStream();
			String encoding = urlConnection.getContentEncoding();
			String body = IOUtils.toString(inputStream, encoding);
			inputStream.close();
			return body;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}  
	}*/

	public static void sendMsgOrNotice(List<Map<String, String>> list,Integer msgType){
		try {
			String 	result = "";
			if(msgType!=null&&msgType==CoreConstants.SMS_DX){
//				result = postBody(CoreConstants.BACKEND_SMS_URL,JsonUtil.toJson(list));
				result = sendBody(CoreConstants.BACKEND_SMS_URL,list);
			}
			if(msgType!=null&&msgType==CoreConstants.SMS_NOTICE){
				if(list.size()>1){
//					result = postBody(CoreConstants.BACKEND_NOTICE_URL,JsonUtil.toJson(list));
					result = sendBody(CoreConstants.BACKEND_NOTICE_URL,list);
				}
			}
			log.info("【短信后台返回结果】："+result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("短信接口后台异常:"+e);
		}
	}

//	---------------------------------新增------------------------------------------------------------------
	/**
	 * 批量发送
	 * @param url
	 * @param smsParamList
	 * @return
	 */
	public static String sendBody(String url,List<Map<String, String>> smsParamList){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		try {
			return restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(new ObjectMapper().writeValueAsString(smsParamList), headers), String.class).getBody();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			log.error("json转化失败", e);
			return null;
		} catch (Exception e){
			e.printStackTrace();
			log.error("发送失败", e);
			return null;
		}
	}

	/**
	 * 单条发送
	 * @param url
	 * @param params
	 * @return
	 */
	public static String send(String url, MultiValueMap<String,Object> params){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		try {
			return restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(params, headers), String.class).getBody();
		}catch (Exception e){
			e.printStackTrace();
			log.error("发送失败", e);
			return null;
		}
	}
}
