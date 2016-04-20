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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil {

	private static HttpClient hc = new DefaultHttpClient();  //初始化一个HTTP的客户端对象
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
//			String result = postBody("http://10.100.17.177:7777/gqi_sms/interaction/requestBatchMsgSend",JsonTransferUtil.toJSONString(list));
//			System.out.println(result);
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
	public static void sendMSG(List<Map<String, String>> list){
		/*try {
			String 	result = postBody(CoreConstants.BACKEND_SMS_URL,JsonTransferUtil.toJSONString(list));
			if(!JSONObject.fromObject(result).getString("retCode").equalsIgnoreCase("success")){
				String str = JSONObject.fromObject(result).getString("msg");
				str = new String(str.getBytes("gbk"),"utf-8");
				System.out.println("---------------------------------------------------------");
				log.error("短信接口后台返回错误信息:"+str);
				System.out.println("---------------------------------------------------------");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("短信接口后台异常:"+e);
		}*/
	}


}
