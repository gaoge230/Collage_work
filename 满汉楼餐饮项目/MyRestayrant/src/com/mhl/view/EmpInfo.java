/*
 * ����һ����ʾ���¹���Ľ���
 */
package com.mhl.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.mhl.tool.*;
import com.mhl.model.*;
public class EmpInfo extends JPanel implements ActionListener{

	JPanel jp1,jp2,jp3,jp4,jp5;
	JLabel jp1_jl1,jp2_jl1;
	JTextField jtf;
	JButton jp1_jb1,jp3_jb1,jp3_jb2,jp3_jb3,jp3_jb4;
	JTable jt;
	JScrollPane jsp;
	EmpModel em;
	public EmpInfo(){
		this.setLayout(new BorderLayout());
		
		//����
		jp1=new JPanel();   //����jp1=new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		jp1_jl1=new JLabel("������������Ա���Ż�ְλ����");
		jp1_jl1.setFont(MyTools.f2);
		jtf=new JTextField(20);
		jp1_jb1=new JButton("ˢ��");
		//ע�����
		jp1_jb1.addActionListener(this);
		jp1_jb1.setFont(MyTools.f2);
		jp1.add(jp1_jl1);
		jp1.add(jtf);
		jp1.add(jp1_jb1);
		
		//�в�
		
		//String paras[]={"1"};
		String select="1";
		em=new EmpModel();
		//em.query("select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where 1=?",paras);
		em.query(select);
		jt=new JTable(em);
		jsp=new JScrollPane(jt);
		jp5=new JPanel(new BorderLayout());
		jp5.add(jsp);
		
		
		//�ϲ�
		jp4=new JPanel(new BorderLayout());
		//����jp2
		jp2=new JPanel();
		jp2_jl1=new JLabel("  ����**����¼��");
		jp2_jl1.setFont(MyTools.f2);
		jp2.add(jp2_jl1);
		//����jp3
		jp3=new JPanel();
		jp3_jb1=new JButton("��ϸ��Ϣ");
		jp3_jb1.setFont(MyTools.f2);
		jp3_jb2=new JButton("���");
		jp3_jb2.setFont(MyTools.f2);
		jp3_jb3=new JButton("�޸�");
		jp3_jb3.setFont(MyTools.f2);
		jp3_jb4=new JButton("ɾ��");
		//����
		jp3_jb4.addActionListener(this);
		jp3_jb4.setFont(MyTools.f2);
		jp3.add(jp3_jb1);
		jp3.add(jp3_jb2);
		jp3.add(jp3_jb3);
		jp3.add(jp3_jb4);
		
		jp4.add(jp2,"West");
		jp4.add(jp3,"East");
		
		
		
		this.add(jp1,"North");
		this.add(jp5,"Center");
		this.add(jp4,"South");
		this.setVisible(true);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jp3_jb4){
			//�����ɾ��
			System.out.println("ɾ��");
			//�õ�������еĵ�һ��
			int row=this.jt.getSelectedRow();
			
			String id=(String)em.getValueAt(row, 0);
			System.out.println("�û���ţ�"+id);

			if(em.deletEmp(id)){
				JOptionPane.showMessageDialog(null, "��ϲɾ���ɹ���");
				
			}else{
				JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�����");
				return;
			}
			//ˢ��jtable
			em=new EmpModel();
			String select="1";
			//em.query("select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where 1=?",paras);
			em.query(select);
			jt.setModel(em);
		}else if(e.getSource()==jp1_jb1){
			System.out.println("��ѯ");
			//���������е�ֵ
			String select=jtf.getText().toString().trim();
			em=new EmpModel();
			em.query(select);
			jt.setModel(em);
		}
	}
	
}
