/**
 * ���ܣ�qq��¼���档
 */
package com.qq.client.view;
import javax.swing.*;

import com.qq.client.model.Qqclientuser;
import com.qq.common.User;

import java.awt.*;
import java.awt.event.*;

public class Qqclientlogin extends JFrame implements ActionListener{
	//���山�����
	JLabel jbl1;
	
	//�����в����
	JPanel jp2;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3;
	JButton jp2_jb1,jp2_jb2;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	
	//�����ϲ����
	JPanel jp1;
	JButton jp1_jb1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Qqclientlogin qqclientlogin=new Qqclientlogin();
	}
	//���캯��
	  public Qqclientlogin()
	 {
		//������
		  jbl1=new JLabel(new ImageIcon("image/qtt.gif"));
		//�����в������񲼾֣�
		  jp2=new JPanel(new GridLayout(3,3));
		  jp2_jbl1=new JLabel("QQ����",JLabel.CENTER);
		  jp2_jbl2=new JLabel("QQ����",JLabel.CENTER);
		  jp2_jbl3=new JLabel("�������뱣��",JLabel.CENTER);
		  jp2_jb1=new JButton(new ImageIcon("image/zc.gif"));
		  jp2_jb2=new JButton(new ImageIcon("image/zh.gif"));
		  jp2_jtf=new JTextField();
		  jp2_jpf=new JPasswordField();
		  jp2_jcb1=new JCheckBox("��ס����");
		  jp2_jcb2=new JCheckBox("�Զ���½");
		  
		  //����
		  jp2.add(jp2_jbl1);
		  jp2.add(jp2_jtf);
		  jp2.add(jp2_jb1);
		  jp2.add(jp2_jbl2);
		  jp2.add(jp2_jpf);
		  jp2.add(jp2_jb2);
		  jp2.add(jp2_jcb1);
		  jp2.add(jp2_jcb2);
		  jp2.add(jp2_jbl3);
		  
		  
		  
		  
		//�����ϲ�
		  jp1=new JPanel();
		  jp1_jb1=new JButton(new ImageIcon("image/qdl.gif"));
		  //��Ӧ�û������¼
		  jp1_jb1.addActionListener(this);
		  jp1.add(jp1_jb1);
		  
		//�ı������ͼ��
		 // Toolkit tk=Toolkit.getDefaultToolkit();
			//	  Image image=tk.createImage("bt.gif"); //���� tk.getImage("image.gif");/*image.gif�����ͼ��*/ 
				//  this.setIconImage(image);
		  
		 this.setTitle("QQ");
		 this.add(jbl1,"North");
		 this.add(jp2,"Center");
		 this.add(jp1,"South");
		 this.setSize(429,350);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setVisible(true);//�ɼ�
		
		  
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����û������¼
		if(e.getSource()==jp1_jb1)
		{
			Qqclientuser qqclientuser=new Qqclientuser();
			User u=new User();
			//�õ��û���������
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqclientuser.checkUser(u))
			{
				new QqFriendlist(u.getUserId());
				//�رյ�½����
				this.dispose();
			}else
			{
				JOptionPane.showMessageDialog(this, "�û������������");
			}
			
		}
	}

}
