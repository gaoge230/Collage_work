/*
 * 管理客户端和服务器通讯的线程类
 */
package com.qq.client.tools;
import java.util.*;
public class ManageClientConServerThread {

	private static HashMap hm=new HashMap<String,ClientConServerThread>();
	
	//把创建好的ClientConServerThread放入到hmzhong
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst)
	{
		hm.put(qqId, ccst);
	}
	//通过qqid取得该线程
	public static ClientConServerThread getClientConServerThread(String qqId)
	{
		return(ClientConServerThread)hm.get(qqId);
	}
	
}
