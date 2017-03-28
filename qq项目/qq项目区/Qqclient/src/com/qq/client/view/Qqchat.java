/*
 * �����������
 * ��Ϊ�ͻ���Ҫ���ڶ�ȡ״̬��������ǰ�������һ���߳�
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
	 
	   JTextArea jta;//�ı���
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
		jb=new JButton("����");
		//����jb
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		//������ͼ��
		this.setIconImage(new ImageIcon("image/bt.gif").getImage());
		this.setTitle(ownerid+" �����ں�"+friend+"����");
		this.setSize(500, 500);
		this.setVisible(true);
		
		
	}
	//��ʾ��Ϣ
	
	public void showMessage(Message m)
	{
		String info=m.getSender()+" ��  "+m.getGetter()+" ˵�� "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����û��������
		if(e.getSource()==jb)
		{
			String onfo=ownerid+" ��  "+friendid+" ˵��"+jtf.getText()+"\r\n";
			this.jta.append(onfo);
			Message m=new Message();
			//��ʼ��m ��ֵ
			m.setSender(ownerid);
			m.setGetter(friendid);
			m.setCon(jtf.getText());
			m.setSendtime(new java.util.Date().toString());
			//���͸�������
			try {
	    		ObjectOutputStream oos=new ObjectOutputStream
	    				(ManageClientConServerThread.getClientConServerThread(ownerid).getS().getOutputStream());
	    		oos.writeObject(m);
	    	}catch(Exception b){
	    		//��ӡ�쳣
	    		b.printStackTrace();
	    	}finally{
	    		
	    	}
			
		}
	}
	//@Override
/*	public void run() {
		// TODO Auto-generated method stub
		
		while(true)
		{   //��ȡ(��������� �͵ȴ�)
			try {
				
				ObjectInputStream ois=new ObjectInputStream(qqclientconserver.s.getInputStream());
	    		Message m=(Message)ois.readObject();
	    		//��ʾ
	    		
	    		String info=m.getSender()+" ��  "+m.getGetter()+" ˵�� "+m.getCon()+"\r\n";
	    		this.jta.append(info);
	    		
	    	}catch(Exception b){
	    		//��ӡ�쳣
	    		b.printStackTrace();
	    	}finally{
	    		
	    	}
			
		}
	}*/
}
