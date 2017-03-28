/**
 * 功能：qq登录界面。
 */
package com.qq.client.view;
import javax.swing.*;

import com.qq.client.model.Qqclientuser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;

public class Qqclientlogin extends JFrame implements ActionListener{
	//定义北部组件
	JLabel jbl1;
	
	//定义中部组件
	JPanel jp2;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3;
	JButton jp2_jb1,jp2_jb2;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	
	//定义南部组件
	JPanel jp1;
	JButton jp1_jb1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Qqclientlogin qqclientlogin=new Qqclientlogin();
	}
	//构造函数
	  public Qqclientlogin()
	 {
		//处理北部
		  jbl1=new JLabel(new ImageIcon("image/qtt.gif"));
		//处理中部（网格布局）
		  jp2=new JPanel(new GridLayout(3,3));
		  jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		  jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		  jp2_jbl3=new JLabel("申请密码保护",JLabel.CENTER);
		  jp2_jb1=new JButton(new ImageIcon("image/zc.gif"));
		  jp2_jb2=new JButton(new ImageIcon("image/zh.gif"));
		  jp2_jtf=new JTextField();
		  jp2_jpf=new JPasswordField();
		  jp2_jcb1=new JCheckBox("记住密码");
		  jp2_jcb2=new JCheckBox("自动登陆");
		  
		  //加入
		  jp2.add(jp2_jbl1);
		  jp2.add(jp2_jtf);
		  jp2.add(jp2_jb1);
		  jp2.add(jp2_jbl2);
		  jp2.add(jp2_jpf);
		  jp2.add(jp2_jb2);
		  jp2.add(jp2_jcb1);
		  jp2.add(jp2_jcb2);
		  jp2.add(jp2_jbl3);
		  
		  
		  
		  
		//处理南部
		  jp1=new JPanel();
		  jp1_jb1=new JButton(new ImageIcon("image/qdl.gif"));
		  //响应用户点击登录
		  jp1_jb1.addActionListener(this);
		  jp1.add(jp1_jb1);
		  
		//改变标题栏图标
		 // Toolkit tk=Toolkit.getDefaultToolkit();
			//	  Image image=tk.createImage("bt.gif"); //或者 tk.getImage("image.gif");/*image.gif是你的图标*/ 
				//  this.setIconImage(image);
		  
		 this.setTitle("QQ");
		 this.add(jbl1,"North");
		 this.add(jp2,"Center");
		 this.add(jp1,"South");
		 this.setSize(429,350);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setVisible(true);//可见
		
		  
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//如果用户点击登录
		if(e.getSource()==jp1_jb1)
		{
			Qqclientuser qqclientuser=new Qqclientuser();
			User u=new User();
			//得到用户名和密码
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqclientuser.checkUser(u))
			{
				new QqFriendlist(u.getUserId());
				//关闭登陆界面
				this.dispose();
			}else
			{
				JOptionPane.showMessageDialog(this, "用户名或密码错误");
			}
			
		}
	}

}
