/*
 * �������˵Ŀ��ƽ��棬��������������رշ�����
 * ���Թ���ͼ���û�
 */
package com.qq.server.view;
import javax.swing.*;

import com.qq.server.model.Myqqserver;

import java.awt.*;
import java.awt.event.*;

public class Mmserverframe extends JFrame implements ActionListener{
        JPanel jp;
        JButton jb1,jb2;
        
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mmserverframe mmserverframe=new Mmserverframe();
	}
     public Mmserverframe()
     {
    	 jp=new JPanel();
    	 jb1=new JButton("����������");
    	 //��������
    	 jb1.addActionListener(this);
    	 jb2=new JButton("�رշ�����");
    	 jp.add(jb1);
    	 jp.add(jb2);
    	 
    	 this.add(jp);
    	 this.setSize(600, 600);
    	 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 this.setVisible(true);
    	 
     }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1)
		{
			new Myqqserver();
		}
		
	}
}
