package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.BookMangement.Model.*;

public class SelBookManagePanel extends JPanel implements ActionListener{

	//定义组件
	JPanel jp,jp0;//卡片一，卡片二
	CardLayout cl;
	JPanel jp1,jp2,jp1_1,jp1_2,jp1_3,jp1_4,jp1_2_1;
	JLabel jl1,jl2,jl3;
	JTextField jtf1,jtf2;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	//卡片二 的组件
	JPanel jp0_1,jp0_1_1,jp0_1_2,jp0_1_3,jp0_1_4,jp0_1_5,jp0_1_6,jp0_1_7,jp0_1_8;
	JLabel jl0_1,jl0_2,jl0_3,jl0_4,jl0_5,jl0_6,jl0_7,jl0_8;
	JTextField jtf0_1,jtf0_2,jtf0_3,jtf0_4,jtf0_5,jtf0_6,jtf0_7,jtf0_8;
	JButton jb0_1;
	//book模型
	BookModel bModel;
	JComboBox jcb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SelBookPanel();
	}
	public SelBookManagePanel(){
		
		//北部
		jp1=new JPanel(new GridLayout(2,2));
		String idem[]={"书名","作者","编号"};
		jcb=new JComboBox(idem);
		
		jp1_1=new JPanel();
		jp1_2=new JPanel(new BorderLayout());
		jp1_3=new JPanel();
		jp1_4=new JPanel();
		jl1=new JLabel("查询方式为：");
		jl1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp1_1.add(jl1);
		
		jp1_2_1=new JPanel();
		
		jl2=new JLabel("①：");
		jl2.setFont(new Font("宋体", Font.PLAIN, 16));
		jb1=new JButton("查询");
		jb1.setFont(new Font("宋体", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jtf1=new JTextField(10);
		
		jp1_2_1.add(jl2);
		jp1_2_1.add(jcb);
		jp1_2.add(jp1_2_1,"West");
		jp1_2.add(jtf1);
		jp1_2.add(jb1,"East");
		
		jtf2=new JTextField(20);
		jl3=new JLabel("②：通过右侧按钮：    ");
		jl3.setFont(new Font("宋体", Font.PLAIN, 16));
		jb2=new JButton("查看所有");
		jb2.setFont(new Font("宋体", Font.PLAIN, 16));
		jb2.addActionListener(this);
		
		jp1_4.add(jl3);
		jp1_4.add(jb2);

	
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		jp1.add(jp1_4);
		//中部
		bModel=new BookModel();
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		
		//西部是一个box布局
		jp2=new JPanel();
		BoxLayout boxLayout=new BoxLayout(jp2,BoxLayout.Y_AXIS);
		jp2.setLayout(boxLayout);
		jb3=new JButton("选中修改");
		jb3.setFont(new Font("宋体", Font.PLAIN, 16));
		jb3.addActionListener(this);
		jb4=new JButton("选中删除");
		jb4.setFont(new Font("宋体", Font.PLAIN, 16));
		jb4.addActionListener(this);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//卡片一
		jp=new JPanel(new BorderLayout());
		jp.add(jp2,"East");
		jp.add(jp1,"North");
		jp.add(jsp);
		
		//卡片二
		jp0=new JPanel();
		jp0_1=new JPanel(new GridLayout(4, 2));//网格布局
		jp0_1_1=new JPanel();
		jp0_1_2=new JPanel();
		jp0_1_3=new JPanel();
		jp0_1_4=new JPanel();
		jp0_1_5=new JPanel();
		jp0_1_6=new JPanel();
		jp0_1_7=new JPanel();
		jp0_1_8=new JPanel();
		
		jl0_1=new JLabel("   编号：");
		jl0_2=new JLabel("   书名： ");
		jl0_3=new JLabel("   作者： ");
		jl0_4=new JLabel("  出版社：");
		jl0_5=new JLabel("出版时间：");
		jl0_6=new JLabel("   价格： ");
		jl0_7=new JLabel("   数量： ");
		jl0_8=new JLabel("   位置： ");
		jl0_1.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_2.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_3.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_4.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_5.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_6.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_7.setFont(new Font("宋体", Font.PLAIN, 16));
		jl0_8.setFont(new Font("宋体", Font.PLAIN, 16));
		
		jtf0_1=new JTextField(15);
		jtf0_2=new JTextField(15);
		jtf0_3=new JTextField(15);
		jtf0_4=new JTextField(15);
		jtf0_5=new JTextField(15);
		jtf0_6=new JTextField(15);
		jtf0_7=new JTextField(15);
		jtf0_8=new JTextField(15);
		jp0_1_1.add(jl0_1);
		jp0_1_1.add(jtf0_1);
		jp0_1_2.add(jl0_2);
		jp0_1_2.add(jtf0_2);
		jp0_1_3.add(jl0_3);
		jp0_1_3.add(jtf0_3);
		jp0_1_4.add(jl0_4);
		jp0_1_4.add(jtf0_4);
		jp0_1_5.add(jl0_5);
		jp0_1_5.add(jtf0_5);
		jp0_1_6.add(jl0_6);
		jp0_1_6.add(jtf0_6);
		jp0_1_7.add(jl0_7);
		jp0_1_7.add(jtf0_7);
		jp0_1_8.add(jl0_8);
		jp0_1_8.add(jtf0_8);
		jp0_1.add(jp0_1_1);
		jp0_1.add(jp0_1_2);
		jp0_1.add(jp0_1_3);
		jp0_1.add(jp0_1_4);
		jp0_1.add(jp0_1_5);
		jp0_1.add(jp0_1_6);
		jp0_1.add(jp0_1_7);
		jp0_1.add(jp0_1_8);
		
		jp0.add(jp0_1);
		jb0_1=new JButton("确认修改");
		jb0_1.addActionListener(this);
		jb0_1.setFont(new Font("黑体", Font.PLAIN, 16));
		jp0.add(jb0_1);
		
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jp,"1");
		this.add(jp0,"2");
		/*this.add(jp2,"East");
		this.add(jp1,"North");
		this.add(jsp);*/
		//this.setLayout(new BorderLayout());
		this.setLocation(200, 50);
		this.setSize(1000, 600);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//查找特定书籍（书名或编号或作者）
		if(arg0.getSource()==jb1){
			System.out.println("查询书籍！");
			//获得文本框中的数据
			if(jtf1.getText().trim().equals("")){
				System.out.println("空");
				JOptionPane.showMessageDialog(this, "不允许为空");
				return;
			}
			
			String paras[]={"%"+jtf1.getText().trim()+"%"};
			String a =jcb.getSelectedItem().toString();
			System.out.println(a+jtf1.getText().trim());
			bModel=new BookModel();
			bModel.Fuquery(a, paras);
			//更新table
			jt.setModel(bModel);
			
		}
		
		//查看所有书籍
		if(arg0.getSource()==jb2){
			System.out.println("查看所有");
			String paras[]=null;
			bModel=new BookModel();
			bModel.query(paras);
			//更新
			jt.setModel(bModel);
		}
		//修改书籍
		//String Bid=null;
		if(arg0.getSource()==jb3){
			System.out.println("选中修改！");
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "请选中一行！！");
				return;
			}
			//得到选中的行
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(), 0);
			jtf0_1.setText((String)bModel.getValueAt(jt.getSelectedRow(), 0));
			jtf0_2.setText((String)bModel.getValueAt(jt.getSelectedRow(), 1));
			jtf0_3.setText((String)bModel.getValueAt(jt.getSelectedRow(), 2));
			jtf0_4.setText((String)bModel.getValueAt(jt.getSelectedRow(), 3));
			jtf0_5.setText((String)bModel.getValueAt(jt.getSelectedRow(), 4));
			jtf0_6.setText((String)bModel.getValueAt(jt.getSelectedRow(), 5));
			jtf0_7.setText((String)bModel.getValueAt(jt.getSelectedRow(), 6));
			jtf0_8.setText((String)bModel.getValueAt(jt.getSelectedRow(), 7));
			
			cl.show(this, "2");
			
		}
		
		//确认修改jb0_1
		if(arg0.getSource()==jb0_1){
			
			String paras[]={jtf0_1.getText().trim(),jtf0_2.getText().trim(),jtf0_3.getText().trim(),jtf0_4.getText().trim(),
					jtf0_5.getText().trim(),jtf0_6.getText().trim(),jtf0_7.getText().trim(),jtf0_8.getText().trim(),
					(String)bModel.getValueAt(jt.getSelectedRow(), 0)};
			bModel=new BookModel();
			if(bModel.update(paras)){
				JOptionPane.showMessageDialog(new JFrame(), "修改成功！！");
				
				jt.setModel(bModel);
				cl.show(this, "1");
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "修改失败！！");
			}
			
			
			
		}
		//删除书籍
		if(arg0.getSource()==jb4){
			System.out.println("选中删除！");
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "请选中一行！！！");
			}
			//得到选中行
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(), 0);
			String paras[]={Bid};
			bModel=new BookModel();
			if(bModel.delete(paras)){
				JOptionPane.showMessageDialog(new JFrame(), "删除成功！！");
				//jt更新
				jt.setModel(bModel);
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "删除失败！！");
			}
			
			
		}
		
	}

}
