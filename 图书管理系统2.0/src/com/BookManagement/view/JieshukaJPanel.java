package com.BookManagement.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.BookMangement.Model.ReadModel;


public class JieshukaJPanel extends JPanel implements ActionListener{

	/**
	 * Create the panel.
	 */
	JPasswordField jPasswordField;
	JPanel jp1_3_0,jp1_3_1,jp1_3_2,jp1_3_3,jp1_3_4,jp1_3_5,jp1_3_6,jp1_3_7,jp1_3_8;
	JLabel jl1,jl2,jl3,jl3_1,jl3_2,jl3_3,jl3_4,jl3_5,jl3_6,jl3_7,jl3_8;
	JTextField jtf3_1,jtf3_2,jtf3_3,jtf3_4,jtf3_5,jtf3_6,jtf3_7,jtf3_8;
	JButton jp1_3_jb1,jp1_3_jb2;
	JPanel jp3_1;
	
	Image im;

	public void paintComponent(Graphics g){
		g.drawImage(im, 0, 0, 695, 541, this);
	}
	public JieshukaJPanel(){
	try {
		im=ImageIO.read(new File("image//����2.jpg"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		//��Ƭ��----//���鿨ģ��jp1_3
		System.out.println("1111");
				jp3_1=new JPanel(new GridLayout(8, 1));
				//����
				jp1_3_1=new JPanel();
				jp1_3_2=new JPanel();
				jp1_3_3=new JPanel();
				jp1_3_4=new JPanel();
				jp1_3_5=new JPanel();
				jp1_3_6=new JPanel();
				jp1_3_7=new JPanel();
				jp1_3_8=new JPanel();
				
				//��Ϊ͸��
				jp3_1.setOpaque(false);
				jp1_3_1.setOpaque(false);
				jp1_3_2.setOpaque(false);
				jp1_3_3.setOpaque(false);
				jp1_3_4.setOpaque(false);
				jp1_3_5.setOpaque(false);
				jp1_3_6.setOpaque(false);
				jp1_3_7.setOpaque(false);
				jp1_3_8.setOpaque(false);
			
			
				jl3_1=new JLabel("ѧ�ţ�");
				jl3_1.setFont(new Font("����", Font.BOLD, 16));
				jl3_2=new JLabel("������");
				jl3_2.setFont(new Font("����", Font.BOLD, 16));
				jl3_3=new JLabel("���䣺");
				jl3_3.setFont(new Font("����", Font.BOLD, 16));
				jl3_4=new JLabel("�Ա�");
				jl3_4.setFont(new Font("����", Font.BOLD, 16));
				jl3_5=new JLabel("רҵ��");
				jl3_5.setFont(new Font("����", Font.BOLD, 16));
				jl3_6=new JLabel("��ַ��");
				jl3_6.setFont(new Font("����", Font.BOLD, 16));
				jl3_7=new JLabel("��ϵ��ʽ��");
				jl3_7.setFont(new Font("����", Font.BOLD, 16));
				jl3_8=new JLabel("���룺");
				jl3_8.setFont(new Font("����", Font.BOLD, 16));
				//�����������
				jtf3_1=new JTextField(20);
				jtf3_2=new JTextField(20);
				jtf3_3=new JTextField(20);
				jtf3_4=new JTextField(20);
				jtf3_5=new JTextField(20);
				jtf3_6=new JTextField(20);
				jtf3_7=new JTextField(20);
				jPasswordField=new JPasswordField(20);
				jp1_3_1.add(jl3_1);
				jp1_3_1.add(jtf3_1);
				jp1_3_2.add(jl3_2);
				jp1_3_2.add(jtf3_2);
				jp1_3_3.add(jl3_3);
				jp1_3_3.add(jtf3_3);
				jp1_3_4.add(jl3_4);
				jp1_3_4.add(jtf3_4);
				jp1_3_5.add(jl3_5);
				jp1_3_5.add(jtf3_5);
				jp1_3_6.add(jl3_6);
				jp1_3_6.add(jtf3_6);
				jp1_3_7.add(jl3_7);
				jp1_3_7.add(jtf3_7);
				jp1_3_8.add(jl3_8);
				jp1_3_8.add(jPasswordField);
				
				jp3_1.add(jp1_3_1);
				jp3_1.add(jp1_3_2);
				jp3_1.add(jp1_3_3);
				jp3_1.add(jp1_3_4);
				jp3_1.add(jp1_3_5);
				jp3_1.add(jp1_3_6);
				jp3_1.add(jp1_3_7);
				jp3_1.add(jp1_3_8);
			
						
				//������鿨���
				//jp1_3=new mypanel();
				
				jp1_3_jb1=new JButton(new ImageIcon("image//�쿨1.png"));
				jp1_3_jb1.setFont(new Font("����", Font.BOLD, 18));
				jp1_3_jb1.addActionListener(this);
				jp1_3_jb1.setBounds(330, 394, 150, 45);
				//jp1_3_jb2=new JButton(new ImageIcon("image//������ҳ.png"));
				//jp1_3_jb2.setOpaque(false);
				//jp1_3_jb2.setBackground(Color.PINK);
				//jp1_3_jb2.addActionListener(this);
				//jp1_3_jb2.setBounds(0, 0, 100, 70);
				jp3_1.setBounds(160, 20, 400, 350);
				//this.add(jp1_3_jb2);
				
				this.setLayout(null);
				this.add(jp3_1);
				this.add(jp1_3_jb1);
				
				
				this.setVisible(true);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//����ͼ�鿨
				if(arg0.getSource()==jp1_3_jb1){
					if(jtf3_1.getText().equals("")&&jtf3_2.getText().equals("")&&jtf3_5.getText().equals("")
							&&jtf3_6.getText().equals("")&&jtf3_7.getText().equals("")
						&&String.valueOf(jPasswordField.getPassword()).equals("")){
						JOptionPane.showMessageDialog(new JFrame(), "����Ϊ�գ�");
						return;
						
					}
					
					System.out.println("����ͼ�鿨");
					
					ReadModel rModel=new ReadModel();
					//���ò���
					String paras[]={jtf3_1.getText().trim(),jtf3_2.getText().trim(),jtf3_3.getText().trim()
							,jtf3_4.getText().trim(),jtf3_5.getText().trim()
							,jtf3_6.getText().trim(),jtf3_7.getText().trim(),String.valueOf(jPasswordField.getPassword()).trim()};
					for (String string : paras) {
						System.out.println(string);
					}
					if(rModel.add(paras)){
						JOptionPane.showMessageDialog(this, "��ϲע��ɹ�!!!");
						jtf3_1.setText("");
						jtf3_2.setText("");
						jtf3_3.setText("");
						jtf3_4.setText("");
						jtf3_5.setText("");
						jtf3_6.setText("");
						jtf3_7.setText("");
						jPasswordField.setText("");
						
					}else {
						JOptionPane.showMessageDialog(this, "���������Ϣ����");
					}
					
				}
	}

}
