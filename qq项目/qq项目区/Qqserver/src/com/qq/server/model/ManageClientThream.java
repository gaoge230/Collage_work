package com.qq.server.model;
import java.util.*;
public class ManageClientThream {
	//���͹����̱߳��
	public static HashMap hm=new HashMap<String,SerConClientThread>();
	//��hm�����һ���ͻ���ͨѶ�߳�
	public static void addClientThread (String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
		
	}
	//����id�õ��߳�
	public static SerConClientThread getClientThread(String uid)
	{
		return(SerConClientThread)hm.get(uid);
	}
}
