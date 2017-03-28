package com.gao;

import javax.swing.*;

import sun.audio.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainFrame extends JFrame implements ActionListener{
	
	Timer timer;
	JLabel alert,jl1_1,jl1_2;
	JTextField  rest,period;
	JButton enter,stop;
	JPanel jp2,jp0,jp1,jp1_1,jp1_2,jp1_3;
	int periodTime,restTime;
	TimeDialog timerdialog;
	int realsecond=5;
	
	
	public MainFrame(){
		this.setLayout(new BorderLayout());
		
		jp2=new JPanel(new BorderLayout());
		alert=new JLabel("      ������ʿ");
		alert.setFont(new Font("����",Font.PLAIN,40));
		alert.setForeground(Color.ORANGE);
		jp2.add(alert,"Center");
		
		rest=new JTextField(10);
		period=new JTextField(10);
		enter=new JButton("ȷ�ϣ�");
		enter.addActionListener(this);
		stop=new JButton("ֹͣ");
		stop.addActionListener(this);
		
		jp0=new JPanel();
		jp1=new JPanel(new GridLayout(3, 1));
		jp1_1=new JPanel();
		jl1_1=new JLabel("����ʱ������");
		
		jp1_1.add(jl1_1);		
		jp1_1.add(rest);		
		jl1_2=new JLabel("������Ϣʱ�䣺");
		jp1_2=new JPanel();
		jp1_2.add(jl1_2);
		jp1_2.add(period);
		
		
		jp1_3=new JPanel();
		jp1_3.add(enter);
		jp1_3.add(stop);
		stop.setVisible(false);
		jp1.add(jp1_1);
		jp1.add(jp1_2);
		jp1.add(jp1_3);
		
		jp0.add(jp1);

		this.add(jp2,"North");
		this.add(jp0,"South");
		
		this.addWindowListener(new WindowAdapter() {
			public void windowIconified(WindowEvent e) {
				dispose();// ������С��ʱdispose�ô���
			}
		});
		MyTray.createTray(this, "images/shu.jpg", "����������׵����");
		this.setVisible(true);
		this.setLocation(450, 200);
		this.setSize(400, 200);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==enter){
			if(period.getText().equals("")||rest.getText().equals("")){
				JOptionPane.showMessageDialog(this, "��������Ч��ʱ��������Ϣʱ�䣡��");
			}
			else{
				periodTime=Integer.parseInt(period.getText().trim());
				restTime=Integer.parseInt(rest.getText().trim());
				start();
				JOptionPane.showMessageDialog(this, "���óɹ�����");
				this.enter.setVisible(false);
				this.stop.setVisible(true);
				this.period.setEditable(false);
				this.rest.setEditable(false);
				this.dispose();
			}
		}else if(e.getSource()==stop){
			timer.cancel();
			this.enter.setVisible(true);
			this.stop.setVisible(false);
			this.period.setEditable(true);
			this.rest.setEditable(true);
		}
	}
	
	public void start(){
		timer=new Timer();
		TimerTask  timerTask=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//ִ����Ϣʱ��
				carry_rest();
				
			}
		};
		timer.schedule(timerTask, (periodTime)*10*1000, periodTime*30*1000);
	}
	
	//��Ϣ����
	public void carry_rest(){
		//��ʾ��ʾ���� 1���������Ϣ��
		int second=realsecond;
		//һ��5����Զ��رյ���ʾ����
		timerdialog=new TimeDialog();
		second=timerdialog.getDialog(this, second,0);
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��������
		try {
			InputStream io=new FileInputStream("E://�һ�//����//����08_����֮��cnwav.wav");
			AudioStream as = new AudioStream(io);
			AudioData ad=as.getData();
			AudioPlayer.player.start(as);
	
			//����ѭ������  
			ContinuousAudioDataStream cads= new ContinuousAudioDataStream (ad);
			//ѭ�����ſ�ʼŶ  
			AudioPlayer.player.start(cads);  
			//ѭ������ֹͣ  
			timerdialog=new TimeDialog();
			//second=timerdialog.getDialog(this, restTime*10, 1);
			//����
			
			DesktopLock desktopLock=new DesktopLock(restTime*10);
			while(desktopLock.lockDesk.isVisible()==true){
				System.out.println(desktopLock.lockDesk.isShowing());
			}
			
				AudioPlayer.player.stop(cads); 
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args){
		new MainFrame();
	}

}
class loopMusic extends Thread{
	public void run(){
	
			//��������
			try {
				InputStream io=new FileInputStream("E://�һ�//����//����08_����֮��cnwav.wav");
				AudioStream as = new AudioStream(io);
				AudioData ad=as.getData();
				AudioPlayer.player.start(as);
		
				//����ѭ������  
				ContinuousAudioDataStream cads= new ContinuousAudioDataStream (ad);
				//ѭ�����ſ�ʼŶ  
				AudioPlayer.player.start(cads);  
				//ѭ������ֹͣ  
				AudioPlayer.player.stop(cads);  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
}