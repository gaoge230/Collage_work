/*
 * 好友聊天界面
 * 因为客户端要处于读取状态，因此我们把它做成一个线程
 */
package com.qq.client.view;
import javax.swing.*;
import com.qq.common.*;
import java.awt.*;
import java.awt.event.*;
import com.qq.client.model.*;
import java.io.*;
import com.qq.client.tools.*;
public class Qqchat extends JFrame implements ActionListener{
	 
	   JTextArea jta;//文本域
	   JTextField jtf;
	   JButton jb;
	   JPanel jp;
	   
	   String ownerid;
	   String friendid;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Qqchat qqchat=new Qqchat("1");
	}
	public Qqchat(String ownerid,String friend)
	{
		this.ownerid=ownerid;
		this.friendid=friend;
		jta=new JTextArea();
		jtf=new JTextField(30);
		jb=new JButton("发送");
		//监听jb
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		//标题栏图标
		this.setIconImage(new ImageIcon("image/bt.gif").getImage());
		this.setTitle(ownerid+" 你正在和"+friend+"聊天");
		this.setSize(500, 500);
		this.setVisible(true);
		
		
	}
	//显示消息
	
	public void showMessage(Message m)
	{
		String info=m.getSender()+" 对  "+m.getGetter()+" 说： "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击发送
		if(e.getSource()==jb)
		{
			String onfo=ownerid+" 对  "+friendid+" 说："+jtf.getText()+"\r\n";
			this.jta.append(onfo);
			Message m=new Message();
			//初始化m 赋值
			m.setSender(ownerid);
			m.setGetter(friendid);
			m.setCon(jtf.getText());
			m.setSendtime(new java.util.Date().toString());
			//发送给服务器
			try {
	    		ObjectOutputStream oos=new ObjectOutputStream
	    				(ManageClientConServerThread.getClientConServerThread(ownerid).getS().getOutputStream());
	    		oos.writeObject(m);
	    	}catch(Exception b){
	    		//打印异常
	    		b.printStackTrace();
	    	}finally{
	    		
	    	}
			
		}
	}
	//@Override
/*	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{   //读取(如果读不到 就等待)
			try {
				
				ObjectInputStream ois=new ObjectInputStream(qqclientconserver.s.getInputStream());
	    		Message m=(Message)ois.readObject();
	    		//显示
	    		
	    		String info=m.getSender()+" 对  "+m.getGetter()+" 说： "+m.getCon()+"\r\n";
	    		this.jta.append(info);
	    		
	    	}catch(Exception b){
	    		//打印异常
	    		b.printStackTrace();
	    	}finally{
	    		
	    	}
			
		}
	}*/
}
