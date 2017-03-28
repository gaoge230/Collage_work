/*
 * �ͻ����������ͨѶ���߳�
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
	//�߳�
	public void run()
	{
		while(true)
		{
			//��ͣ�ض�ȡ�ӷ������̷�������Ϣ
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				System.out.println("�ӷ�������������Ϣ"+m.getSender()+"��"+m.getGetter()+"���ݣ�"
						+m.getCon());
				
				//�Ѵӷ����������Ϣ��ʾ������ʾ���������
				Qqchat qqchat=ManangeQqchat.getQqchat(m.getGetter()+" "+m.getSender());
				//��ʾ
				qqchat.showMessage(m);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
}
