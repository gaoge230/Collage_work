package com.gao.connectServer;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Handler;
import android.os.Message;
/**
 * 
 * @author 高歌
 * @Time 2017.2.21
 *
 */
public class ConnectServer {
	/** Called when the activity is first created. */
	// 模拟器自己把自己当成localhost了，服务器应该为
	private static String strUrl = "http://10.158.193.164:8080/EZN_Server/queryid.action";
	private String reCode = null;

	/**
	 * 连接服务器，并获得服务器传过来的json数据。
	 * 
	 * @param handler
	 * @param name
	 *            遥控器的name
	 */
	public void getPostServerData(final Handler handler, final String name) {

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub //
		 * 使用UrlConnection请求服务器将用户名密码发送给服务器验证 try {
		 * 
		 * URL url = new URL(strUrl); HttpURLConnection connection =
		 * (HttpURLConnection) url .openConnection();
		 * connection.setRequestMethod("POST");
		 * //connection.setConnectTimeout(100* 1000);// 设置连接超时 // 设置一些请求头信息
		 * String body = "queryname=" + name;
		 * connection.setRequestProperty("Content-Length", body.length() +
		 * "");// 设置请求头 //connection.setRequestProperty("Cathe-Control",
		 * "max-age=0"); // connection.setRequestProperty("Origin",
		 * "http://地址");
		 * 
		 * // 设置urlConnection可以写请求内容 connection.setDoOutput(true);
		 * connection.setDoInput(true); // 获取yigeOutputStream往里面传数据
		 * //connection.getOutputStream().write(body.getBytes());
		 * 
		 * //DataOutputStream dataOutputStream = new
		 * DataOutputStream(connection.getOutputStream());
		 * //dataOutputStream.writeBytes("queryname="+URLEncoder.encode(name,
		 * "UTF-8"));
		 * 
		 * if (connection.getResponseCode() == 200) { InputStream inputStream =
		 * connection.getInputStream(); reCode =
		 * StreamUtils.streamToString(inputStream);
		 * 
		 * }
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } } }).start();
		 */

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					// 创建连接
					HttpClient client = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(strUrl);
					// 设置参数，防html表单提交
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("queryNameJson", name));
					HttpResponse httpResponse = null;

					httpPost.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
					httpResponse = client.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						System.out.println("连接服务器结果：200");
						// 取出字符串
						HttpEntity entity = httpResponse.getEntity();
						String entityString = EntityUtils.toString(entity,
								"utf-8");
						System.out.println("来自服务器的entityString:" + entityString);
						JSONObject json = new JSONObject(entityString);
						System.out.println(json.get("queryUserCodeJson"));						
						// JSONArray jsonArray = new JSONArray(entityString);
						reCode=(String) json.get("queryUserCodeJson");
											
						
					}

					Message msg = Message.obtain();
					msg.what = 1;
					msg.obj = reCode;
					System.out.println("来自服务器的reCode:" + reCode);
					handler.sendMessage(msg);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}
}
