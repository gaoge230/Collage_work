/*
f * 登录界面
 */
package com.BookManagement.view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.tools.Tool;

import com.BookMangement.Model.*;
public class Userlogin extends JDialog implements ActionListener,KeyListener{
	//定义组件
	JPanel jp1;
	JLabel jl1,jl2;
	JTextField jtf;
	JPasswordField jpf;
	JButton jb1,jb2;
	
	MainWindow mainWindow;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new Userlogin();
	}
	
	public Userlogin(MainWindow mainWindow) {
		// TODO Auto-generated constructor stub
		this.mainWindow=mainWindow;
		
		//创建组件
		//创建一个BackImage对象
		BackImage bi=new BackImage();
		bi.setLayout(null);
		//bi.setOpaque(false);
		//把位置确定
		bi.setBounds(0, 0, 560, 330);
		jp1=new JPanel();
		jl1=new JLabel();
		jl2=new JLabel();
		jtf=new JTextField(20);
		jtf.setBounds(273, 175, 150, 27);
		jpf=new JPasswordField(20);
		jpf.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					jb1.doClick();
				    
				}
			}
			
		});
		
		jpf.setBounds(273, 212, 150, 27);
		
		
		jb1=new JButton(new ImageIcon("image//1.jpg"));
		//jb1.setOpaque(true);
		//jb1.setBorder(BorderFactory.createLoweredBevelBorder());
		jb1.setBounds(259,257,63,25);
		jb1.addActionListener(this);
		
		jb2=new JButton(new ImageIcon("image//2.jpg"));
		//jb2.setOpaque(true);
		jb2.setBounds(340,257,63,25);
		jb2.addActionListener(this);
		
		
		
		bi.add(jtf);
		bi.add(jpf);
		bi.add(jb1);
		bi.add(jb2);
		this.add(bi);
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int hight=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//设置此窗体在所有窗体前面
		
		this.setAlwaysOnTop(true);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setLocation(width/2-300,hight/2-200);
		this.setSize(560,330);
		this.setVisible(true);
		
	}
	//定义一个内部类
		class BackImage extends JPanel{
			
			Image im;
			//构造函数
			public BackImage(){
				try {
					im=ImageIO.read(new File("image//登录界面.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
			public void paintComponent(Graphics g){
				g.drawImage(im, 0, 0, 560, 330, this);
			}
		}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb1){
			if(jtf.getText().trim().equals("")||jpf.getPassword().toString().trim().equals("")){
				JOptionPane.showMessageDialog(new JFrame(), "账号，密码不能为空！！！");
				return;
			}
			System.out.println("登录");
			//获得登录的 id  和   密码。
			String id=jtf.getText().toString().trim();
			String  passwd=new String(jpf.getPassword());
			System.out.println("ID:"+id +"密码:"+passwd);
			
			//查询登录身份及是否正确 返回身份或空
			UserModel um=new  UserModel();
			String identity=um.login(id,passwd);
			
			System.out.println(identity.trim().equals("读者"));
			
			if(identity.trim().equals("读者")){
				System.out.println(identity);
				//以读者身份进入界面
				mainWindow.dispose();
				this.dispose();
				new UserClient(id,identity);
				

			}else if (identity.trim().equals("管理员")){
				System.out.println(identity);
				//以管理员身份进入界面
				mainWindow.dispose();
				this.dispose();
				new UserClient(id,identity);
				
			}else {
				JOptionPane.showMessageDialog(this, "账号密码输入有误！！！");
			}
			jtf.setText("");
			jpf.setText("");
			
		}
		if(arg0.getSource()==jb2){
			System.out.println("关闭");
			//关闭，释放
			this.dispose();
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_UP){
			//jb1.doClick();
		    System.out.println("DGGDFSGDGDFG");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
