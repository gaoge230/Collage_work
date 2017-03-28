package com.gao;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class MyTray {
	private String imageIon;
	private String toolTip;//������ʾ
	private TrayIcon trayIon;//����ͼ��
	private SystemTray systemTray;//����
	private Component component;
	
	public MyTray(Component component ,String imageURL,String toolTip){
		this.imageIon=imageURL;
		this.toolTip=toolTip;
		this.component=component;
		
		init(component,imageIon,toolTip);
		
	}
	public void init(Component component ,String imageIon,String toolTip){
		if(SystemTray.isSupported()){//�жϵ�ǰƽ̨�Ƿ�֧������
			systemTray=SystemTray.getSystemTray(); 
			
			Image image=Toolkit.getDefaultToolkit().getImage(imageIon);
			PopupMenu popupMenu=new PopupMenu();
			MenuItem exitItem=new MenuItem("�˳�");
			MenuItem showItem=new MenuItem("��ʾ");
			//���Ӽ���
			exitItem.addActionListener(new RestoreItemActionListener());
			showItem.addActionListener(new ExitItemActionListener());
			
			popupMenu.add(showItem);
			popupMenu.addSeparator();// ��һ���ָ��߻����ַ���ӵ��˵��ĵ�ǰλ��
			popupMenu.add(exitItem);
			
			trayIon=new TrayIcon(image, toolTip, popupMenu);
			trayIon.addMouseListener(new TrayIconMouseListener());
		
			try {
				systemTray.add(trayIon);
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public class RestoreItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
		
	}
	public class ExitItemActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			component.setVisible(true);
		}
		
	}
	public class TrayIconMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			//�����ʾ����
			if(e.getButton()==MouseEvent.BUTTON1){
				component.setVisible(true);
			}
			//�Ҽ�Ĭ����ʾ�˵�
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public  static void createTray(Component component ,String imageURL,String toolTip){
		new MyTray(component, imageURL, toolTip) ;
	}
}
