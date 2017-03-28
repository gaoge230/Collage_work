/*
 * 这是qq服务器，监听，等待某个qq客户端，来连接，
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
    		System.out.println("服务器在9999端监听。");
    		//z在9999号端口监听
    		ServerSocket ss=new ServerSocket(9999);
    		//阻塞，等待连接
    		while(true)//线程，保持始终监听 而不是监听一次
    		{
    			Socket s=ss.accept();
    		
    		
    		    //接受客户端发来的信息
    		    ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
    		    User u=(User)ois.readObject();
    		    System.out.println("服务器收到的用户名id："+u.getUserId()+"  密码："+u.getPasswd());
    		    Message m=new Message();
    		    ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
    		    if(u.getPasswd().equals("666666"))
    		    {
    			    //返回一个成功登陆的信息
    			    m.setMesType("1");
    			    oos.writeObject(m);
    			    //单开一个线程，让服务器和客户端保持通讯
    			    SerConClientThread scct=new SerConClientThread(s);
    			    //加入到hashmap中
    			    ManageClientThream.addClientThread(u.getUserId(), scct);
    			    //启动与该客户端通讯的线程
    			    scct.start();
    			
    		    }else{
    			    m.setMesType("2");
    			    oos.writeObject(m);
    			    //关闭连接
    			    s.close();
    		    }
    		   
    		    
    		
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		
    	}
    	
    }
	
}
