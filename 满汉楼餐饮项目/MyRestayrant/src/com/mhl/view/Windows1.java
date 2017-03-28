/*
 * 2015.12.3
 * 这是系统管理员，经理，主管可以进入的操作界面
 * 完成界面的顺序：从上到下，从左到右
 */
package com.mhl.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;
import java.util.Calendar;

import com.mhl.tool.*;

import javax.imageio.*;

public class Windows1 extends JFrame implements ActionListener,MouseListener{
	//定义需要的组件
	//标题图片
	Image title,timebj,p1_bj,p3_bj;
	JMenuBar jmb;
	//一级菜单
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//二级菜单
	JMenuItem jm1_1,jm1_2,jm1_3,jm1_4,jm1_5;
	//图标
	ImageIcon jm1_1_icon1,jm1_1_icon2,jm1_1_icon3,jm1_1_icon4,jm1_1_icon5;
	//工具栏
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//定义面板
	JPanel jp1,jp2,jp3,jp4,jp5;
	//显示当前时间
	JLabel timeNow;
	//中间需要的组件
	//p1的组件
	JLabel jp1_jl1,jp1_jl2,jp1_jl3,jp1_jl4,jp1_jl5,jp1_jl6,jp1_jl7,jp1_jl8;
	//p2的jlabel
	JLabel jp2_jl1,jp2_jl2;
	//javax.swing包中的timer可以定时的触发action时间。
	javax.swing.Timer t;
	//定义拆分窗口
	JSplitPane jsp1;
	CardLayout ctCardLayout,cardp2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Windows1 windows1=new Windows1();
	}
	
	//初始化菜单
	public void initMenu(){
		//菜单
		   //设置图标创建图表
		jm1_1_icon1=new ImageIcon("image/jm1_1_icon1.jpg");
		jm1_1_icon2=new ImageIcon("image/jm1_1_icon2.jpg");
		jm1_1_icon3=new ImageIcon("image/jm1_1_icon3.jpg");
		jm1_1_icon4=new ImageIcon("image/jm1_1_icon4.jpg");
		jm1_1_icon5=new ImageIcon("image/jm1_1_icon5.jpg");
		jmb=new JMenuBar();
		   //创建一级菜单
		jm1=new JMenu("系统管理");
		jm1.setFont(MyTools.f1);
		   //创建二级菜单
		jm1_1=new JMenuItem("切换用户",jm1_1_icon1);
		jm1_2=new JMenuItem("切换到收款界面",jm1_1_icon2); 
		jm1_3=new JMenuItem("登录管理",jm1_1_icon3); 
		jm1_4=new JMenuItem("万年历",jm1_1_icon4); 
		jm1_5=new JMenuItem("退出",jm1_1_icon5); 
		   //设置字体
		jm1_1.setFont(MyTools.f2);
		jm1_2.setFont(MyTools.f2);
		jm1_3.setFont(MyTools.f2);
		jm1_4.setFont(MyTools.f2);
		jm1_5.setFont(MyTools.f2);
		   //加入
		jm1.add(jm1_1);
		jm1.add(jm1_2);
		jm1.add(jm1_3);
		jm1.add(jm1_4);
		jm1.add(jm1_5);
		
		jm2=new JMenu("人事管理");
		jm2.setFont(MyTools.f1);
		jm3=new JMenu("菜单服务");
		jm3.setFont(MyTools.f1);
		jm4=new JMenu("报表统计");
		jm4.setFont(MyTools.f1);
		jm5=new JMenu("成本及库房");
		jm5.setFont(MyTools.f1);
		jm6=new JMenu("帮助");
		jm6.setFont(MyTools.f1);
		
		//将一级菜单加入jmenubar
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//将jmenubar加入jframe
		this.setJMenuBar(jmb);
	}
	
	//初始化北部的工具栏
	public void initToolBar(){
		//设置北部的工具栏；
				jtb=new JToolBar();
				   //把工具栏设置为不可以浮动
				jtb.setFloatable(false);
				jb1=new JButton(new ImageIcon("image/toolbar/jb1.jpg"));
				jb2=new JButton(new ImageIcon("image/toolbar/jb2.jpg"));
				jb3=new JButton(new ImageIcon("image/toolbar/jb3.jpg"));
				jb4=new JButton(new ImageIcon("image/toolbar/jb4.jpg"));
				jb5=new JButton(new ImageIcon("image/toolbar/jb5.jpg"));
				jb6=new JButton(new ImageIcon("image/toolbar/jb6.jpg"));
				jb7=new JButton(new ImageIcon("image/toolbar/jb7.jpg"));
				jb8=new JButton(new ImageIcon("image/toolbar/jb8.jpg"));
				jb9=new JButton(new ImageIcon("image/toolbar/jb9.jpg"));
				jb10=new JButton(new ImageIcon("image/toolbar/jb10.jpg"));
				jtb.add(jb1);
				jtb.add(jb2);
				jtb.add(jb3);
				jtb.add(jb4);
				jtb.add(jb5);
				jtb.add(jb6);
				jtb.add(jb7);
				jtb.add(jb8);
				jtb.add(jb9);
				jtb.add(jb10);
	}
	//初始化南部的时间函数
	public void initTime(){
		       //处理南部的状态栏
				jp5=new JPanel(new BorderLayout());
				timeNow=new JLabel("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"     ");//获得当前时间
				timeNow.setFont(MyTools.f1);
				
				  //创建timer
				t=new Timer(1000, this); //每隔1000毫秒，去触发一个actionevent事件
				  //启动该定时器
				t.start();
				  //设置背景
				try {
					timebj=ImageIO.read(new File("image/time_bg.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   //创建背景panel
				ImagePanel ip1=new ImagePanel(timebj);
				   //放大左边
				ip1.setLayout(new BorderLayout());
				ip1.add(timeNow,"East");
				jp5.add(ip1);
	}
	//初始化中间
	public void initCenter(){
		//处理中间的部分
				jp1=new JPanel(new BorderLayout());
				//获得背景图片
				try {
					p1_bj=ImageIO.read(new File("image/center/jp1_bg.jpg"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				//创建光标
				Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
				//创建带背景的面板
				ImagePanel ip2=new ImagePanel(p1_bj);
				//设置ip2为网格布局
				ip2.setLayout(new GridLayout(8, 1));
				//创建组件jlabel
				jp1_jl1=new JLabel(new ImageIcon("image/center/label_1.gif"));
				ip2.add(jp1_jl1,0);
				jp1_jl2=new JLabel("人 事 登 记",new ImageIcon("image/center/label_2.jpg"),0);
				//设置字体
				jp1_jl2.setFont(MyTools.f3);
				//设置jlabel为禁用
				jp1_jl2.setEnabled(false);
				//注册鼠标监听
				jp1_jl2.addMouseListener(this);
				//设置鼠标进入后的形状(手状光标)
				jp1_jl2.setCursor(myCursor);
				ip2.add(jp1_jl2,1);
				jp1_jl3=new JLabel("登 录 管 理",new ImageIcon("image/center/label_3.jpg"),0);
				jp1_jl3.setFont(MyTools.f3);
				jp1_jl3.setEnabled(false);
				jp1_jl3.addMouseListener(this);
				jp1_jl3.setCursor(myCursor);
				ip2.add(jp1_jl3,2);
				jp1_jl4=new JLabel("菜 谱 价 格",new ImageIcon("image/center/label_4.jpg"),0);
				jp1_jl4.setFont(MyTools.f3);
				jp1_jl4.setEnabled(false);
				jp1_jl4.addMouseListener(this);
				jp1_jl4.setCursor(myCursor);
				ip2.add(jp1_jl4,3);
				jp1_jl5=new JLabel("报 表 统 计",new ImageIcon("image/center/label_5.jpg"),0);
				jp1_jl5.setFont(MyTools.f3);
				jp1_jl5.setEnabled(false);
				jp1_jl5.addMouseListener(this);
				jp1_jl5.setCursor(myCursor);
				ip2.add(jp1_jl5,4);
				jp1_jl6=new JLabel("成本及库房",new ImageIcon("image/center/label_6.jpg"),0);
				jp1_jl6.setFont(MyTools.f3);
				jp1_jl6.setEnabled(false);
				jp1_jl6.addMouseListener(this);
				jp1_jl6.setCursor(myCursor);
				ip2.add(jp1_jl6,5);
				jp1_jl7=new JLabel("系 统 设 置",new ImageIcon("image/center/label_7.jpg"),0);
				jp1_jl7.setFont(MyTools.f3);
				jp1_jl7.setEnabled(false);
				jp1_jl7.addMouseListener(this);
				jp1_jl7.setCursor(myCursor);
				ip2.add(jp1_jl7,6);
				jp1_jl8=new JLabel("动 画 帮 助",new ImageIcon("image/center/label_8.jpg"),0);
				jp1_jl8.setFont(MyTools.f3);
				jp1_jl8.setEnabled(false);
				jp1_jl8.addMouseListener(this);
				jp1_jl8.setCursor(myCursor);
				ip2.add(jp1_jl8,7);
				//将背景面板加入jp1中
				jp1.add(ip2);
				
				//处理jp2,jp3,jp4;
				cardp2=new CardLayout();
				jp2=new JPanel(cardp2);
				
				ctCardLayout=new CardLayout();
				jp3=new JPanel(ctCardLayout);
				jp4=new JPanel(new BorderLayout());
				//给p2 加入jlabel
				jp2_jl1=new JLabel(new ImageIcon("image/center/jp2_left.jpg"));
				jp2_jl2=new JLabel(new ImageIcon("image/center/jp2_right.jpg"));
				jp2_jl1.addMouseListener(this);
				jp2_jl2.addMouseListener(this);
				jp2_jl1.setCursor(myCursor);
				jp2_jl2.setCursor(myCursor);
				jp2.add(jp2_jl1,"left");
				jp2.add(jp2_jl1,"right");
				//给p3加一个主界面卡片
				try {
					p3_bj=ImageIO.read(new File("image/center/p3_bj.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImagePanel ip3=new ImagePanel(p3_bj);
				
				
				jp3.add(ip3,"0");
				
				jp3.add(new EmpInfo(),"1");
				
				//对jp3添加几个jlabel 做测试

				JLabel dl=new JLabel(new ImageIcon("/image/center/jp3_3.jpg"));
				Image p3_bj3=null;
				try {
					p3_bj3=ImageIO.read(new File("image/center/jp3_3.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImagePanel ip4=new ImagePanel(p3_bj3);
				jp3.add(ip4,"2");
				JLabel rs=new JLabel(new ImageIcon("/image/center/jp3_2.jpg"));
				//把p2，p3加入p4中
				jp4.add(jp2,"West");
				jp4.add(jp3,"Center");
				
				 //做一个拆分窗口，分别存放p1 与 p4
				jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
				//设置左边的大小
				jsp1.setDividerLocation(120);
				//把边界线设为0
				jsp1.setDividerSize(0);
	}
	//构造函数
	public Windows1(){
		//设置布局
		this.setLayout(new BorderLayout());
		 //从jframe中取得容器Container
		Container ct=this.getContentPane();
		
		//调用初始化菜单函数
		this.initMenu();
		//调用初始化工具栏函数
		this.initToolBar();
		//调用初始化中部的函数
		this.initCenter();
		//初始化南部时间
		this.initTime();
		
		//加入各部分
		ct.add(jtb,"North");
		ct.add(jsp1,"Center");
		ct.add(jp5,"South");
		
		
		//设置顶层容器Jframe的标题图片
		try {
			title=ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setTitle("满汉楼餐饮管理系统！");
		this.setFont(MyTools.f3);
		this.setIconImage(title);
		//设置大小
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h-35);
		//关闭窗口时，退出系统
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//重新获得当前时间
		this.timeNow.setText("当前时间："+Calendar.getInstance().getTime().toLocaleString()+"     ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//当用户点击了jlabel，相应相应的卡片
		if(e.getSource()==jp1_jl2){
			//点击人事管理
			ctCardLayout.show(jp3, "1");
		}else if (e.getSource()==jp1_jl3){
			//点击登录管理
			ctCardLayout.show(jp3, "2");
		}else if(e.getSource()==jp1_jl4){
			
		}else if(e.getSource()==jp1_jl5){
			
		}else if(e.getSource()==jp1_jl6){
			
		}else if(e.getSource()==jp1_jl7){
			
		}else if(e.getSource()==jp1_jl8){
			
		}else if(e.getSource()==jp2_jl1){
			//点击左按钮 》》》》》切换cardp2
			this.cardp2.show(jp2, "right");
			//将拆分窗口jsp1切换
			jsp1.setDividerLocation(0);
			
		}else if(e.getSource()==jp2_jl2){
			//点击右按钮》》》》》》切换cardp2
			this.cardp2.show(jp2, "left");
			//将拆分窗口jsp1切换
			jsp1.setDividerLocation(120);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户鼠标进入了某个jlabel则高亮
		if(e.getSource()==jp1_jl2){
			jp1_jl2.setEnabled(true);
		}else if(e.getSource()==jp1_jl3){
			jp1_jl3.setEnabled(true);
		}else if(e.getSource()==jp1_jl4){
			jp1_jl4.setEnabled(true);
		}else if(e.getSource()==jp1_jl5){
			jp1_jl5.setEnabled(true);
		}else if(e.getSource()==jp1_jl6){
			jp1_jl6.setEnabled(true);
		}else if(e.getSource()==jp1_jl7){
			jp1_jl7.setEnabled(true);
		}else if(e.getSource()==jp1_jl8){
			jp1_jl8.setEnabled(true);
		}
		
			
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//如果用户选择了某个jlabel则禁用
		if(e.getSource()==jp1_jl2){
			jp1_jl2.setEnabled(false);
		}else if(e.getSource()==jp1_jl3){
			jp1_jl3.setEnabled(false);
		}else if(e.getSource()==jp1_jl4){
			jp1_jl4.setEnabled(false);
		}else if(e.getSource()==jp1_jl5){
			jp1_jl5.setEnabled(false);
		}else if(e.getSource()==jp1_jl6){
			jp1_jl6.setEnabled(false);
		}else if(e.getSource()==jp1_jl7){
			jp1_jl7.setEnabled(false);
		}else if(e.getSource()==jp1_jl8){
			jp1_jl8.setEnabled(false);
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

}
