package com.qq.server.model;
import java.util.*;
public class ManageClientThream {
	//泛型管理线程标记
	public static HashMap hm=new HashMap<String,SerConClientThread>();
	//向hm中添加一个客户端通讯线程
	public static void addClientThread (String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
		
	}
	//根据id得到线程
	public static SerConClientThread getClientThread(String uid)
	{
		return(SerConClientThread)hm.get(uid);
	}
}
