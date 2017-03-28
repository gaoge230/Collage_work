package com.gao;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class TimeDialog {
	String messige;
	JLabel tishi;
	JButton close;
	JDialog jdialog;
	int seconds=0;
	Runnable r;
	public int getDialog(JFrame father ,int second,int type){
		messige="g��ע����";
		tishi=new JLabel(messige);
		
		tishi.setFont(new Font("����", Font.BOLD, 20));
		tishi.setBounds(120, 6, 200, 20);
		
		close=new JButton("�ر�");
		close.setBounds(130,40,60,20);
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jdialog.setVisible(false);
			}
		});
		jdialog=new JDialog(father, true);
		jdialog.setLayout(null);
		jdialog.add(tishi);
		jdialog.add(close);
		this.seconds=second;
		ScheduledExecutorService s=Executors.newSingleThreadScheduledExecutor();
		if(type==0){
			jdialog.setTitle("�´���Ϣ����"+seconds+"��ִ�У���");
		
			r=new Runnable() {
			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					seconds--;
					if(seconds==0){
						jdialog.dispose();
					}else{
						jdialog.setTitle("�´���Ϣ����"+seconds+"��ִ�У���");
					}
				}
			};
		}else{
			jdialog.setTitle("����"+seconds+"������Ϣ������");
			
			 r=new Runnable() {
			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					seconds--;
					if(seconds<0){
						jdialog.dispose();
					}else{
						jdialog.setTitle("�´���Ϣ����"+seconds+"��ִ�У���");
					}
				}
			};
			close.setVisible(false);
		}
		s.scheduleAtFixedRate(r, 1, 1, TimeUnit.SECONDS);
		jdialog.pack(); // �����˴��ڵĴ�С�����ʺ������������ѡ��С�Ͳ��֡� 
        jdialog.setSize(new Dimension(350,100));  
        jdialog.setLocationRelativeTo(father);  
        jdialog.setVisible(true);
		return seconds;
	}
	public void update(){
		

	}
}
