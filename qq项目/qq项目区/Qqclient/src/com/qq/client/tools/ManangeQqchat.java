/*
 * �����û����������
 */
package com.qq.client.tools;
import java.util.*;
import com.qq.client.view.*;
public class ManangeQqchat {
	
	private static HashMap hm=new HashMap<String,Qqchat>();
	
    //����
	public static void addQqchat(String LoginAnFriendId,Qqchat qqchat)
	{
		hm.put(LoginAnFriendId, qqchat);
	}
	//ȡ��
    public static Qqchat getQqchat(String LoginAnFriendId)
    {
    	return(Qqchat)hm.get(LoginAnFriendId);
    }
}
