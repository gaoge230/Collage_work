/*
 * 客户端连接服务器后台
 */
package com.qq.client.model;
import java.net.*;
import java.io.*;
import java.util.*;
import com.qq.common.*;
import com.qq.client.tools.*;

public class qqclientconserver {
	
	public  Socket s;
	/*public qqclientconserver()
	{
		try {
    		Socket s=new Socket("192.168.5.109",9999);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
		
		
	}*/
	//发送第一次请求  object 是uesr对象
	public boolean sendloginInfoToServer (Object o)
	{   boolean b=false;
		try {
    		s=new Socket("127.1.1.0",9999);
    		//发送
    		ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
    		oos.writeObject(o);
    		//返回转为message 然后判断
    		ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
    		Message ms=(Message)ois.readObject();
    		//验证用户登陆
    		if(ms.getMesType().equals("1"))
    		{
    			//创建一个该qq号与服务器保持通讯连接的线程
    			ClientConServerThread ccst=new ClientConServerThread(s);
    			//启动线程
    			ccst.start();
    			ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
    			b=true;
    			
    		}
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
		return b;
	}
	
	public void SendInfoToServer(Object o)
	{
		/*try {
    		Socket s=new Socket("192.168.5.109",9999);
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}*/
	}

}
