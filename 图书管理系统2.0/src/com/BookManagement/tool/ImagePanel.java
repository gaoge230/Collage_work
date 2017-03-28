package com.BookManagement.tool;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	Image image;
	//g���캯��ȥָ��jpanel��С
	public ImagePanel(Image im){
		//ϣ�����Ĵ�С������Ӧ��
		this.image=im;
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h);
	}
	
	//��������paint �ǻ�������     paintComponent�ǻ�������
	public void paintComponent(Graphics g){
		//����
		super.paintComponents(g);
		g.drawImage(image,0,0, this.getWidth(),this.getHeight(),this);
		
	}
}
