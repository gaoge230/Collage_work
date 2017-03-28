/*
 * 开始动画。
 */
package com.BookManagement.view;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
public class Begin extends JWindow implements Runnable{
	//定义进度条
	JProgressBar jpb;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Begin b=new Begin();
		Thread t=new Thread(b);
		t.start();
	}
	public Begin(){
		mypanel mp=new mypanel();
		jpb=new JProgressBar();
		jpb.setBackground(Color.BLACK);//设置背景颜色
		jpb.setForeground(Color.GREEN);//设置进度条填充颜色
		jpb.setBorderPainted(false);//设置进度条边框不显示
		
		
		this.add(mp,"Center");
		this.add(jpb,"South");
		//创建进程
		Thread k=new Thread(mp);
		k.start();
		this.setLocation(400,150);
		this.setSize(510,340);
		this.setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("进入线程！！");
		int i=0;
		int a[]={0,10,20,25,30,35,40,50,55,60,65,75,80,90,100,100};
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			jpb.setValue(a[i]);
			i++;
			if(i>=a.length) break;
		}
		//线程结束将会跳入图书管理界面
		new MainWindow();
		//关闭此jwindow
		this.dispose();
		System.out.println("线程结束！！");
	}

}


class mypanel extends JPanel implements Runnable{
	
	ImageIcon a=null;//方法二
	Image im;//方法一
	String Hanzi="";//存储要绘制的汉子
	JLabel jl1;
	
	//重写paint
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		//显示图片
		//arg0.drawImage(im, 0, 0, 400, 390,this);
		
		//arg0.setFont(new Font("华文行楷",Font.BOLD,30));
		arg0.setColor(Color.YELLOW);
		paintHanzi(arg0, Hanzi);
	}
	//画字的函数
	public void paintHanzi(Graphics arg0,String a){
		
		String []l=a.split(",");
		int hight=80;
		for (String string : l) {
			if(string.equals(l[0])){
				arg0.setFont(new Font("华文行楷",Font.BOLD,52));
				arg0.drawString(string, 100, hight);
				hight+=70;
				continue;
			}
			arg0.setColor(Color.BLACK);
			arg0.setFont(new Font("华文行楷",Font.BOLD,30));
			arg0.drawString(string, 100, hight);
			hight+=50;
		}
		
	}
	
	//构造函数
	public mypanel(){
		/*//读取图片
		try {
			im=ImageIO.read(new File("image//psb.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		jl1=new JLabel(new ImageIcon("image//Begin.jpg"));
		this.add(jl1);
	
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("进入线程2");
		String a[]={"图书管理系统!,","组长：高歌,","组员：武培文,","组员：王春晓,","组员：袁向明"};
		int k=0;
		while (true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Hanzi="";
			for(int i=0;i<k;i++){
				Hanzi=Hanzi+a[i];
			}
				
				
			System.out.println(Hanzi);
			//重绘
			this.repaint();
			//结束线程
			if(k>=a.length) break;
			k=k+1;
		
		}
		System.out.println("结束线程2");
	}

	
}
