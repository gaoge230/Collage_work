package com.BookManagement.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.BookMangement.Model.BookModel;
import com.BookMangement.Model.BorrowModel;
import com.BookMangement.Model.UserModel;

public class ReturnPanel extends JPanel implements ActionListener{
	//定义
	JTable jt;
	public BorrowModel getbModel() {
		return bModel;
	}
	public void setbModel(BorrowModel bModel) {
		this.bModel = bModel;
	}
	public JTable getJt() {
		return jt;
	}
	public void setJt(JTable jt) {
		this.jt = jt;
	}
	JTextField jtf1;
	JScrollPane jsp;
	BorrowModel bModel;
	JLabel jl1;
	JButton jb1,jb2;
	JPanel jp1;
	private String identity;//标记登陆者身份
	private String id;//标记登陆者的id
	public ReturnPanel(String id,String identity){
		this.id=id;
		this.identity=identity;
		
		//创建北部
		jl1=new JLabel("借阅列表如下：");
		jl1.setFont(new Font("宋体", Font.PLAIN, 30));
		jl1.setAlignmentX(LEFT_ALIGNMENT);//设置垂直对齐
		JLabel jl2=new JLabel("读者编号：");
		jl2.setFont(new Font("宋体", Font.PLAIN, 16));
		jtf1=new JTextField(20);
		jb2=new JButton("查询");
		jb2.setFont(new Font("宋体", Font.PLAIN, 16));
		jb2.addActionListener(this);
		JPanel jp2=new JPanel();
		JPanel jp3=new JPanel(new GridLayout(2,1));
		jp2.add(jl2);
		jp2.add(jtf1);
		jp2.add(jb2);
		jp3.add(jl1);
		jp3.add(jp2);
		
		
		//创建中间
		bModel=new BorrowModel();
		//String paras[]={id};
		//bModel.query(paras);
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		//创建东部
		jp1=new JPanel();
		jb1=new JButton("选中还书！");
		jb1.setFont(new Font("宋体", Font.PLAIN, 16));
		jb1.addActionListener(this);
		jb1.setAlignmentY(HEIGHT);
		jp1.add(jb1);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(jp3,"North");
		this.add(jsp);
		this.add(jp1,"East");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//点击还书
		if(arg0.getSource()==jb1){
			if(jt.getSelectedRow()==-1){
				JOptionPane.showMessageDialog(new JFrame(), "请选中一行！！！");
				return;
			}
			//得到当前归还时间
			//得到点击当前系统时间
			Date date=new Date();
			String nowtime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
			System.out.println(nowtime);
			//得到点击的学号 和 书编号主键
			
			id=(String)bModel.getValueAt(jt.getSelectedRow(),0);
			String Bid=(String)bModel.getValueAt(jt.getSelectedRow(),1);
			String Ftime=(String)bModel.getValueAt(jt.getSelectedRow(),4);
			int dead=Integer.parseInt((String)bModel.getValueAt(jt.getSelectedRow(),5));
			//生成参数(时间，读者id，书id)
			String paras[]={nowtime,id,Bid};
			bModel=new BorrowModel();
			
			//写入归还时间
			if(bModel.upReturnTime(paras)){
				//增加该书的Stock
				BookModel bookModel=new BookModel();
				String p2[]={Bid};
				if(bookModel.addStock(p2)){
				//还书成功并显示是否超期，并提示超期多少天
				UserModel uModel=new UserModel();
				String tishi="没有超期！！";
				int day=uModel.DeadTime(Ftime, dead);
				if(day>0){
					 tishi="超期"+day+"天！";
				}
				JOptionPane.showMessageDialog(new JFrame(), "还书成功，"+tishi);
				
				//更新table
				bModel=new BorrowModel();
				String p[]={id};
				bModel.query(p);
				jt.setModel(bModel);

				
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "增加数量失败！！");
				}
				
			}else{
				JOptionPane.showMessageDialog(new JFrame(), "还书失败！！");
			}
			

		}
		
		if(arg0.getSource()==jb2){
			bModel=new BorrowModel();
			String paras[]={jtf1.getText().trim()};
			bModel.query(paras);
			jt.setModel(bModel);
		}
	}
}
