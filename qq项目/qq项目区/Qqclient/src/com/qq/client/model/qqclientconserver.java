/*
 * �ͻ������ӷ�������̨
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
	//���͵�һ������  object ��uesr����
	public boolean sendloginInfoToServer (Object o)
	{   boolean b=false;
		try {
    		s=new Socket("127.1.1.0",9999);
    		//����
    		ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
    		oos.writeObject(o);
    		//����תΪmessage Ȼ���ж�
    		ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
    		Message ms=(Message)ois.readObject();
    		//��֤�û���½
    		if(ms.getMesType().equals("1"))
    		{
    			//����һ����qq�������������ͨѶ���ӵ��߳�
    			ClientConServerThread ccst=new ClientConServerThread(s);
    			//�����߳�
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
