/*
 * ����qq���������������ȴ�ĳ��qq�ͻ��ˣ������ӣ�
 */
package com.qq.server.model;
import java.net.*;
import java.io.*;
import java.util.*;
import com.qq.common.Message;
import com.qq.common.User;
public class Myqqserver {
    public Myqqserver()
    {   
    	
    	try {
    		System.out.println("��������9999�˼�����");
    		//z��9999�Ŷ˿ڼ���
    		ServerSocket ss=new ServerSocket(9999);
    		//�������ȴ�����
    		while(true)//�̣߳�����ʼ�ռ��� �����Ǽ���һ��
    		{
    			Socket s=ss.accept();
    		
    		
    		    //���ܿͻ��˷�������Ϣ
    		    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
    		    User u=(User)ois.readObject();
    		    System.out.println("�������յ����û���id��"+u.getUserId()+"  ���룺"+u.getPasswd());
    		    Message m=new Message();
    		    ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
    		    if(u.getPasswd().equals("666666"))
    		    {
    			    //����һ���ɹ���½����Ϣ
    			    m.setMesType("1");
    			    oos.writeObject(m);
    			    //����һ���̣߳��÷������Ϳͻ��˱���ͨѶ
    			    SerConClientThread scct=new SerConClientThread(s);
    			    //���뵽hashmap��
    			    ManageClientThream.addClientThread(u.getUserId(), scct);
    			    //������ÿͻ���ͨѶ���߳�
    			    scct.start();
    			
    		    }else{
    			    m.setMesType("2");
    			    oos.writeObject(m);
    			    //�ر�����
    			    s.close();
    		    }
    		   
    		    
    		
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
    	
    }
	
}
