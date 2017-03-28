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
		messige="g关注健康";
		tishi=new JLabel(messige);
		
		tishi.setFont(new Font("宋体", Font.BOLD, 20));
		tishi.setBounds(120, 6, 200, 20);
		
		close=new JButton("关闭");
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
			jdialog.setTitle("下次休息将在"+seconds+"后执行！！");
		
			r=new Runnable() {
			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					seconds--;
					if(seconds==0){
						jdialog.dispose();
					}else{
						jdialog.setTitle("下次休息将在"+seconds+"后执行！！");
					}
				}
			};
		}else{
			jdialog.setTitle("还有"+seconds+"结束休息！！！");
			
			 r=new Runnable() {
			
				@Override
				public void run() {
					// TODO Auto-generated method stub
					seconds--;
					if(seconds<0){
						jdialog.dispose();
					}else{
						jdialog.setTitle("下次休息将在"+seconds+"后执行！！");
					}
				}
			};
			close.setVisible(false);
		}
		s.scheduleAtFixedRate(r, 1, 1, TimeUnit.SECONDS);
		jdialog.pack(); // 调整此窗口的大小，以适合其子组件的首选大小和布局。 
        jdialog.setSize(new Dimension(350,100));  
        jdialog.setLocationRelativeTo(father);  
        jdialog.setVisible(true);
		return seconds;
	}
	public void update(){
		

	}
}
