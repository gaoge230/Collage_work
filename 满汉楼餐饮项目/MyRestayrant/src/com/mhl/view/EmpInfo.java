/*
 * 这是一个显示人事管理的界面
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
		
		//北部
		jp1=new JPanel();   //或者jp1=new JPanel(new FlowLayout(FlowLayout.CENTER)); 
		jp1_jl1=new JLabel("请输入姓名（员工号或职位）：");
		jp1_jl1.setFont(MyTools.f2);
		jtf=new JTextField(20);
		jp1_jb1=new JButton("刷新");
		//注册监听
		jp1_jb1.addActionListener(this);
		jp1_jb1.setFont(MyTools.f2);
		jp1.add(jp1_jl1);
		jp1.add(jtf);
		jp1.add(jp1_jb1);
		
		//中部
		
		//String paras[]={"1"};
		String select="1";
		em=new EmpModel();
		//em.query("select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where 1=?",paras);
		em.query(select);
		jt=new JTable(em);
		jsp=new JScrollPane(jt);
		jp5=new JPanel(new BorderLayout());
		jp5.add(jsp);
		
		
		//南部
		jp4=new JPanel(new BorderLayout());
		//处理jp2
		jp2=new JPanel();
		jp2_jl1=new JLabel("  共有**条记录。");
		jp2_jl1.setFont(MyTools.f2);
		jp2.add(jp2_jl1);
		//处理jp3
		jp3=new JPanel();
		jp3_jb1=new JButton("详细信息");
		jp3_jb1.setFont(MyTools.f2);
		jp3_jb2=new JButton("添加");
		jp3_jb2.setFont(MyTools.f2);
		jp3_jb3=new JButton("修改");
		jp3_jb3.setFont(MyTools.f2);
		jp3_jb4=new JButton("删除");
		//监听
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
			//点击了删除
			System.out.println("删除");
			//得到点击的行的第一列
			int row=this.jt.getSelectedRow();
			
			String id=(String)em.getValueAt(row, 0);
			System.out.println("用户编号："+id);

			if(em.deletEmp(id)){
				JOptionPane.showMessageDialog(null, "恭喜删除成功！");
				
			}else{
				JOptionPane.showMessageDialog(null, "删除失败！！！");
				return;
			}
			//刷新jtable
			em=new EmpModel();
			String select="1";
			//em.query("select cleId,cleName,cleSex,cleZw,cleSex from clerkInfo where 1=?",paras);
			em.query(select);
			jt.setModel(em);
		}else if(e.getSource()==jp1_jb1){
			System.out.println("查询");
			//获得输入框中的值
			String select=jtf.getText().toString().trim();
			em=new EmpModel();
			em.query(select);
			jt.setModel(em);
		}
	}
	
}
