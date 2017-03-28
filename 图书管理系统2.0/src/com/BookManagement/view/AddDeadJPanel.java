package com.BookManagement.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.BookMangement.Model.BorrowModel;
import com.BookMangement.Model.UserModel;

public class AddDeadJPanel extends JPanel implements ActionListener{

	JTable jt;
	JScrollPane jsp;
	BorrowModel borrowModel;
	JPanel jp1,jp2,jp3;
	String id;
	JLabel jl1;
	JButton jb1;
	public AddDeadJPanel(String id) {
		this.id=id;
		jl1=new JLabel("δ�������б����£�");
		jl1.setFont(new Font("����", Font.PLAIN, 30));
		jl1.setAlignmentX(LEFT_ALIGNMENT);
		jp2=new JPanel();
		jp2.add(jl1);
		
		
		//�м�
		borrowModel=new BorrowModel();
		String paras[]={id};
		borrowModel.query(paras);
		jt=new JTable(borrowModel);
		jsp=new JScrollPane(jt);
		
		//����
		jp3=new JPanel();
		jb1=new JButton("ѡ������");
		jb1.setFont(new Font("����", Font.PLAIN, 16));
		jb1.setAlignmentY(HEIGHT);
		jb1.addActionListener(this);
		jp3.add(jb1);
		
		this.setLayout(new BorderLayout());
		this.add(jp2,"North");
		this.add(jsp);
		this.add(jp3,"East");
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1){
			//�ж��Ƿ����ڹ�
			if(Integer.parseInt((String)borrowModel.getValueAt(jt.getSelectedRow(), 5))>30){
				JOptionPane.showMessageDialog(new JFrame(), "���ã����Ѿ����ڵ���������ˣ��������ڣ�����");
				return;
			}
			
			//�ж��Ƿ���\
			UserModel uModel=new UserModel();
			int day=uModel.DeadTime((String)borrowModel.getValueAt(jt.getSelectedRow(), 4), 30);
			if(day>0){
				JOptionPane.showMessageDialog(new JFrame(), "���ã����Ѿ����ڣ��������ڣ�����");
				return;
			}
			String paras[]={id,(String)borrowModel.getValueAt(jt.getSelectedRow(), 1)};
			if(borrowModel.addDead(paras)){
				JOptionPane.showMessageDialog(new JFrame(), "���ã����ڳɹ�������");
				borrowModel=new BorrowModel();
				String p[]={id};
				borrowModel.query(p);
				jt.setModel(borrowModel);
			}
			
			
			
			
		}
	}

}
