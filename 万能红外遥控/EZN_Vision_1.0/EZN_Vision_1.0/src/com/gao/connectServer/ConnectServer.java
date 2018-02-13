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
 * @author �߸�
 * @Time 2017.2.21
 *
 */
public class ConnectServer {
	/** Called when the activity is first created. */
	// ģ�����Լ����Լ�����localhost�ˣ�������Ӧ��Ϊ
	private static String strUrl = "http://10.158.193.164:8080/EZN_Server/queryid.action";
	private String reCode = null;

	/**
	 * ���ӷ�����������÷�������������json���ݡ�
	 * 
	 * @param handler
	 * @param name
	 *            ң������name
	 */
	public void getPostServerData(final Handler handler, final String name) {

		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub //
		 * ʹ��UrlConnection������������û������뷢�͸���������֤ try {
		 * 
		 * URL url = new URL(strUrl); HttpURLConnection connection =
		 * (HttpURLConnection) url .openConnection();
		 * connection.setRequestMethod("POST");
		 * //connection.setConnectTimeout(100* 1000);// �������ӳ�ʱ // ����һЩ����ͷ��Ϣ
		 * String body = "queryname=" + name;
		 * connection.setRequestProperty("Content-Length", body.length() +
		 * "");// ��������ͷ //connection.setRequestProperty("Cathe-Control",
		 * "max-age=0"); // connection.setRequestProperty("Origin",
		 * "http://��ַ");
		 * 
		 * // ����urlConnection����д�������� connection.setDoOutput(true);
		 * connection.setDoInput(true); // ��ȡyigeOutputStream�����洫����
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
					// ��������
					HttpClient client = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(strUrl);
					// ���ò�������html���ύ
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					params.add(new BasicNameValuePair("queryNameJson", name));
					HttpResponse httpResponse = null;

					httpPost.setEntity(new UrlEncodedFormEntity(params,
							HTTP.UTF_8));
					httpResponse = client.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						System.out.println("���ӷ����������200");
						// ȡ���ַ���
						HttpEntity entity = httpResponse.getEntity();
						String entityString = EntityUtils.toString(entity,
								"utf-8");
						System.out.println("���Է�������entityString:" + entityString);
						JSONObject json = new JSONObject(entityString);
						System.out.println(json.get("queryUserCodeJson"));						
						// JSONArray jsonArray = new JSONArray(entityString);
						reCode=(String) json.get("queryUserCodeJson");
											
						
					}

					Message msg = Message.obtain();
					msg.what = 1;
					msg.obj = reCode;
					System.out.println("���Է�������reCode:" + reCode);
					handler.sendMessage(msg);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}
}
