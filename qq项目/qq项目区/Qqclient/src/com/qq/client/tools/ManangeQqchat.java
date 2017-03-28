/*
 * 管理用户聊天见面类
 */
package com.qq.client.tools;
import java.util.*;
import com.qq.client.view.*;
public class ManangeQqchat {
	
	private static HashMap hm=new HashMap<String,Qqchat>();
	
    //加入
	public static void addQqchat(String LoginAnFriendId,Qqchat qqchat)
	{
		hm.put(LoginAnFriendId, qqchat);
	}
	//取出
    public static Qqchat getQqchat(String LoginAnFriendId)
    {
    	return(Qqchat)hm.get(LoginAnFriendId);
    }
}
