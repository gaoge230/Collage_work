/*
 * ��ʼ������
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
	//���������
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
		jpb.setBackground(Color.BLACK);//���ñ�����ɫ
		jpb.setForeground(Color.GREEN);//���ý����������ɫ
		jpb.setBorderPainted(false);//���ý������߿���ʾ
		
		
		this.add(mp,"Center");
		this.add(jpb,"South");
		//��������
		Thread k=new Thread(mp);
		k.start();
		this.setLocation(400,150);
		this.setSize(510,340);
		this.setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("�����̣߳���");
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
		//�߳̽�����������ͼ��������
		new MainWindow();
		//�رմ�jwindow
		this.dispose();
		System.out.println("�߳̽�������");
	}

}


class mypanel extends JPanel implements Runnable{
	
	ImageIcon a=null;//������
	Image im;//����һ
	String Hanzi="";//�洢Ҫ���Ƶĺ���
	JLabel jl1;
	
	//��дpaint
	@Override
	public void paint(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paint(arg0);
		//��ʾͼƬ
		//arg0.drawImage(im, 0, 0, 400, 390,this);
		
		//arg0.setFont(new Font("�����п�",Font.BOLD,30));
		arg0.setColor(Color.YELLOW);
		paintHanzi(arg0, Hanzi);
	}
	//���ֵĺ���
	public void paintHanzi(Graphics arg0,String a){
		
		String []l=a.split(",");
		int hight=80;
		for (String string : l) {
			if(string.equals(l[0])){
				arg0.setFont(new Font("�����п�",Font.BOLD,52));
				arg0.drawString(string, 100, hight);
				hight+=70;
				continue;
			}
			arg0.setColor(Color.BLACK);
			arg0.setFont(new Font("�����п�",Font.BOLD,30));
			arg0.drawString(string, 100, hight);
			hight+=50;
		}
		
	}
	
	//���캯��
	public mypanel(){
		/*//��ȡͼƬ
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
		System.out.println("�����߳�2");
		String a[]={"ͼ�����ϵͳ!,","�鳤���߸�,","��Ա��������,","��Ա��������,","��Ա��Ԭ����"};
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
			//�ػ�
			this.repaint();
			//�����߳�
			if(k>=a.length) break;
			k=k+1;
		
		}
		System.out.println("�����߳�2");
	}

	
}
