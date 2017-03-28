/*
 * 2015.12.3
 * ����ϵͳ����Ա���������ܿ��Խ���Ĳ�������
 * ��ɽ����˳�򣺴��ϵ��£�������
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
	//������Ҫ�����
	//����ͼƬ
	Image title,timebj,p1_bj,p3_bj;
	JMenuBar jmb;
	//һ���˵�
	JMenu jm1,jm2,jm3,jm4,jm5,jm6;
	//�����˵�
	JMenuItem jm1_1,jm1_2,jm1_3,jm1_4,jm1_5;
	//ͼ��
	ImageIcon jm1_1_icon1,jm1_1_icon2,jm1_1_icon3,jm1_1_icon4,jm1_1_icon5;
	//������
	JToolBar jtb;
	JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8,jb9,jb10;
	//�������
	JPanel jp1,jp2,jp3,jp4,jp5;
	//��ʾ��ǰʱ��
	JLabel timeNow;
	//�м���Ҫ�����
	//p1�����
	JLabel jp1_jl1,jp1_jl2,jp1_jl3,jp1_jl4,jp1_jl5,jp1_jl6,jp1_jl7,jp1_jl8;
	//p2��jlabel
	JLabel jp2_jl1,jp2_jl2;
	//javax.swing���е�timer���Զ�ʱ�Ĵ���actionʱ�䡣
	javax.swing.Timer t;
	//�����ִ���
	JSplitPane jsp1;
	CardLayout ctCardLayout,cardp2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Windows1 windows1=new Windows1();
	}
	
	//��ʼ���˵�
	public void initMenu(){
		//�˵�
		   //����ͼ�괴��ͼ��
		jm1_1_icon1=new ImageIcon("image/jm1_1_icon1.jpg");
		jm1_1_icon2=new ImageIcon("image/jm1_1_icon2.jpg");
		jm1_1_icon3=new ImageIcon("image/jm1_1_icon3.jpg");
		jm1_1_icon4=new ImageIcon("image/jm1_1_icon4.jpg");
		jm1_1_icon5=new ImageIcon("image/jm1_1_icon5.jpg");
		jmb=new JMenuBar();
		   //����һ���˵�
		jm1=new JMenu("ϵͳ����");
		jm1.setFont(MyTools.f1);
		   //���������˵�
		jm1_1=new JMenuItem("�л��û�",jm1_1_icon1);
		jm1_2=new JMenuItem("�л����տ����",jm1_1_icon2); 
		jm1_3=new JMenuItem("��¼����",jm1_1_icon3); 
		jm1_4=new JMenuItem("������",jm1_1_icon4); 
		jm1_5=new JMenuItem("�˳�",jm1_1_icon5); 
		   //��������
		jm1_1.setFont(MyTools.f2);
		jm1_2.setFont(MyTools.f2);
		jm1_3.setFont(MyTools.f2);
		jm1_4.setFont(MyTools.f2);
		jm1_5.setFont(MyTools.f2);
		   //����
		jm1.add(jm1_1);
		jm1.add(jm1_2);
		jm1.add(jm1_3);
		jm1.add(jm1_4);
		jm1.add(jm1_5);
		
		jm2=new JMenu("���¹���");
		jm2.setFont(MyTools.f1);
		jm3=new JMenu("�˵�����");
		jm3.setFont(MyTools.f1);
		jm4=new JMenu("����ͳ��");
		jm4.setFont(MyTools.f1);
		jm5=new JMenu("�ɱ����ⷿ");
		jm5.setFont(MyTools.f1);
		jm6=new JMenu("����");
		jm6.setFont(MyTools.f1);
		
		//��һ���˵�����jmenubar
		jmb.add(jm1);
		jmb.add(jm2);
		jmb.add(jm3);
		jmb.add(jm4);
		jmb.add(jm5);
		jmb.add(jm6);
		//��jmenubar����jframe
		this.setJMenuBar(jmb);
	}
	
	//��ʼ�������Ĺ�����
	public void initToolBar(){
		//���ñ����Ĺ�������
				jtb=new JToolBar();
				   //�ѹ���������Ϊ�����Ը���
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
	//��ʼ���ϲ���ʱ�亯��
	public void initTime(){
		       //�����ϲ���״̬��
				jp5=new JPanel(new BorderLayout());
				timeNow=new JLabel("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"     ");//��õ�ǰʱ��
				timeNow.setFont(MyTools.f1);
				
				  //����timer
				t=new Timer(1000, this); //ÿ��1000���룬ȥ����һ��actionevent�¼�
				  //�����ö�ʱ��
				t.start();
				  //���ñ���
				try {
					timebj=ImageIO.read(new File("image/time_bg.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				   //��������panel
				ImagePanel ip1=new ImagePanel(timebj);
				   //�Ŵ����
				ip1.setLayout(new BorderLayout());
				ip1.add(timeNow,"East");
				jp5.add(ip1);
	}
	//��ʼ���м�
	public void initCenter(){
		//�����м�Ĳ���
				jp1=new JPanel(new BorderLayout());
				//��ñ���ͼƬ
				try {
					p1_bj=ImageIO.read(new File("image/center/jp1_bg.jpg"));
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				//�������
				Cursor myCursor=new Cursor(Cursor.HAND_CURSOR);
				//���������������
				ImagePanel ip2=new ImagePanel(p1_bj);
				//����ip2Ϊ���񲼾�
				ip2.setLayout(new GridLayout(8, 1));
				//�������jlabel
				jp1_jl1=new JLabel(new ImageIcon("image/center/label_1.gif"));
				ip2.add(jp1_jl1,0);
				jp1_jl2=new JLabel("�� �� �� ��",new ImageIcon("image/center/label_2.jpg"),0);
				//��������
				jp1_jl2.setFont(MyTools.f3);
				//����jlabelΪ����
				jp1_jl2.setEnabled(false);
				//ע��������
				jp1_jl2.addMouseListener(this);
				//��������������״(��״���)
				jp1_jl2.setCursor(myCursor);
				ip2.add(jp1_jl2,1);
				jp1_jl3=new JLabel("�� ¼ �� ��",new ImageIcon("image/center/label_3.jpg"),0);
				jp1_jl3.setFont(MyTools.f3);
				jp1_jl3.setEnabled(false);
				jp1_jl3.addMouseListener(this);
				jp1_jl3.setCursor(myCursor);
				ip2.add(jp1_jl3,2);
				jp1_jl4=new JLabel("�� �� �� ��",new ImageIcon("image/center/label_4.jpg"),0);
				jp1_jl4.setFont(MyTools.f3);
				jp1_jl4.setEnabled(false);
				jp1_jl4.addMouseListener(this);
				jp1_jl4.setCursor(myCursor);
				ip2.add(jp1_jl4,3);
				jp1_jl5=new JLabel("�� �� ͳ ��",new ImageIcon("image/center/label_5.jpg"),0);
				jp1_jl5.setFont(MyTools.f3);
				jp1_jl5.setEnabled(false);
				jp1_jl5.addMouseListener(this);
				jp1_jl5.setCursor(myCursor);
				ip2.add(jp1_jl5,4);
				jp1_jl6=new JLabel("�ɱ����ⷿ",new ImageIcon("image/center/label_6.jpg"),0);
				jp1_jl6.setFont(MyTools.f3);
				jp1_jl6.setEnabled(false);
				jp1_jl6.addMouseListener(this);
				jp1_jl6.setCursor(myCursor);
				ip2.add(jp1_jl6,5);
				jp1_jl7=new JLabel("ϵ ͳ �� ��",new ImageIcon("image/center/label_7.jpg"),0);
				jp1_jl7.setFont(MyTools.f3);
				jp1_jl7.setEnabled(false);
				jp1_jl7.addMouseListener(this);
				jp1_jl7.setCursor(myCursor);
				ip2.add(jp1_jl7,6);
				jp1_jl8=new JLabel("�� �� �� ��",new ImageIcon("image/center/label_8.jpg"),0);
				jp1_jl8.setFont(MyTools.f3);
				jp1_jl8.setEnabled(false);
				jp1_jl8.addMouseListener(this);
				jp1_jl8.setCursor(myCursor);
				ip2.add(jp1_jl8,7);
				//������������jp1��
				jp1.add(ip2);
				
				//����jp2,jp3,jp4;
				cardp2=new CardLayout();
				jp2=new JPanel(cardp2);
				
				ctCardLayout=new CardLayout();
				jp3=new JPanel(ctCardLayout);
				jp4=new JPanel(new BorderLayout());
				//��p2 ����jlabel
				jp2_jl1=new JLabel(new ImageIcon("image/center/jp2_left.jpg"));
				jp2_jl2=new JLabel(new ImageIcon("image/center/jp2_right.jpg"));
				jp2_jl1.addMouseListener(this);
				jp2_jl2.addMouseListener(this);
				jp2_jl1.setCursor(myCursor);
				jp2_jl2.setCursor(myCursor);
				jp2.add(jp2_jl1,"left");
				jp2.add(jp2_jl1,"right");
				//��p3��һ�������濨Ƭ
				try {
					p3_bj=ImageIO.read(new File("image/center/p3_bj.jpg"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImagePanel ip3=new ImagePanel(p3_bj);
				
				
				jp3.add(ip3,"0");
				
				jp3.add(new EmpInfo(),"1");
				
				//��jp3��Ӽ���jlabel ������

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
				//��p2��p3����p4��
				jp4.add(jp2,"West");
				jp4.add(jp3,"Center");
				
				 //��һ����ִ��ڣ��ֱ���p1 �� p4
				jsp1=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jp1,jp4);
				//������ߵĴ�С
				jsp1.setDividerLocation(120);
				//�ѱ߽�����Ϊ0
				jsp1.setDividerSize(0);
	}
	//���캯��
	public Windows1(){
		//���ò���
		this.setLayout(new BorderLayout());
		 //��jframe��ȡ������Container
		Container ct=this.getContentPane();
		
		//���ó�ʼ���˵�����
		this.initMenu();
		//���ó�ʼ������������
		this.initToolBar();
		//���ó�ʼ���в��ĺ���
		this.initCenter();
		//��ʼ���ϲ�ʱ��
		this.initTime();
		
		//���������
		ct.add(jtb,"North");
		ct.add(jsp1,"Center");
		ct.add(jp5,"South");
		
		
		//���ö�������Jframe�ı���ͼƬ
		try {
			title=ImageIO.read(new File("image/title.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setTitle("����¥��������ϵͳ��");
		this.setFont(MyTools.f3);
		this.setIconImage(title);
		//���ô�С
		int w=Toolkit.getDefaultToolkit().getScreenSize().width;
		int h=Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setSize(w,h-35);
		//�رմ���ʱ���˳�ϵͳ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//���»�õ�ǰʱ��
		this.timeNow.setText("��ǰʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+"     ");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//���û������jlabel����Ӧ��Ӧ�Ŀ�Ƭ
		if(e.getSource()==jp1_jl2){
			//������¹���
			ctCardLayout.show(jp3, "1");
		}else if (e.getSource()==jp1_jl3){
			//�����¼����
			ctCardLayout.show(jp3, "2");
		}else if(e.getSource()==jp1_jl4){
			
		}else if(e.getSource()==jp1_jl5){
			
		}else if(e.getSource()==jp1_jl6){
			
		}else if(e.getSource()==jp1_jl7){
			
		}else if(e.getSource()==jp1_jl8){
			
		}else if(e.getSource()==jp2_jl1){
			//�����ť �����������л�cardp2
			this.cardp2.show(jp2, "right");
			//����ִ���jsp1�л�
			jsp1.setDividerLocation(0);
			
		}else if(e.getSource()==jp2_jl2){
			//����Ұ�ť�������������л�cardp2
			this.cardp2.show(jp2, "left");
			//����ִ���jsp1�л�
			jsp1.setDividerLocation(120);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//����û���������ĳ��jlabel�����
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
		//����û�ѡ����ĳ��jlabel�����
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
