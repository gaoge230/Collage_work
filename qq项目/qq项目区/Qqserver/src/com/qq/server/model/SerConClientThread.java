/*
 * 功能：是服务器和某个客户端的通讯线程
 */

package com.qq.server.model;
import java.net.*;
import java.io.*;
import com.qq.common.*;
public class SerConClientThread extends Thread{

	Socket s;
	//构造函数
	public SerConClientThread(Socket s)
	{
		//把服务器和该客户端的连接赋给s
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//这里该线程就可以接受客户端的信息
			try{
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//读取m中的信息
				System.out.println(m.getSender()+" 对  "+m.getGetter()+" 说 "+m.getCon());
				
				//转发给通讯用户在hashmap中保存通讯线程并用id标示
				//取得通行人的通信线程
				SerConClientThread sc=ManageClientThream.getClientThread(m.getGetter());
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				
				 
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
	}
}
