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
	private String toolTip;//托盘提示
	private TrayIcon trayIon;//托盘图标
	private SystemTray systemTray;//托盘
	private Component component;
	
	public MyTray(Component component ,String imageURL,String toolTip){
		this.imageIon=imageURL;
		this.toolTip=toolTip;
		this.component=component;
		
		init(component,imageIon,toolTip);
		
	}
	public void init(Component component ,String imageIon,String toolTip){
		if(SystemTray.isSupported()){//判断当前平台是否支持托盘
			systemTray=SystemTray.getSystemTray(); 
			
			Image image=Toolkit.getDefaultToolkit().getImage(imageIon);
			PopupMenu popupMenu=new PopupMenu();
			MenuItem exitItem=new MenuItem("退出");
			MenuItem showItem=new MenuItem("显示");
			//增加监听
			exitItem.addActionListener(new RestoreItemActionListener());
			showItem.addActionListener(new ExitItemActionListener());
			
			popupMenu.add(showItem);
			popupMenu.addSeparator();// 将一个分隔线或连字符添加到菜单的当前位置
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
			//左键显示界面
			if(e.getButton()==MouseEvent.BUTTON1){
				component.setVisible(true);
			}
			//右键默认显示菜单
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
