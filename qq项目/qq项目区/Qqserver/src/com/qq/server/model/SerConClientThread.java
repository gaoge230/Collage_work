/*
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨѶ�߳�
 */

package com.qq.server.model;
import java.net.*;
import java.io.*;
import com.qq.common.*;
public class SerConClientThread extends Thread{

	Socket s;
	//���캯��
	public SerConClientThread(Socket s)
	{
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s=s;
	}
	
	public void run()
	{
		while(true)
		{
			//������߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			try{
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//��ȡm�е���Ϣ
				System.out.println(m.getSender()+" ��  "+m.getGetter()+" ˵ "+m.getCon());
				
				//ת����ͨѶ�û���hashmap�б���ͨѶ�̲߳���id��ʾ
				//ȡ��ͨ���˵�ͨ���߳�
				SerConClientThread sc=ManageClientThream.getClientThread(m.getGetter());
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				
				 
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			
		}
		
	}
}
