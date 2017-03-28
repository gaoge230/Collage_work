package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.BookMangement.Model.*;

public class SelBookPanel extends JPanel implements ActionListener{

	//�������
	JPanel jp1,jp1_0,jp1_1,jp1_2,jp1_3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1;
	JTable jt;
	JScrollPane jsp;
	//bookģ��
	BookModel bModel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelBookPanel();
	}
	public SelBookPanel(){
		
		jp1=new JPanel(new GridLayout(2,2));
		
		jp1_1=new JPanel();
		jp1_2=new JPanel();
		jp1_3=new JPanel();
		jl1=new JLabel("������");
		jl1.setFont(new Font("����", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		
		jl2=new JLabel("���ߣ�");
		jl2.setFont(new Font("����", Font.PLAIN, 16));
		jtf2=new JTextField(20);
		
		jp1_1.add(jl1);
		jp1_1.add(jtf1);
		jp1_2.add(jl2);
		jp1_2.add(jtf2);
		
		jb1=new JButton("��ѯ");
		jb1.setFont(new Font("����", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jp1_3.add(jb1);
		jp1_0=new JPanel();
		jp1.add(jp1_1);
		jp1.add(jp1_0);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		//�в�
		bModel=new BookModel();
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		
		this.setLayout(new BorderLayout());
		this.add(jp1,"North");
		this.add(jsp);
		//this.add(jb1,"South");
		this.setLocation(200, 50);
		this.setSize(1000, 600);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1){
			System.out.println("��ѯ�鼮��");
			//����ı����е�����
			if(jtf1.getText().trim().equals("")&&jtf2.getText().equals("")){
				System.out.println("��");
				JOptionPane.showMessageDialog(this, "������Ϊ��");
				return;
			}
			String paras[]={"%"+jtf1.getText().trim()+"%","%"+jtf2.getText()+"%"};
			for (String string : paras) {
				System.out.println(string);
			}
			System.out.println("������");
			bModel=new BookModel();
			bModel.query(paras);
			//����table
			jt.setModel(bModel);
			
			
			
		}
		
	}

}
