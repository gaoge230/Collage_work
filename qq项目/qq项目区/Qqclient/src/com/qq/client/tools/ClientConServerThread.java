/*
 * 客户端与服务器通讯的线程
 */
package com.qq.client.tools;
import java.io.*;
import java.net.*;
import com.qq.client.view.Qqchat;
import com.qq.common.*;
public class ClientConServerThread extends Thread{

	private Socket s;
	public ClientConServerThread(Socket s)
	{
		this.s=s;
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	//线程
	public void run()
	{
		while(true)
		{
			//不停地读取从服务器短发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				System.out.println("从服务器发来的消息"+m.getSender()+"给"+m.getGetter()+"内容："
						+m.getCon());
				
				//把从服务器获得消息显示到该显示的聊天界面
				Qqchat qqchat=ManangeQqchat.getQqchat(m.getGetter()+" "+m.getSender());
				//显示
				qqchat.showMessage(m);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
