package com.BookManagement.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.remote.rmi.RMIConnectionImpl;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.BookMangement.Model.ReadModel;

public class ReadManage extends JPanel implements ActionListener{

	//定义
	JPanel jp1,jp2,jp1_1,jp1_2;
	JScrollPane jsp;
	JTable jt;
	JButton jb1,jb2,jb3;
	JLabel jl1;
	JTextField jtf1;
	ReadModel rModel;
	/**
	 * Create the panel.
	 */
	public ReadManage() {
		jp1=new JPanel();
		jp1_1=new JPanel();
		jp1_2=new JPanel();
		jl1=new JLabel("读者id：");
		jl1.setFont(new Font("宋体", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		jb1=new JButton("查询");
		jb1.setFont(new Font("宋体", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jb2=new JButton("查询所有");
		jb2.setFont(new Font("宋体", Font.PLAIN, 16));
		jb2.addActionListener(this);
		jp1_1.add(jl1);
		jp1_1.add(jtf1);
		jp1_1.add(jb1);
		
		jp1_2.add(jb2);
		
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		//中间
		jt=new JTable();
		rModel=new ReadModel();
		jt.setModel(rModel);
		jsp=new JScrollPane(jt);
		//dong部
		jp2=new JPanel();
		BoxLayout bl=new BoxLayout(jp2, BoxLayout.Y_AXIS);
		jp2.setLayout(bl);
		jb3=new JButton("选中删除");
		jb3.setFont(new Font("宋体", Font.PLAIN, 16));
		jb3.addActionListener(this);
		jp2.add(jb3);
		
		this.setLayout(new BorderLayout());
		this.add(jp1,"North");
		this.add(jsp);
		this.add(jp2,"East");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			rModel=new ReadModel();
			String paras[]={jtf1.getText().trim()};
			rModel.query(paras);
			jt.setModel(rModel);
			
		}
		if(e.getSource()==jb2){
			rModel=new ReadModel();
			String paras[]=null;
			rModel.query(paras);
			jt.setModel(rModel);
		}
		//删除
		if(e.getSource()==jb3){
			String paras[]={(String)rModel.getValueAt(jt.getSelectedRow(), 0)};
			
			if(rModel.delete(paras)){
				JOptionPane.showMessageDialog(new JFrame(), "删除成功！！！");
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "删除失败！！！");
			}
			rModel=new ReadModel();
			jt.setModel(rModel);
		}
	}

}
