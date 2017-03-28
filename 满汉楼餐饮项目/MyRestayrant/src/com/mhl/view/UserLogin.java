package com.mhl.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.mhl.tool.*;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.*;

import java.io.*;

import com.mhl.model.*;

public class UserLogin extends JDialog implements ActionListener{

	JLabel jl1,jl2,jl3;
	JTextField jName;
	JButton jCon,jCancel;
	JPasswordField jPasswd;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserLogin ul=new UserLogin();
		
	}
	
	public UserLogin(){
		//把一个组件放入Jframe中，或者jdialog中直接加入
		//也可以这样做 创建一个容器对象Container
		Container ct=this.getContentPane();
		
		//创建各个组件
		jl1=new JLabel("请输入用户名：");
		jl1.setFont(MyTools.f1);//字体
		//位置
		jl1.setBounds(60,190,150,30);
		ct.add(jl1);
		
		jName=new JTextField();
		jName.setBounds(180, 190, 120, 30);
		//设置下陷的
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		
		jl2=new JLabel("或员工号");
		jl2.setFont(MyTools.f2);
		//设置前景色
		jl2.setForeground(Color.red);
		jl2.setBounds(110,210,150,30);
		ct.add(jl2);
		
		jl3=new JLabel("请输入密码：");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(60,230,150,30);
		ct.add(jl3);
		
		jPasswd=new JPasswordField();
		jPasswd.setBounds(180, 230, 120, 30);
		//设置下陷
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		
		jCon=new JButton("确定");
		jCon.setFont(MyTools.f1);
		jCon.setBounds(110, 280, 70, 30);
		ct.add(jCon);
		jCon.addActionListener(this);
		
		jCancel=new JButton("取消");
		jCancel.setFont(MyTools.f1);
		jCancel.setBounds(200, 280, 70, 30);
		ct.add(jCancel);
		jCancel.addActionListener(this);
		
		
		
		this.setLayout(null);
		//创建一个BackImage对象
		BackImage bi=new BackImage();
		//把位置确定
		bi.setBounds(0, 0, 360, 360);
		
		//this.add(bi);
		ct.add(bi);
		
		//不用上下框
		this.setUndecorated(true);
		
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, hight/2-150);
		this.setSize(360,360);
		this.setVisible(true);
		
	}
	
	//定义一个内部类
	class BackImage extends JPanel{
		
		Image im;
		//构造函数
		public BackImage(){
			try {
				im=ImageIO.read(new File("image//login.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		public void paintComponent(Graphics g){
			g.drawImage(im, 0, 0, 360, 360, this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//判断
		if(arg0.getSource().equals(jCon)){
			//点击了确定按钮
			System.out.println("确定");
			String uId=jName.getText().toString().trim();
			String p=new String(jPasswd.getPassword());
			System.out.print(uId+"  "+p);
			//创建模型
			UserModel um=new UserModel();
			String zhiwei=um.checkUser(uId, p);
			if(zhiwei.equals("主管")||zhiwei.equals("管理员")||zhiwei.equals("经理")){
				//进入管理界面
				this.dispose();
				//提示框
				JOptionPane.showMessageDialog(this, "欢迎"+zhiwei+"进入管理系统。");
				new Windows1();
				
			}else if(zhiwei==""){
				JOptionPane.showMessageDialog(this, "您的输入有误！！！");
				//清空jname，jpasswd中的内容
				this.jName.setText("");
				this.jPasswd.setText("");
				return;
				
			}else{
				//转到收款界面
			}
			
		}else if(arg0.getSource().equals(jCancel)){
			System.out.println("取消");
			this.dispose();
		}
	}

}
