package com.qq.client.model;
import com.qq.common.*;
public class Qqclientuser {

	 //��֤���û��Ƿ�Ϸ�
	public boolean checkUser(User u)
	{
		return new qqclientconserver().sendloginInfoToServer(u);
		//boolean b=false;
		//return b;
	}
}
