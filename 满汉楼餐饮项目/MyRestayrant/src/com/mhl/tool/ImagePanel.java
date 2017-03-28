/*
 * ����һ�����Զ�̬����һ��ͼƬ��������jpanel
 */
package com.mhl.tool;

import javax.swing.JPanel;
import javax.imageio.*;
import java.awt.*;

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
