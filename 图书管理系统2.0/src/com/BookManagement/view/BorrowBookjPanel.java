package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.BookMangement.Model.*;

public class BorrowBookjPanel extends JPanel implements ActionListener{

	//定义组件
	JPanel jp1,jp1_0,jp1_1,jp1_2,jp1_3;
	JLabel jl1,jl2;
	JTextField jtf1,jtf2;
	JButton jb1;
	JTable jt;
	JScrollPane jsp;
	JPanel jp2;
	JButton jb2;
	//book模型
	BookModel bModel;
	
	private String identity;//标记登陆者身份
	private String id;//标记登陆者的id
	
	public BorrowBookjPanel(String id,String identity){
		this.id=id;
		this.identity=identity;
		
		//总体是边界布局 北部是网格
		jp1=new JPanel(new GridLayout(2,2));
		
		jp1_1=new JPanel();
		jp1_2=new JPanel();
		jp1_3=new JPanel();
		jl1=new JLabel("读者学号：");
		jl1.setFont(new Font("宋体", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		
		jl2=new JLabel("图书编号：");
		jl2.setFont(new Font("宋体", Font.PLAIN, 16));
		jtf2=new JTextField(20);
		
		jp1_1.add(jl1);
		jp1_1.add(jtf1);
		jp1_2.add(jl2);
		jp1_2.add(jtf2);
		
		jb1=new JButton("查询");
		jb1.setFont(new Font("宋体", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jp1_3.add(jb1);
		jp1_0=new JPanel();
		jp1.add(jp1_1);
		jp1.add(jp1_0);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		//中部
		bModel=new BookModel();
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		
		//东部
		jp2=new JPanel();
		jb2=new JButton("选中借阅！");
		jb2.addActionListener(this);
		jb2.setFont(new Font("宋体", Font.PLAIN, 16));
		jb2.setBounds(0, 100, 50, 20);
		jp2.add(jb2);
		
		
		this.setLayout(new BorderLayout());
		this.add(jp1,"North");
		this.add(jsp);
		this.add(jp2,"East");

		this.setLocation(200, 50);
		this.setSize(1000, 600);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//点击了查询书籍的按钮
		if(arg0.getSource()==jb1){
			System.out.println("查询书籍！");
			//获得文本框中的数据 ，不允许同时为空
			
			if(jtf1.getText().trim().equals("")&&jtf2.getText().equals("")){
				System.out.println("空");
				JOptionPane.showMessageDialog(this, "不允许都为空");
				return;
			}
			//得到查询参数（及要查询的书籍的编号） 模糊查询
			String paras[]={"%"+jtf2.getText().trim()+"%"};
			
			for (String string : paras) {
				System.out.println(string);
			}
			
			//更新table来显示查询结果
			bModel=new BookModel();
			bModel.queryBid(paras);
			jt.setModel(bModel);	
			
		}
		
		//点击了借阅书籍的按钮
		if(arg0.getSource()==jb2){
			//判断用户是不是选中了一行
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "请选中一行！！！");
				return;
			}
			System.out.println("借阅");
			//得到选中行的第一列 （也就是书籍的编号）
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(), 0);
			//得到该书的库存量（以供后续判断）
			int Bstock=Integer.parseInt((String)bModel.getValueAt(jt.getSelectedRow(), 6));
			
			System.out.println(Bid);
			System.out.println(Bstock);
			
			//得到当前借阅时间
			Date date=new Date();
			String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			java.sql.Timestamp goodsTimestamp=java.sql.Timestamp.valueOf(nowtime);
			System.out.println(nowtime);
			System.out.println(goodsTimestamp);
			//判断该书的库存量是否充足
			if(Bstock<1){	
				JOptionPane.showMessageDialog(new JFrame(), "库存量不够！！");
				
			}else{
				
				BorrowModel bModel=new BorrowModel();
				//设置参数，此处归还时间设为NULL，表示此书没有归还
				String paras[]={this.jtf1.getText().trim(),Bid,nowtime,"30",null,"1"};
				//判读是否借阅成功（相当于该书的库存量减一）
				if(bModel.add(paras)){
					
					//修改数量该书的数量
					String p[]={Bid};
					BookModel bookModel=new BookModel();
					if(bookModel.reduceStock(p)){
						JOptionPane.showMessageDialog(new JFrame(), "借阅成功！！！");
			
						
					}
					else{
						JOptionPane.showMessageDialog(new JFrame(), "数量修改失败！！！");
					}
					
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "借阅失败！！！");
				}
						
				
			}
				
		}
		
	}

}
