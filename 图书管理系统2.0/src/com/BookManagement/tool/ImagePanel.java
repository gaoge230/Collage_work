package com.BookManagement.tool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	Image image;
	//g构造函数去指定jpanel大小
	public ImagePanel(Image im){
		//希望他的大小是自适应的
		this.image=im;
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	
	//画出背景paint 是画整个的     paintComponent是画容器的
	public void paintComponent(Graphics g){
		//清屏
		super.paintComponents(g);
		g.drawImage(image,0,0, this.getWidth(),this.getHeight(),this);
		
	}
}
