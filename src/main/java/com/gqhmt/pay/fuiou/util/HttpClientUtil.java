package com.gqhmt.pay.fuiou.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import net.sf.json.JSONObject;
import com.gqhmt.core.util.JsonTransferUtil;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.pay.exception.AppException;
public class HttpClientUtil {
	//private static HttpClient hc = new DefaultHttpClient();  //初始化一个HTTP的客户端对象
	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	public static void main(String[] args) {
		//测试查询模板内容 getTempContant
		testRequestGetTempContant();
		//测试单条发,可以同一个模板多条
//		testRequestMsgSend();
		//测试批量发，不同模板批量
		testRequestBathMsgSend();
	}
	
	/**
	 * 根据商户系统编码和模板编码，返回模板内容
	 */
	public static void testRequestGetTempContant(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("sysCode","0001");			//商户系统编码，在平台系统查看
		NameValuePair nv2 = new BasicNameValuePair("tempCode","0026");			//商户模板编码，在平台系统查看
		NameValuePair nv3 = new BasicNameValuePair("jsonPrams","[{'0':'aa','1':'bb'}]"); //模板内容中，参数
		params.add(nv1);
		params.add(nv2);
		params.add(nv3);
		try {
			String result = post("http://10.100.17.177:7777/gqi_sms/interaction/getTempContant",params);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--");
	}
	
	/**
	 * 单条发送
	 */
	public static void testRequestMsgSend(){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		NameValuePair nv1 = new BasicNameValuePair("sysCode","0001");			//商户系统编码，在平台系统查看
		NameValuePair nv2 = new BasicNameValuePair("tempCode","0026");			//商户模板编码，在平台系统查看
		NameValuePair nv3 = new BasicNameValuePair("phoneNo","15011389819");	//手机号，多个用","分开
		NameValuePair nv4 = new BasicNameValuePair("jsonPrams","[{'0':'aa','1':'bb'}]"); //模板内容中，参数
		params.add(nv1);
		params.add(nv2);
		params.add(nv3);
		params.add(nv4);
		try {
			String result = post("http://10.100.17.177:7777/gqi_sms/interaction/requestMsgSend",params);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--");
	}
	
	/**
	 * 批量发送
	 */
	public static void testRequestBathMsgSend(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> baMap = new HashMap<String, String>();
		baMap.put("sysCode", "0001");	//商户系统编码，在平台系统查看
		baMap.put("tempCode", "0026");	//商户模板编码，在平台系统查看
		list.add(baMap);
		for (int i = 0; i < 1; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("phoneNo", "15011389819");	//手机号，多个用","分开
			map.put("0", "麦先生");		//模板内容中，参数0
			map.put("1", "您好"); 		//模板内容中，参数1
			list.add(map);
		}
		try {
			String result = postBody("http://10.100.17.177:7777/gqi_sms/interaction/requestBatchMsgSend",JsonTransferUtil.toJSONString(list));
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--");
	}
	
	// 功能: postBody形式发送数据
	// @param urlPath 对方地址
	// @param json 要传送的数据
	// @return
	// @throws Exception
	public static String postBody(String urlPath, String json) throws Exception {
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
	}

	// 功能: Post形式发送请求
	// @param url 请求地址
	// @param params 请求中传递的参数
	// @return
	@SuppressWarnings({ "rawtypes", "unchecked" }) 
	public static String post(String url, List params) {
		HttpClient hc = new DefaultHttpClient();  //初始化一个HTTP的客户端对象
		StringBuffer buff = new StringBuffer("");
		try {
			// Post请求
			HttpPost httppost = new HttpPost(url);
			// 设置参数
			httppost.setEntity(new UrlEncodedFormEntity(params));
			// 发送请求
			HttpResponse httpresponse = hc.execute(httppost);
			httpresponse.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == httpresponse.getStatusLine().getStatusCode()) {
				HttpEntity entity = httpresponse.getEntity();
				buff.append(EntityUtils.toString(entity));
			}
			httppost.abort();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
	/**
	 * 批量发送
	 */
	public static void testRequestShareBatchMsgSend(){
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> baMap = new HashMap<String, String>();
		baMap.put("sysCode", "0001");	//商户系统编码，在平台系统查看
		list.add(baMap);
		for (int i = 0; i < 1; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("phoneNo", "15011389819");	//手机号，多个用","分开
			map.put("0", new String("麦先生"));		//模板内容中，参数0
			map.put("1", "您好"); 		//模板内容中，参数1
			map.put("tempCode", "0026");	//商户模板编码，在平台系统查看
			map.put("sendType", "1");
			list.add(map);
		}
		
		for (int i = 0; i < 1; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("0", new String("麦先生"));	//模板内容中，参数0
			map.put("1", "您好"); 				//模板内容中，参数1
			map.put("title", "满标了");			//商户模板编码，在平台系统查看
			map.put("customerId", "1111");		//商户模板编码，在平台系统查看
			map.put("tempCode", "0026");		//商户模板编码，在平台系统查看
			map.put("sendType", "2");		
			list.add(map);
		}

		try {
			String result = postBody("http://10.100.17.177:7777/gqi_sms/interaction/requestShareBatchMsgSend",JsonTransferUtil.toJSONString(list));
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--");
	}
	public static void sendMsgOrNotice(List<Map<String, String>> list,Integer msgType){
		try {
			String 	result = "";
			if(msgType!=null&&msgType==CoreConstants.SMS_DX){
				result = postBody(CoreConstants.BACKEND_SMS_URL,JsonUtil.toJson(list));
				
			}
			if(msgType!=null&&msgType==CoreConstants.SMS_NOTICE){
				if(list.size()>1){
					result = postBody(CoreConstants.BACKEND_NOTICE_URL,JsonUtil.toJson(list));
				}
			}
			log.info("【短信后台返回结果】："+result);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("短信接口后台异常:"+e);
		}
	}
	
	public static boolean byPostMethodJSON(String url, String params, String urlEncoded) {
		// 创建线程安全的httpClient
		HttpClient httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());
		// 创建一个HttpGet请求，作为目标地址。
		HttpPost httpPost = new HttpPost(url);
		try {
			if (params != null ) {
				// 格式化参数列表并提交
				httpPost.addHeader("Content-type","application/json");
				StringEntity stringEntity = new StringEntity(params,urlEncoded);
				httpPost.setEntity(stringEntity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				String string = EntityUtils.toString(entity);
				JSONObject fromObject = JSONObject.fromObject(string);
				if("0000".equals(fromObject.getString("code"))){
					log.info("===result===="+string);
					return true;
				}else{
					log.info("===result===="+string);
					return false;
				}
				
			}else{
				throw new AppException("推送失败！");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.error("【推送请求出现异常】："+e.getMessage());
			throw new AppException("连接错误--"+e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			log.error("【推送请求出现异常】："+e.getMessage());
			throw new AppException("连接错误--"+e.getMessage());
		} finally {
			releaseSource(null, httpPost, httpClient);
		}
	}
	
	public static void releaseSource(HttpGet httpGet, HttpPost httpPost,HttpClient httpClient) {
		if (httpGet != null) {
			httpGet.abort();
		}
		if (httpPost != null) {
			httpPost.abort();
		}
		if (httpClient != null) {
			httpClient.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * 批量发送,单一发送在for串调用一次就可以了
	 */
	public static void testRequestAppBatchMsgSend(List<Map<String, String>> list){
//		list = new ArrayList<Map<String, String>>();
//		for (int i = 5; i < 10; i++) {
//			Map<String, String> map = new HashMap<String, String>();
//			map.put("customerId", i+"");	//客户号
//			map.put("title", "5");			//标题
//			map.put("content", "内容"); 		//内容
//			map.put("noticeType", "4"); 	//类型，1新增客户2新增订单3即将到期4系统消息
//			map.put("adviserid", "4"); 		//顾问oa帐号
//			
//			list.add(map);
//		}

		try {
			//生产地址：http://sms.gqhmt.com/
			//测试地址：http://10.100.200.103:8101
			String result = postBody(
					CoreConstants.BACKEND_SMS_URL,
					JsonTransferUtil.toJSONString(list));
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("--");
		
	}
}
