package com.gao;





import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DesktopLock {
	JFrame lockDesk=new JFrame();
	Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
	int width=(int) d.getWidth();
	int height=(int) d.getHeight();
	Robot  robot=null;
	int second;

	public DesktopLock(int second){
		this.second=second;
		myPanel m=new myPanel(this.second,lockDesk);
		lockDesk.setContentPane(m);
		Thread t=new Thread(m);
		t.start();
		lockDesk.setAlwaysOnTop(true);
		//lockDesk.toFront();
		lockDesk.setUndecorated(true);
		lockDesk.setSize(width, height);
		lockDesk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lockDesk.setVisible(true);
		try {
			robot=new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock();
	}
	public void lock(){
		lockMouse();
		lockDesk.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				robot.keyRelease(17);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==18){
					robot.keyRelease(e.getKeyCode());
					robot.keyPress(524);
				}
				robot.keyRelease(e.getKeyCode());
				lockDesk.toFront();
			}
		});
		
		lockDesk.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				lockDesk.toFront();
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	//锁鼠标
	public void lockMouse(){
		lockDesk.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
				Point p=MouseInfo.getPointerInfo().getLocation();
				int x=p.x;
				int y=p.y;
				if(x>(width/2+100)){
					robot.mouseMove(width/2-100, y);
				}
				if(x<(width/2-100)){
					robot.mouseMove(width/2+100, y);
				}
				if(y>(height*(2f/8f))){
					robot.mouseMove(x, (int)(height*(1/8)));
				}
				if(y<(height*(1f/8f))){
					robot.mouseMove(x, (int)(height*(2/8)));
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public static void main(String[] aargs){
			//new DesktopLock();
	}
	
}

//自定义面板p
class myPanel extends JPanel implements Runnable{

	String tip="";
	int second;
	String str="";
	JFrame jframe;
	public myPanel(int second,JFrame jframe){
		tip="在不被人注意的角落里起飞！！";
		this.second=second;
		this.jframe=jframe;
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		this.setLayout(null);
		this.setBackground(Color.GREEN);
		
		g.setColor(Color.RED);
		g.setFont(new Font("宋体",Font.BOLD,50));
		g.drawString(tip, 340, 200);
		g.setColor(Color.BLUE);
		//g.drawString("锁", width/2-110, height/2+70);
		//g.drawOval(width / 2 - 135, height / 2 - 135, 270, 270);
		g.drawString(str, 650, 400);
		g.setColor(Color.BLACK);
		g.drawOval(590, 290, 200, 200);
		
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(second>0){
			second--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int minute=second/60;
			str=minute+":"+second%60;
			this.repaint();
			
		}
		jframe.dispose();
	}

}
