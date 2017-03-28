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
		//��һ���������Jframe�У�����jdialog��ֱ�Ӽ���
		//Ҳ���������� ����һ����������Container
		Container ct=this.getContentPane();
		
		//�����������
		jl1=new JLabel("�������û�����");
		jl1.setFont(MyTools.f1);//����
		//λ��
		jl1.setBounds(60,190,150,30);
		ct.add(jl1);
		
		jName=new JTextField();
		jName.setBounds(180, 190, 120, 30);
		//�������ݵ�
		jName.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jName);
		
		jl2=new JLabel("��Ա����");
		jl2.setFont(MyTools.f2);
		//����ǰ��ɫ
		jl2.setForeground(Color.red);
		jl2.setBounds(110,210,150,30);
		ct.add(jl2);
		
		jl3=new JLabel("���������룺");
		jl3.setFont(MyTools.f1);
		jl3.setBounds(60,230,150,30);
		ct.add(jl3);
		
		jPasswd=new JPasswordField();
		jPasswd.setBounds(180, 230, 120, 30);
		//��������
		jPasswd.setBorder(BorderFactory.createLoweredBevelBorder());
		ct.add(jPasswd);
		
		jCon=new JButton("ȷ��");
		jCon.setFont(MyTools.f1);
		jCon.setBounds(110, 280, 70, 30);
		ct.add(jCon);
		jCon.addActionListener(this);
		
		jCancel=new JButton("ȡ��");
		jCancel.setFont(MyTools.f1);
		jCancel.setBounds(200, 280, 70, 30);
		ct.add(jCancel);
		jCancel.addActionListener(this);
		
		
		
		this.setLayout(null);
		//����һ��BackImage����
		BackImage bi=new BackImage();
		//��λ��ȷ��
		bi.setBounds(0, 0, 360, 360);
		
		//this.add(bi);
		ct.add(bi);
		
		//�������¿�
		this.setUndecorated(true);
		
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-200, hight/2-150);
		this.setSize(360,360);
		this.setVisible(true);
		
	}
	
	//����һ���ڲ���
	class BackImage extends JPanel{
		
		Image im;
		//���캯��
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
		//�ж�
		if(arg0.getSource().equals(jCon)){
			//�����ȷ����ť
			System.out.println("ȷ��");
			String uId=jName.getText().toString().trim();
			String p=new String(jPasswd.getPassword());
			System.out.print(uId+"  "+p);
			//����ģ��
			UserModel um=new UserModel();
			String zhiwei=um.checkUser(uId, p);
			if(zhiwei.equals("����")||zhiwei.equals("����Ա")||zhiwei.equals("����")){
				//����������
				this.dispose();
				//��ʾ��
				JOptionPane.showMessageDialog(this, "��ӭ"+zhiwei+"�������ϵͳ��");
				new Windows1();
				
			}else if(zhiwei==""){
				JOptionPane.showMessageDialog(this, "�����������󣡣���");
				//���jname��jpasswd�е�����
				this.jName.setText("");
				this.jPasswd.setText("");
				return;
				
			}else{
				//ת���տ����
			}
			
		}else if(arg0.getSource().equals(jCancel)){
			System.out.println("ȡ��");
			this.dispose();
		}
	}

}
