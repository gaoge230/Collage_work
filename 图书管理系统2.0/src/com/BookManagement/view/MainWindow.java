/*
 * 主界面
 * 
 */
package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.BookMangement.Model.*;
public class MainWindow extends JFrame implements ActionListener,MouseListener{
	//定义组件
	JPanel jp1,jp1_1,jp1_2,jp1_2_1,jp1_3,jp3_1;
	CardLayout cl1;
	//jp1_3的组件
	JPasswordField jPasswordField;
	JPanel jp1_3_0,jp1_3_1,jp1_3_2,jp1_3_3,jp1_3_4,jp1_3_5,jp1_3_6,jp1_3_7,jp1_3_8;
	JLabel jl1,jl2,jl3,jl3_1,jl3_2,jl3_3,jl3_4,jl3_5,jl3_6,jl3_7,jl3_8;
	JTextField jtf3_1,jtf3_2,jtf3_3,jtf3_4,jtf3_5,jtf3_6,jtf3_7,jtf3_8;
	JButton jp1_3_jb1,jp1_3_jb2;
	//jp1_2 的组件
	JLabel jp1_2_jl1,jp1_2_jl2;
	JButton jp1_2_jb1,jp1_2_jb2;
	JTextField jp1_2_jtf1,jp1_2_jtf2;
	JTable jt;
	JScrollPane jsp;
	//模型 
	BookModel bModel;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainWindow();
	}
	public MainWindow(){
		
		//卡片一 ————————显示三个功能   jp1_1
		jp1_1=new mypanel();
		jp1_1.setLayout(null);
		jp1_1.setBounds(0, 0, 800, 600);
		
		
		
		//创建光标
		Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
		jl1=new JLabel(new ImageIcon("image//查询1.png"));
		//jl1=new JLabel("dfgsd");

		jl1.setBackground(Color.gray);
		jl1.setOpaque(false);
		jl1.setBounds(277, 208,242,49);
		jl1.addMouseListener(this);
		jp1_1.add(jl1);
		jl1.setCursor(myCursor);
		
		jl2=new JLabel(new ImageIcon("image//登录2.png"));
		jl2.setBounds(275, 302, 244,48);
		jl2.addMouseListener(this);
		jp1_1.add(jl2);
		jl2.setCursor(myCursor);
		
		jl3=new JLabel(new ImageIcon("image//办卡1.png"));
		jl3.setBounds(275,343,244,150);
		jl3.addMouseListener(this);
		//jp1_1.add(jl3);
		jl3.setCursor(myCursor);
		
		//卡片二-------图书查询 jp1_2
		//北部
		JPanel jp1_2_0=new JPanel(new BorderLayout());
		jp1_2_0.setOpaque(false);
		jp1_2_1=new JPanel(new GridLayout(2,1));
		jp1_2_1.setOpaque(false);
		JPanel jp1_2_1_1=new JPanel();
		JPanel jp1_2_1_2=new JPanel();
		jp1_2_1_1.setOpaque(false);
		jp1_2_1_2.setOpaque(false);
		
		jp1_2_jl1=new JLabel("书名：");
		jp1_2_jl1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp1_2_jtf1=new JTextField(20);
		jp1_2_jl2=new JLabel("作者：");
		jp1_2_jl2.setFont(new Font("宋体", Font.PLAIN, 16));
		jp1_2_jtf2=new JTextField(20);
		jp1_2_1_1.add(jp1_2_jl1);
		jp1_2_1_1.add(jp1_2_jtf1);
		jp1_2_1_2.add(jp1_2_jl2);
		jp1_2_1_2.add(jp1_2_jtf2);
		jp1_2_1.add(jp1_2_1_1);
		jp1_2_1.add(jp1_2_1_2);
		
		jp1_2_jb1=new JButton("查询");
		jp1_2_jb1.setFont(new Font("宋体", Font.PLAIN, 16));
		jp1_2_jb1.setOpaque(false);
		jp1_2_jb1.addActionListener(this);
		jp1_2_jb2=new JButton(new ImageIcon("image//返回首页.png"));
		jp1_2_jb2.setOpaque(false);
		jp1_2_jb2.addActionListener(this);
		
		JPanel jp1_2_3=new JPanel();
		jp1_2_3.setOpaque(false);
		jp1_2_3.add(jp1_2_1);
		jp1_2_3.add(jp1_2_jb1);
		
		jp1_2_0.add(jp1_2_jb2,"West");
		jp1_2_0.add(jp1_2_3);
		
		//中部
		bModel=new BookModel();
		jt=new JTable(bModel);
		jsp=new JScrollPane(jt);
		
		jp1_2=new mypanel();
		jp1_2.setLayout(new BorderLayout());
		jp1_2.setOpaque(false);
		jp1_2.add(jp1_2_0,"North");
		jp1_2.add(jsp);
		
		
		
		//卡片三----//借书卡模板jp1_3
		jp3_1=new JPanel(new GridLayout(8, 1,0,0));
		//属性
		jp1_3_1=new JPanel();
		jp1_3_2=new JPanel();
		jp1_3_3=new JPanel();
		jp1_3_4=new JPanel();
		jp1_3_5=new JPanel();
		jp1_3_6=new JPanel();
		jp1_3_7=new JPanel();
		jp1_3_8=new JPanel();
		
		//设为透明
		jp3_1.setOpaque(false);
		jp1_3_1.setOpaque(false);
		jp1_3_2.setOpaque(false);
		jp1_3_3.setOpaque(false);
		jp1_3_4.setOpaque(false);
		jp1_3_5.setOpaque(false);
		jp1_3_6.setOpaque(false);
		jp1_3_7.setOpaque(false);
		jp1_3_8.setOpaque(false);
	
	
		jl3_1=new JLabel("学号：");
		jl3_1.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_2=new JLabel("姓名：");
		jl3_2.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_3=new JLabel("年龄：");
		jl3_3.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_4=new JLabel("性别：");
		jl3_4.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_5=new JLabel("专业：");
		jl3_5.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_6=new JLabel("地址：");
		jl3_6.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_7=new JLabel("联系方式：");
		jl3_7.setFont(new Font("宋体", Font.BOLD, 16));
		jl3_8=new JLabel("密码：");
		jl3_8.setFont(new Font("宋体", Font.BOLD, 16));
		//各属性输入框
		jtf3_1=new JTextField(20);
		jtf3_2=new JTextField(20);
		jtf3_3=new JTextField(20);
		jtf3_4=new JTextField(20);
		jtf3_5=new JTextField(20);
		jtf3_6=new JTextField(20);
		jtf3_7=new JTextField(20);
		//jtf3_8=new JTextField(20);
		jPasswordField=new JPasswordField(20);
		jp1_3_1.add(jl3_1);
		jp1_3_1.add(jtf3_1);
		jp1_3_2.add(jl3_2);
		jp1_3_2.add(jtf3_2);
		jp1_3_3.add(jl3_3);
		jp1_3_3.add(jtf3_3);
		jp1_3_4.add(jl3_4);
		jp1_3_4.add(jtf3_4);
		jp1_3_5.add(jl3_5);
		jp1_3_5.add(jtf3_5);
		jp1_3_6.add(jl3_6);
		jp1_3_6.add(jtf3_6);
		jp1_3_7.add(jl3_7);
		jp1_3_7.add(jtf3_7);
		jp1_3_8.add(jl3_8);
		jp1_3_8.add(jPasswordField);
		
		jp3_1.add(jp1_3_1);
		jp3_1.add(jp1_3_2);
		jp3_1.add(jp1_3_3);
		jp3_1.add(jp1_3_4);
		jp3_1.add(jp1_3_5);
		jp3_1.add(jp1_3_6);
		jp3_1.add(jp1_3_7);
		jp3_1.add(jp1_3_8);
	
				
		//办理借书卡面板
		jp1_3=new mypanel();
		jp1_3.setLayout(null);
		jp1_3_jb1=new JButton(new ImageIcon("image//办卡1.png"));
		jp1_3_jb1.setFont(new Font("黑体", Font.BOLD, 18));
		jp1_3_jb1.addActionListener(this);
		jp1_3_jb1.setBounds(330, 394, 150, 45);
		jp1_3_jb2=new JButton(new ImageIcon("image//返回首页.png"));
		jp1_3_jb2.setOpaque(false);
		jp1_3_jb2.setBackground(Color.PINK);
		jp1_3_jb2.addActionListener(this);
		jp1_3_jb2.setBounds(0, 0, 100, 70);
		jp3_1.setBounds(200, 20, 400, 350);
		jp1_3.add(jp1_3_jb2);
		jp1_3.add(jp3_1);
		jp1_3.add(jp1_3_jb1);
		
		
		//jp1设置为卡片布局
		cl1=new CardLayout();
		jp1=new JPanel(cl1);
		//加入各卡片
		jp1.add(jp1_1, "1");
		jp1.add(jp1_2,"2");
		jp1.add(jp1_3,"3");

//	/*	int width=Toolkit.getDefaultToolkit().getScreenSize().width;
//		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;*/
			
		//最底层面板是便捷布局，中间为一个卡片布局的面板
		this.add(jp1);
		
//		//设置标题图片
		Image im = null;
		try {
			im = ImageIO.read(new File("image//小图标.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIconImage(im);	
		this.setResizable(false);
		this.setTitle("图书管理系统！");
		this.setFont(new Font("宋体",Font.BOLD,18));
		this.setLocation(300, 50);
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class mypanel extends JPanel{
		Image im;
		public mypanel(){
			try {
				im=ImageIO.read(new File("image//背景2.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g){
			g.drawImage(im, 0, 0, 800, 600, this);
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//书籍查询
		if(arg0.getSource()==jp1_2_jb1){
			System.out.println("查询书籍！");
			//获得文本框中的数据
			if(jp1_2_jtf1.getText().trim().equals("")&&jp1_2_jtf2.getText().equals("")){
				System.out.println("空");
				JOptionPane.showMessageDialog(this, "不允许都为空");
				return;
			}
			
			//参数
			//jp1_2_jtf1.getText().trim()  书名
			//jp1_2_jtf2.getText()  作者
			String paras[]={"%"+jp1_2_jtf1.getText().trim()+"%","%"+jp1_2_jtf2.getText()+"%"};
			
			System.out.println(paras.length);
			for (String string : paras) {
				System.out.println(string);
			}
			bModel=new BookModel();
			bModel.query(paras);
			//更新table
			jt.setModel(bModel);
		}
		//申请图书卡
		if(arg0.getSource()==jp1_3_jb1){
			System.out.println("申请图书卡");
			ReadModel rModel=new ReadModel();
			//设置参数
			String paras[]={jtf3_1.getText().trim(),jtf3_2.getText().trim(),jtf3_3.getText().trim()
					,jtf3_4.getText().trim(),jtf3_5.getText().trim()
					,jtf3_6.getText().trim(),jtf3_7.getText().trim(),String.valueOf(jPasswordField.getPassword())};
			for (String string : paras) {
				System.out.println(string);
			}
			if(rModel.add(paras)){
				JOptionPane.showMessageDialog(this, "恭喜注册成功!!!");
				
				cl1.show(jp1, "1");
			}else {
				JOptionPane.showMessageDialog(this, "您输入的信息有误！");
			}
			
		}
		//从卡片3 返回首页
		if(arg0.getSource()==jp1_3_jb2){

			cl1.show(jp1, "1");
		}
		if(arg0.getSource()==jp1_2_jb2){

			cl1.show(jp1, "1");
		}
		
	}
	
	//鼠标在组建上按下
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//图书查询
		if(e.getSource()==jl1){
			System.out.println("图书查询！");
			cl1.show(jp1, "2");
			
//			BookModel bm=new BookModel();
//			String paras[]={"我","给","1"};
//			if(bm.update(paras)){
//				JOptionPane.showMessageDialog(this, "删除成功！");
//			}
			
		}
		//用户登录
		if(e.getSource()==jl2){
			System.out.println("用户登录！");
			new Userlogin(this);
			

		}
		//办理借书卡
		if(e.getSource()==jl3){
			System.out.println("办理借书卡！");
		
			cl1.show(jp1, "3");
			
//			ReadModel rm=new ReadModel();
//			String paras[]={"1307064108","123","读者"};
//			if(rm.add(paras)){
//				JOptionPane.showMessageDialog(this, "恭喜注册成功!!!");
//			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标进入
		if(e.getSource()==jl1){

			
		}
		if(e.getSource()==jl2){
			System.out.println("dianji jl2");
		}
		if(e.getSource()==jl3){
			System.out.println("dianji jl3");
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		//鼠标离开
		if(e.getSource()==jl1){
			System.out.println("dianji jl1");

			
		}
		if(e.getSource()==jl2){
			System.out.println("dianji jl2");
		}
		if(e.getSource()==jl3){
			System.out.println("dianji jl3");
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

