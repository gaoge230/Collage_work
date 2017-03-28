/*
 * 好友列表（包括黑名单，陌生人）
 */
package com.qq.client.view;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import com.qq.client.tools.*;

public class QqFriendlist extends JFrame implements ActionListener,MouseListener{
	
	    //定义第一张卡片（好友）
	    JPanel jphy1,jphy2,jphy3;
	    JButton jphy_jb1,jphy_jb2,jphy_jb3;
	    JScrollPane jsp1;
	    
	    //定义第二张卡片（陌生人）
	    JPanel jpmsr1,jpmsr2,jpmsr3;
	    JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	    JScrollPane jsp2;
	    
	    //定义JFrame为卡片布局CardLayout
	    CardLayout cl;
	    //定义全局
	    String owner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendlist qqfriendlist=new QqFriendlist();

	}
	//在标题栏显示当前用户的id
	public QqFriendlist(String ownerid)
	{   this.owner=ownerid;
		//处理第一张卡片（边界布局）
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		//监听按钮
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		
		jphy1=new JPanel(new BorderLayout());
		//中间是JScrollPane(可以滚动) 上加一个JPane
		jphy2=new JPanel(new GridLayout(50,1,4,4));//假设50个人间距4
		//给jphy2初始化50个人
		JLabel []jbls=new JLabel[50];
		
		for(int i=0;i<50;i++)
		{
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/tx.gif"),JLabel.LEFT);
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
			
		}
		jphy3=new JPanel(new GridLayout(2,1));
		//把按钮驾到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		//初始化第一张卡片
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//处理第二张卡片（边界布局）
		jpmsr_jb1=new JButton("我的好友");
		//监听jpmsr_jb1
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
				
		jpmsr1=new JPanel(new BorderLayout());
		//中间是JScrollPane(可以滚动) 上加一个JPane
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));//假设50个人间距4
		//给jpmsr2初始化20个人
		JLabel []jbls2=new JLabel[20];
				
		for(int i=0;i<jbls2.length;i++)
		{
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/tx.gif"),JLabel.LEFT);
			jpmsr2.add(jbls2[i]);
					
		}
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把按钮驾到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
				
				
		jsp2=new JScrollPane(jpmsr2);
				
		//初始化第二张卡片
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
        cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//设标题栏显示id
		this.setTitle(ownerid);
		this.setSize(160,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果用户点击了陌生人就显示第二张卡片
		if(arg0.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}else if(arg0.getSource()==jpmsr_jb1)
		{
			cl.show(this.getContentPane(), "1");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//响应用户双击的事件，并得到好友的编号
		if(e.getClickCount()==2)
		{
			//得到好友的编号
			String friendNo=((JLabel)e.getSource()).getText();
			System.out.println("你希望和"+friendNo+"聊天");
			Qqchat qqchat=new Qqchat(owner,friendNo);
			
			//把聊天界面加入到管理类中
			ManangeQqchat.addQqchat(this.owner+" "+friendNo, qqchat);
			
			
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//用户鼠标知道那就变成红色
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
		
	}

}
