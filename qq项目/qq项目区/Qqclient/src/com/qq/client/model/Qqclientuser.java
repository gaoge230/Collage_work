package com.qq.client.model;
import com.qq.common.*;
public class Qqclientuser {

	 //验证该用户是否合法
	public boolean checkUser(User u)
	{
		return new qqclientconserver().sendloginInfoToServer(u);
		//boolean b=false;
		//return b;
	}
}
